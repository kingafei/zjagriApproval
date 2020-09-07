package cn.com.ecenter.pre.mapper;


import cn.com.ecenter.pre.entity.AcceptEntity;
import com.baomidou.dynamic.datasource.annotation.DS;


@DS(value = "base")
public interface AcceptMapper {

    void  save(AcceptEntity accept);

    AcceptEntity getByProjid(String projid);


}
