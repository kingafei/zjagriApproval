package cn.com.ecenter.pre.mapper;


import cn.com.ecenter.pre.entity.TransactEntity;
import com.baomidou.dynamic.datasource.annotation.DS;


@DS(value = "base")
public interface TransactMapper {

    void  save(TransactEntity accept);



}
