package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.NytFlowInfoEntity;
import cn.com.ecenter.xzspxt.mapper.NytFlowInfoMapper;
import cn.com.ecenter.xzspxt.service.NytFlowInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NytFlowInfoServiceImpl implements NytFlowInfoService {
    @Autowired
    private NytFlowInfoMapper nytFlowInfoMapper;

    @Override
    public void add(List<NytFlowInfoEntity> list) {
        nytFlowInfoMapper.add(list);
    }

    @Override
    public List<NytFlowInfoEntity> selectByPid(String tongId) {
        return nytFlowInfoMapper.selectByPid(tongId);
    }

    @Override
    public List<NytFlowInfoEntity> getOrderActivity(String tongId) {
        return getOrderActivity(nytFlowInfoMapper.selectByPid(tongId));
    }

    public List<NytFlowInfoEntity> getOrderActivity(List<NytFlowInfoEntity> list) {
        if (null == list || list.size() == 0) {
            return list;
        }
        List<NytFlowInfoEntity> resultList = new ArrayList<NytFlowInfoEntity>();
        NytFlowInfoEntity source = new NytFlowInfoEntity();
        for (NytFlowInfoEntity nf : list) {
            if (StringUtils.isBlank(nf.getSourceActivityId())) {
                return resultList;
            }
            boolean temp = true;
            for (NytFlowInfoEntity nfi : list) {
                if (nf.getSourceActivityId().equals(nfi.getTargetActivityId())) {
                    temp = false;
                    break;
                }
            }
            if (temp) {
                source = nf;
            }
        }
        resultList.add(source);
        for (NytFlowInfoEntity nf : list) {
            for (NytFlowInfoEntity nfi : list) {
                if (source.getTargetActivityId().equals(nfi.getSourceActivityId())) {
                    source = nfi;
                    resultList.add(nfi);
                    break;
                }
            }
        }
        return resultList;
    }
}
