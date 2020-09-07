package cn.com.ecenter.xzspxt.mapper;

import cn.com.ecenter.xzspxt.entity.ZwfwRegionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZwfwRegionMapper {

    List<ZwfwRegionEntity> selectAll();

    List<ZwfwRegionEntity> selectAllBycode(String param1, String param2);

    List<ZwfwRegionEntity>  selectAllByDept(Integer idx,Integer size);

    /**
     * 查询镇一级
     * @param param1
     * @return
     */
    List<ZwfwRegionEntity> selectTownBycode(String param1);

    /**
     * 查询村一级
     * @param param1
     * @return
     */
    List<ZwfwRegionEntity> selectVillageBycode(String param1);

    /**
     * 关联区域查询部门
     * @param level
     * @param parentId
     * @return
     */
    List<ZwfwRegionEntity> selectDept(String level,String parentId);

}
