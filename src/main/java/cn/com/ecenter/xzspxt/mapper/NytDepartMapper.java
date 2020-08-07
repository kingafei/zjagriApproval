package cn.com.ecenter.xzspxt.mapper;


import java.util.List;
import java.util.Map;

import cn.com.ecenter.xzspxt.entity.NytDepartEntity;
import cn.com.ecenter.xzspxt.entity.ZwfwRegionEntity;
import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface NytDepartMapper {

    int insertSelective(NytDepartEntity record);

    NytDepartEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NytDepartEntity record);

    List<NytDepartEntity> list(Map<String, Object> qryMap);

    int count(Map<String, Object> qryMap);

    int saveList( List<ZwfwRegionEntity> list);
}
