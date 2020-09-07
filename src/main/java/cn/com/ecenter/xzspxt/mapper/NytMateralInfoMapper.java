package cn.com.ecenter.xzspxt.mapper;

import cn.com.ecenter.xzspxt.entity.NytAddressInfoEntity;
import cn.com.ecenter.xzspxt.entity.NytMateralInfoEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
@DS(value = "base")
public interface NytMateralInfoMapper extends BaseMapper<NytMateralInfoEntity> {

    void add(List<NytMateralInfoEntity> list);

    List<NytMateralInfoEntity> selectPID(String pId);
}
