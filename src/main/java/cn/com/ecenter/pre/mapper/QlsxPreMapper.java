package cn.com.ecenter.pre.mapper;


import java.util.List;
import java.util.Map;

import cn.com.ecenter.pre.entity.QlsxPreEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface QlsxPreMapper {

    int insertSelective(QlsxPreEntity record);

    QlsxPreEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QlsxPreEntity record);

    List<QlsxPreEntity> list(Map<String, Object> qryMap);

    int count(Map<String, Object> qryMap);

}
