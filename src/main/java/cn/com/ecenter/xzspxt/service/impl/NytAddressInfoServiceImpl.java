package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytAddressInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytAddressInfoMapper;
import cn.com.ecenter.xzspxt.service.NytAddressInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytAddressInfoServiceImpl implements NytAddressInfoService {

    @Autowired
    private NytAddressInfoMapper nytAddressInfoMapper;

    @Override
    public void add(List<NytAddressInfoEntity> list) {
        nytAddressInfoMapper.add(list);
    }

}
