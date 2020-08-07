package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import cn.com.ecenter.xzspxt.mapper.NytQlsxMapper;
import cn.com.ecenter.xzspxt.service.NytQlsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NytQlsxServiceImpl implements NytQlsxService {
    @Autowired
    private NytQlsxMapper nytQlsxMapper;

    @Override
    public void addList(List<NytQlsxEntity> nytQlsx) {
        nytQlsxMapper.addList(nytQlsx);
    }
}
