package cn.com.ecenter.xzspxt.mapper;

import cn.com.ecenter.xzspxt.entity.NytAddressInfoEntity;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
@DS(value = "base")
public interface NytQlsxMapper extends BaseMapper<NytQlsxEntity> {

    void addList(List<NytQlsxEntity> nytQlsx);
}
