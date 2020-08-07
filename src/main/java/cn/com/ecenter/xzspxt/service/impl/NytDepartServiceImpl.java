package cn.com.ecenter.xzspxt.service.impl;


import cn.com.ecenter.xzspxt.common.Sys_Constant;
import cn.com.ecenter.xzspxt.entity.NytDepartEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.entity.ZwfwRegionEntity;
import cn.com.ecenter.xzspxt.mapper.NytDepartMapper;
import cn.com.ecenter.xzspxt.mapper.ZwfwRegionMapper;
import cn.com.ecenter.xzspxt.service.NytDepartService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("nytepartService")
public class NytDepartServiceImpl implements NytDepartService {

    @Autowired
    private cn.com.ecenter.xzspxt.mapper.NytDepartMapper NytDepartMapper;

    @Autowired
    private ZwfwRegionMapper zwfwRegionMapper;

    /**
     * 列表
     */
    @Override
    public ResultData<List<NytDepartEntity>> list(Map<String, Object> qryMap){
        ResultData<List<NytDepartEntity>> result = new ResultData<List<NytDepartEntity>>();
        try {
            result.setData(NytDepartMapper.list(qryMap));
            result.setCount(NytDepartMapper.count(qryMap));
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
    public ResultData<NytDepartEntity> sel(Integer id){
        ResultData<NytDepartEntity> result = new ResultData<NytDepartEntity>();
        try{
            result.setData(NytDepartMapper.selectByPrimaryKey(id));
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
     * 保存
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public ResultData<NytDepartEntity> save(NytDepartEntity record){
        ResultData<NytDepartEntity> result = new ResultData<NytDepartEntity>();
        try{

            Date date = new Date();

            record.setCreateTime(date);
            record.setUpdateTime(date);
            List<ZwfwRegionEntity> zwfwRegionEntities = zwfwRegionMapper.selectAllByDept(1, 1);



            if(NytDepartMapper.insertSelective(record) > 0){
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
    public ResultData<NytDepartEntity> update(NytDepartEntity record){
        ResultData<NytDepartEntity> result = new ResultData<NytDepartEntity>();
        try{
            if(NytDepartMapper.updateByPrimaryKeySelective(record) > 0){
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
            NytDepartEntity record = new NytDepartEntity();
            record.setIsDelete(1);
            String[] idsList = ids.split(",");
            for (String id : idsList){
                record.setId(Integer.valueOf(id));
                    NytDepartMapper.updateByPrimaryKeySelective(record);
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
