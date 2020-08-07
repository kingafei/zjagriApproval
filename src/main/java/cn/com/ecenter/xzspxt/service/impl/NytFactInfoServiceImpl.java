package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytFactInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytFactInfoMapper;
import cn.com.ecenter.xzspxt.service.NytFactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytFactInfoServiceImpl implements NytFactInfoService {

    @Autowired
    private NytFactInfoMapper nytFactInfoMapper;

    @Override
    public void add(List<NytFactInfoEntity> list) {
        nytFactInfoMapper.add(list);
    }
}
