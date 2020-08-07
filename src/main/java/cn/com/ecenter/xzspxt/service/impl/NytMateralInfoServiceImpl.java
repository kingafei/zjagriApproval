package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytMateralInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytMateralInfoMapper;
import cn.com.ecenter.xzspxt.service.NytMateralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytMateralInfoServiceImpl implements NytMateralInfoService {
    @Autowired
    private NytMateralInfoMapper nytMateralInfoMapper;

    @Override
    public void add(List<NytMateralInfoEntity> list) {
        nytMateralInfoMapper.add(list);
    }
}
