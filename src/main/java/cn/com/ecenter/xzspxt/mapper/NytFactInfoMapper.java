package cn.com.ecenter.xzspxt.mapper;

import cn.com.ecenter.xzspxt.entity.NytFactInfoEntity;
import cn.com.ecenter.xzspxt.entity.NytQaInfoEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
@DS(value = "base")
public interface NytFactInfoMapper extends BaseMapper<NytFactInfoEntity> {

    void add(List<NytFactInfoEntity> list);
}
