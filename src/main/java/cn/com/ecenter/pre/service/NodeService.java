package cn.com.ecenter.pre.service;

import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.entity.NodeEntity;

import java.util.List;
import java.util.Map;

public interface NodeService {

    public void save(ApasinfoEntity accept, Map<String, Object> paramMap);

    public FebsResponse node(ApasinfoEntity apasinfo, List<AttrEntity> tableData, Map<String, Object> paramMap);

    public FebsResponse nodeNext(ApasinfoEntity apasinfo, Map<String, Object> paramMap);

    public List<NodeEntity> getByProjid(String projid);

}
