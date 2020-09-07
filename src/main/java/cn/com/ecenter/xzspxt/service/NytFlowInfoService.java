package cn.com.ecenter.xzspxt.service;

import cn.com.ecenter.xzspxt.entity.NytFlowInfoEntity;

import java.util.List;

public interface NytFlowInfoService {
    void add(List<NytFlowInfoEntity> list);

    List<NytFlowInfoEntity> selectByPid(String tongId);

    List<NytFlowInfoEntity> getOrderActivity(String tongId);
}
