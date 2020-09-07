package cn.com.ecenter.pre.mapper;


import cn.com.ecenter.pre.entity.FormFileEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@DS(value = "base")
public interface FormFileMapper {

    /**
     * 根据材料表的uuid查询相关附件
     * @param uudi
     * @return
     */
    List<FormFileEntity> getByUUID(String uudi);

    void updateByUNID(FormFileEntity file);

    void deleteByUUID(String unid);

    void insertSelective(FormFileEntity file);

    List<FormFileEntity> getById(@Param("list") String[] list);

}
