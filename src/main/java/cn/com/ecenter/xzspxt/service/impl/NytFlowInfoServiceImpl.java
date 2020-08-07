package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytFlowInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytFlowInfoMapper;
import cn.com.ecenter.xzspxt.service.NytFlowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytFlowInfoServiceImpl implements NytFlowInfoService {
    @Autowired
    private NytFlowInfoMapper nytFlowInfoMapper;

    @Override
    public void add(List<NytFlowInfoEntity> list) {
        nytFlowInfoMapper.add(list);
    }
}
