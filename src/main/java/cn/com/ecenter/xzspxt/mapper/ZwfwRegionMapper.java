package cn.com.ecenter.xzspxt.mapper;

import cn.com.ecenter.xzspxt.entity.ZwfwRegionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZwfwRegionMapper {

    List<ZwfwRegionEntity> selectAll();

    List<ZwfwRegionEntity> selectAllBycode(String param1, String param2);

    List<ZwfwRegionEntity>  selectAllByDept(Integer idx,Integer size);

}
