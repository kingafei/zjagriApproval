package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytChargeitemInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytChargeitemInfoMapper;
import cn.com.ecenter.xzspxt.service.NytChargeitemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytChargeitemInfoServiceImpl implements NytChargeitemInfoService {

    @Autowired
    private NytChargeitemInfoMapper nytChargeitemInfoMapper;

    @Override
    public void add(List<NytChargeitemInfoEntity> list) {
        nytChargeitemInfoMapper.add(list);
    }
}
