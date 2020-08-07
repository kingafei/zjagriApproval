package cn.com.ecenter.xzspxt.service.impl;


import cn.com.ecenter.xzspxt.common.Sys_Constant;
import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;
import cn.com.ecenter.xzspxt.mapper.NytDataDictMapper;
import cn.com.ecenter.xzspxt.service.NytDataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("NytDataDictService")
public class NytDataDictServiceImpl implements NytDataDictService {

    @Autowired
    private NytDataDictMapper nytDataDictMapper;

    /**
     * 列表
     */
    @Override
    public ResultData<List<NytDataDictEntity>> list(Map<String, Object> qryMap){
        ResultData<List<NytDataDictEntity>> result = new ResultData<List<NytDataDictEntity>>();
        try {
            result.setData(nytDataDictMapper.list(qryMap));
            result.setCount(nytDataDictMapper.count(qryMap));
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
    public ResultData<NytDataDictEntity> sel(String id){
        ResultData<NytDataDictEntity> result = new ResultData<NytDataDictEntity>();
        try{
            result.setData(nytDataDictMapper.selectByPrimaryKey(id));
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
    public ResultData<NytDataDictEntity> save(NytDataDictEntity record){
        ResultData<NytDataDictEntity> result = new ResultData<NytDataDictEntity>();
        try{

            Date date = new Date();
            record.setIsDelete(0);
            record.setCreateTime(date);
            record.setUpdateTime(date);
            if(nytDataDictMapper.insertSelective(record) > 0){
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
    public ResultData<NytDataDictEntity> update(NytDataDictEntity record){
        ResultData<NytDataDictEntity> result = new ResultData<NytDataDictEntity>();
        try{
            if(nytDataDictMapper.updateByPrimaryKeySelective(record) > 0){
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
            NytDataDictEntity record = new NytDataDictEntity();
            record.setIsDelete(1);
            String[] idsList = ids.split(",");
            for (String id : idsList){
                record.setPkId(Integer.valueOf(id));
                nytDataDictMapper.updateByPrimaryKeySelective(record);
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
