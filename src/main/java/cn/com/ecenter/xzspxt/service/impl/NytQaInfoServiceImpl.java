package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytQaInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytQaInfoMapper;
import cn.com.ecenter.xzspxt.service.NytQaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytQaInfoServiceImpl implements NytQaInfoService {
    @Autowired
    private NytQaInfoMapper nytQaInfoMapper;

    @Override
    public void add(List<NytQaInfoEntity> list) {
        nytQaInfoMapper.add(list);
    }
}
