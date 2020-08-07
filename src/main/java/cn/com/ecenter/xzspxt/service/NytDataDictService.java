package cn.com.ecenter.xzspxt.service;


import cn.com.ecenter.xzspxt.entity.ResultData;
import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;

import java.util.List;
import java.util.Map;

public interface NytDataDictService {

    /**
     * 列表
     */
    ResultData<List<NytDataDictEntity>> list(Map<String, Object> qryMap);
    /**
     * 查找单个信息
     */
    ResultData<NytDataDictEntity> sel(String id);
    /**
     * 保存
     */
    ResultData<NytDataDictEntity> save(NytDataDictEntity record);
    /**
     * 修改
     */
    ResultData<NytDataDictEntity> update(NytDataDictEntity record);
    /**
     * 批量软删除
     */
    ResultData<String> delByIds(String ids);

}

