package cn.com.ecenter.pre.mapper;


import cn.com.ecenter.pre.entity.NodeEntity;
import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.List;


@DS(value = "base")
public interface NodeMapper {

    void save(NodeEntity node);

    List<NodeEntity> getByProjid(String projid);


}
