package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytRelatedInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytRelatedInfoMapper;
import cn.com.ecenter.xzspxt.service.NytRelatedInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytRelatedInfoServiceImpl implements NytRelatedInfoService {
    @Autowired
    private NytRelatedInfoMapper nytRelatedInfoMapper;

    @Override
    public void add(List<NytRelatedInfoEntity> list) {
        nytRelatedInfoMapper.add(list);
    }
}
