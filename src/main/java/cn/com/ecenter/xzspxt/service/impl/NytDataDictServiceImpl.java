package cn.com.ecenter.xzspxt.service.impl;


import cn.com.ecenter.common.entity.DictTree;
import cn.com.ecenter.common.utils.TreeUtil;
import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;
import cn.com.ecenter.xzspxt.mapper.NytDataDictMapper;
import cn.com.ecenter.xzspxt.service.NytDataDictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("NytDataDictService")
public class NytDataDictServiceImpl implements NytDataDictService {

    @Autowired
    private NytDataDictMapper nytDataDictMapper;

    /**
     * 保存
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void save(NytDataDictEntity record){
            nytDataDictMapper.insertSelective(record);
    }
    /**
     * 修改
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void update(NytDataDictEntity record){
       nytDataDictMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据父id查询字典
     * @param pid
     * @return
     */
    @Override
    public List<Map<String, String>> selectByPid(String pid) {
         List<NytDataDictEntity> list = nytDataDictMapper.selectByPid(pid);
         List<Map<String, String>> dicts = new ArrayList<>();
        for (NytDataDictEntity dict: list) {
            Map<String, String> map = new HashMap<>();
            map.put("value", dict.getDictValue());
            map.put("text",dict.getValueComment());
            map.put("title",dict.getComment());
            dicts.add(map);
        }
         return  dicts;
    }

    /**
     *  获取字典树
     * @return
     */
    @Override
    public List<DictTree<NytDataDictEntity>> selectTree() {
        List<NytDataDictEntity> nytDataDictEntities = nytDataDictMapper.selectAll();
        List<DictTree<NytDataDictEntity>> trees = this.convertDepts(nytDataDictEntities);
        return TreeUtil.buildDictList(trees);

    }

    private List<DictTree<NytDataDictEntity>> convertDepts(List<NytDataDictEntity> dicts) {
        List<DictTree<NytDataDictEntity>> trees = new ArrayList<>();
        dicts.forEach(dict -> {
            DictTree<NytDataDictEntity> tree = new DictTree<>();
            tree.setId(String.valueOf(dict.getPkId()));
            tree.setParentId(String.valueOf(dict.getFkUid()));
            tree.setName(dict.getValueComment());
            tree.setData(dict);
            trees.add(tree);
        });
        return trees;
    }


}
