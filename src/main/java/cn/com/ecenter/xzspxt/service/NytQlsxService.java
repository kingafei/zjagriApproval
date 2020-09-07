package cn.com.ecenter.xzspxt.service;

import cn.com.ecenter.common.entity.QueryRequest;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface NytQlsxService {
    void add(NytQlsxEntity nytQlsx);


    <T> IPage<NytQlsxEntity> selectByUserId(Map map, QueryRequest request);

    NytQlsxEntity selByTongId(String tongid);
}
