package cn.com.ecenter.pre.mapper;


import cn.com.ecenter.pre.entity.AttrEntity;
import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.List;


@DS(value = "base")
public interface AttrMapper {
    /**
     * 根据申报号查询相关的申报材料
     * @param projid
     * @return
     */
    List<AttrEntity> getById(String projid);

    /**
     * 根据事项表关联id查询相关的申报材料
     * @param pid
     * @return
     */
    List<AttrEntity> getByPId(String pid, String projid);

    void updateByUNID(AttrEntity attr);

    void insertSelective(AttrEntity attr);

}
