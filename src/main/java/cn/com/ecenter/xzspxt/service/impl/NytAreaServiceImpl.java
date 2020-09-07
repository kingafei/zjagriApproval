package cn.com.ecenter.xzspxt.service.impl;


import cn.com.ecenter.common.entity.AreaTree;
import cn.com.ecenter.common.utils.TreeUtil;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.system.mapper.DeptMapper;
import cn.com.ecenter.xzspxt.common.Sys_Constant;
import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.mapper.NytAreaMapper;
import cn.com.ecenter.xzspxt.service.NytAreaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NytAreaServiceImpl implements NytAreaService {

    @Autowired
    private NytAreaMapper nytAreaMapper;

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 树形列表
     */
    @Override
    public List<AreaTree<NytAreaEntity>> treeList(NytAreaEntity NytArea){
        Integer parentId = null;
        Integer level = null;
        // 默认查询从根节点开始
        if (NytArea.getParentId() == null) {
            parentId = 2;
        }
        // 默认查询到县（区）级别
        if (NytArea.getLevel() == null) {
            level = 4;
        }
        NytAreaEntity area = nytAreaMapper.selectByPrimaryKey(parentId);
        List<NytAreaEntity> list = nytAreaMapper.queryByUrl(area.getCodeUrl(),String.valueOf(level));
        List<AreaTree<NytAreaEntity>> trees = convertAreas(list);
        return TreeUtil.buildAreaList(trees, String.valueOf(parentId));
    }


    /**
     *
     * @param areas
     * @return
     */
    private List<AreaTree<NytAreaEntity>> convertAreas(List<NytAreaEntity> areas) {
        List<AreaTree<NytAreaEntity>> trees = new ArrayList<>();
        areas.forEach(area -> {
            AreaTree<NytAreaEntity> tree = new AreaTree<>();
            tree.setId(String.valueOf(area.getId()));
            tree.setParentId(String.valueOf(area.getParentId()));
            tree.setName(area.getName());
            tree.setLevel(area.getLevel());
            tree.setCodeUrl(area.getCodeUrl());
            tree.setData(area);
            trees.add(tree);
        });
        return trees;
    }

    /**
     * 根据父id查询子级
     * @param parentId
     * @return
     */
    @Override
    public ResultData<List<NytAreaEntity>> getByParent(Integer parentId){
        ResultData<List<NytAreaEntity>> result = new ResultData<List<NytAreaEntity>>();
        try {
            result.setData(nytAreaMapper.list(parentId));
            result.setCode(Sys_Constant.SUCCESS_CODE);
            result.setMsg(Sys_Constant.SUCCESS_MSG);
        } catch (Exception e) {
            result.setCode(Sys_Constant.FAIL_CODE);
            result.setMsg(Sys_Constant.FAIL_MSG);
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    /**
     * 查找单个信息
     */
    @Override
    public ResultData<NytAreaEntity> sel(String id){
        ResultData<NytAreaEntity> result = new ResultData<NytAreaEntity>();
        try{
            result.setData(nytAreaMapper.selectByPrimaryKey(Integer.valueOf(id)));
            result.setCode(Sys_Constant.SUCCESS_CODE);
            result.setMsg(Sys_Constant.SUCCESS_MSG);
        } catch (Exception e) {
            result.setCode(Sys_Constant.FAIL_CODE);
            result.setMsg(Sys_Constant.FAIL_MSG);
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    /**
     * 添加保存
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void save(NytAreaEntity record){
        Date date = new Date();
        record.setIsDelete("0");
        record.setCreateTime(date);
        record.setUpdateTime(date);
        record.setState("0");
        // 传过来的级别参数为从父亲那获取过来的，有父id则加1，没有则为最高级别1
        int nowId = nytAreaMapper.maxId() + 1;
        if (null == record.getParentId()) {
            // 如果没有父级默级别唯一，父id为0，id路径为当前路径
            record.setLevel("1");
            record.setParentId(0);
            record.setCodeUrl(nowId + "");
        } else {
            NytAreaEntity area = nytAreaMapper.selectByPrimaryKey(record.getParentId());
            int nowLevel = Integer.parseInt(area.getLevel()) + 1;
            record.setLevel(nowLevel + "");
            record.setCodeUrl(area.getCodeUrl() + "-" + nowId);
        }
        nytAreaMapper.insertSelective(record);
    }
    /**
     * 修改
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void update(NytAreaEntity record){
        nytAreaMapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 批量软删除
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void delByIds(String ids){
        NytAreaEntity record = new NytAreaEntity();
        record.setIsDelete("1");
        String[] idsList = ids.split(",");
        for (String id : idsList){
            record.setId(Integer.valueOf(id));
            // 通过id获取地区路径，并修改为删除路径
            String oldCodeUrl = nytAreaMapper.selectByPrimaryKey(Integer.valueOf(id)).getCodeUrl();
            int indexOf = oldCodeUrl.indexOf("-");
            String newCodeUrl = "1";
            if (indexOf != -1) {
                newCodeUrl = newCodeUrl + oldCodeUrl.substring(indexOf);
            }
            record.setCodeUrl(newCodeUrl);
                nytAreaMapper.updateByPrimaryKeySelective(record);
        }
    }

    /**
     * 根据当前用户获取地区树形列表
     */
    @Override
    public List<AreaTree<NytAreaEntity>> userTree(){
        // 获取当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        // 获取用户所在部门
        Dept dept = deptMapper.selectById(user.getDeptId());
        // 获取用户坐在地区
        NytAreaEntity ar = nytAreaMapper.queryByAdcode(dept.getAdministrationCode());
        //查询当前用户所在地区及下级地区
        List<NytAreaEntity> list = nytAreaMapper.queryByUrl(ar.getCodeUrl(),String.valueOf(4));
        List<AreaTree<NytAreaEntity>> trees = convertAreas(list);
        return TreeUtil.buildAreaList(trees, String.valueOf(ar.getParentId()));
    }

    /**
     * 根据adcode编码查询
     */
    @Override
    public  NytAreaEntity queryByAdcode(String adcode) {
        return nytAreaMapper.queryByAdcode(adcode);
    }

}
