package cn.com.ecenter.xzspxt.service.impl;


import cn.com.ecenter.xzspxt.common.Sys_Constant;
import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.mapper.NytAreaMapper;
import cn.com.ecenter.xzspxt.service.NytAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NytAreaServiceImpl implements NytAreaService {

    @Autowired
    private NytAreaMapper nytAreaMapper;

    /**
     * 列表
     */
    @Override
    public ResultData<List<NytAreaEntity>> treeList(Integer parentId){
        ResultData<List<NytAreaEntity>> result = new ResultData<List<NytAreaEntity>>();
        try {
            result.setData(getTreeList(parentId));
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
     * 递归查询地区树
     */
    public List<NytAreaEntity> getTreeList(Integer parentId){
        //List<NytAreaEntity> treeList = new ArrayList<NytAreaEntity>();
        List<NytAreaEntity> treeList = nytAreaMapper.list(parentId);
        for (NytAreaEntity na : treeList) {
            na.setTreeList(getTreeList(na.getId()));
        }
        return treeList;
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
    public ResultData<NytAreaEntity> save(NytAreaEntity record){
        ResultData<NytAreaEntity> result = new ResultData<NytAreaEntity>();
        try{
            Date date = new Date();
            record.setIsDelete("0");
            record.setCreateTime(date);
            record.setUpdateTime(date);
            record.setState("0");
            // 传过来的级别参数为从父亲那获取过来的，有父id则加1，没有则为最高级别1
            String level = record.getLevel();
            if (level != null) {
                record.setLevel(Integer.parseInt(level) + 1 + "");
            } else {
                record.setLevel("1");
            }
            // 判断有没有父id,有则拼接
            if (record.getParentId() != null) {
                int nowId = nytAreaMapper.maxId() + 1;
                record.setCodeUrl(record.getCodeUrl() + "-" + nowId);
            } else {
                // 无责为最高级别
                record.setParentId(0);
                int nowId = nytAreaMapper.maxId() + 1;
                record.setCodeUrl(nowId + "");
            }
            if(nytAreaMapper.insertSelective(record) > 0){
                result.setData(record);
                result.setCode(Sys_Constant.SUCCESS_CODE);
                result.setMsg(Sys_Constant.SUCCESS_MSG);
            } else {
                result.setCode(Sys_Constant.SUCCESS_CODE);
                result.setMsg(Sys_Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            result.setCode(Sys_Constant.FAIL_CODE);
            result.setMsg(Sys_Constant.FAIL_MSG);
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    /**
     * 修改
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public ResultData<NytAreaEntity> update(NytAreaEntity record){
        ResultData<NytAreaEntity> result = new ResultData<NytAreaEntity>();
        try{
            if(nytAreaMapper.updateByPrimaryKeySelective(record) > 0){
                result.setData(record);
                result.setCode(Sys_Constant.SUCCESS_CODE);
                result.setMsg(Sys_Constant.SUCCESS_MSG);
            } else {
                result.setCode(Sys_Constant.SUCCESS_CODE);
                result.setMsg(Sys_Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            result.setCode(Sys_Constant.FAIL_CODE);
            result.setMsg(Sys_Constant.FAIL_MSG);
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    /**
     * 批量软删除
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public ResultData<String> delByIds(String ids){
        ResultData<String> result = new ResultData<String>();
        try{
            NytAreaEntity record = new NytAreaEntity();
            record.setIsDelete("1");
            String[] idsList = ids.split(",");
            for (String id : idsList){
                record.setId(Integer.valueOf(id));
                // 通过id获取地区路径，并修改为删除路径
                String oldCodeUrl = nytAreaMapper.selectByPrimaryKey(Integer.valueOf(id)).getCodeUrl();
                int indexOf = oldCodeUrl.indexOf("-");
                String newCodeUrl = "0";
                if (indexOf != -1) {
                    newCodeUrl = newCodeUrl + oldCodeUrl.substring(indexOf);
                }
                record.setCodeUrl(newCodeUrl);
                    nytAreaMapper.updateByPrimaryKeySelective(record);
            }
            result.setData("处理成功！");
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

}
