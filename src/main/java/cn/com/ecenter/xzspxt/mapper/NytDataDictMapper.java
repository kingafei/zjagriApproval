package cn.com.ecenter.xzspxt.mapper;


import java.util.List;
import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface NytDataDictMapper {

    int insertSelective(NytDataDictEntity record);

    int updateByPrimaryKeySelective(NytDataDictEntity record);


    //  获取字典表分组集合
    List<NytDataDictEntity> selectAll();

    //  获取分组集合子级
    List<NytDataDictEntity> selectByPid(String pid);





}
