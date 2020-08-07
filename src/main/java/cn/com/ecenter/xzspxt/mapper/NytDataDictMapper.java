package cn.com.ecenter.xzspxt.mapper;


import java.util.List;
import java.util.Map;

import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface NytDataDictMapper {

    int insertSelective(NytDataDictEntity record);

    NytDataDictEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NytDataDictEntity record);

    List<NytDataDictEntity> list(Map<String, Object> qryMap);

    int count(Map<String, Object> qryMap);





}
