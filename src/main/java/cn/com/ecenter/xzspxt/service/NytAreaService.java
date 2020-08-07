package cn.com.ecenter.xzspxt.service;


import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;

import java.util.List;
import java.util.Map;

public interface NytAreaService {

    /**
     * 列表
     */
    ResultData<List<NytAreaEntity>> treeList(Integer parentId);
    /**
     * 查找单个信息
     */
    ResultData<NytAreaEntity> sel(String id);
    /**
     * 保存
     */
    ResultData<NytAreaEntity> save(NytAreaEntity record);
    /**
     * 修改
     */
    ResultData<NytAreaEntity> update(NytAreaEntity record);
    /**
     * 批量软删除
     */
    ResultData<String> delByIds(String ids);

}

