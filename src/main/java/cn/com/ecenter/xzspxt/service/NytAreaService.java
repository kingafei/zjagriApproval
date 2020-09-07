package cn.com.ecenter.xzspxt.service;


import cn.com.ecenter.common.entity.AreaTree;
import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.entity.ResultData;

import java.util.List;
import java.util.Map;

public interface NytAreaService {

    /**
     * 树形列表
     */
    List<AreaTree<NytAreaEntity>> treeList(NytAreaEntity NytArea);
    /**
     * 查找单个信息
     */
    ResultData<NytAreaEntity> sel(String id);
    /**
     * 保存
     */
    void save(NytAreaEntity record);
    /**
     * 修改
     */
    void update(NytAreaEntity record);
    /**
     * 批量软删除
     */
    void delByIds(String ids);

    /**
     * 根据父id查询子级
     */
    ResultData<List<NytAreaEntity>> getByParent(Integer parentId);

    /**
     * 根据当前用户获取其所在地区树
     */
    List<AreaTree<NytAreaEntity>> userTree();

    NytAreaEntity queryByAdcode(String adcode);

}

