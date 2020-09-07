package cn.com.ecenter.xzspxt.service;


import cn.com.ecenter.common.entity.DictTree;
import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;

import java.util.List;
import java.util.Map;

public interface NytDataDictService {

    /**
     * 保存
     */
    void save(NytDataDictEntity record);
    /**
     * 修改
     */
    void update(NytDataDictEntity record);

    /**
     * 据父id查询字典
     */
    List<Map<String, String>>  selectByPid(String pid);

    /**
     * /获取字典树
     * @return
     */
    List<DictTree<NytDataDictEntity>> selectTree();

}

