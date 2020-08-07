package cn.com.ecenter.xzspxt.mapper;

import java.util.List;
import java.util.Map;

import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface NytAreaMapper {

    int insertSelective(NytAreaEntity record);

    NytAreaEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NytAreaEntity record);

    List<NytAreaEntity> list(Integer parentId);

    int count(Map<String, Object> qryMap);

    int maxId();

}
