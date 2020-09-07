package cn.com.ecenter.pre.service;

import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.pre.entity.AcceptEntity;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;

import java.util.List;
import java.util.Map;

public interface TransactService {

    public void  save(ApasinfoEntity accept, NytQlsxEntity qlsx, User user, Dept dept);

    public FebsResponse updteAndSave(ApasinfoEntity apasinfo, Map<String, Object> paramMap );

}
