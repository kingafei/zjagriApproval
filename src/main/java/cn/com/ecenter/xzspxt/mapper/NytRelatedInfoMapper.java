package cn.com.ecenter.xzspxt.mapper;

import cn.com.ecenter.xzspxt.entity.NytQaInfoEntity;
import cn.com.ecenter.xzspxt.entity.NytRelatedInfoEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
@DS(value = "base")
public interface NytRelatedInfoMapper extends BaseMapper<NytRelatedInfoEntity> {

    void add(List<NytRelatedInfoEntity> list);
}
