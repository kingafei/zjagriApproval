package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytFileInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytFileInfoMapper;
import cn.com.ecenter.xzspxt.service.NytFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytFileInfoServiceImpl implements NytFileInfoService {
    @Autowired
    private NytFileInfoMapper nytFileInfoMapper;

    @Override
    public void add(List<NytFileInfoEntity> list) {
        nytFileInfoMapper.add(list);
    }
}
