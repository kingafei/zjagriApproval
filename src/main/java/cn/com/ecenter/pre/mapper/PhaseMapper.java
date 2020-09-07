package cn.com.ecenter.pre.mapper;


import cn.com.ecenter.pre.entity.PhaseEntity;
import com.baomidou.dynamic.datasource.annotation.DS;


@DS(value = "base")
public interface PhaseMapper {

    void  save(PhaseEntity phase);


}
