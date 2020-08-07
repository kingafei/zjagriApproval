package cn.com.ecenter.xzspxt.service;


import cn.com.ecenter.xzspxt.entity.NytDepartEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;

public interface NytDepartService {

    /**
     * 列表
     */
    ResultData<List<NytDepartEntity>> list(Map<String, Object> qryMap);
    /**
     * 查找单个信息
     */
    ResultData<NytDepartEntity> sel(Integer id);
    /**
     * 保存
     */
    ResultData<NytDepartEntity> save(NytDepartEntity record);
    /**
     * 修改
     */
    ResultData<NytDepartEntity> update(NytDepartEntity record);
    /**
     * 批量软删除
     */
    ResultData<String> delByIds(String ids);

}

