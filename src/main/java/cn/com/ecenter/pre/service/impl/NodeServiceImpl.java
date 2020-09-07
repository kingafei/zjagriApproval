package cn.com.ecenter.pre.service.impl;

import cn.com.ecenter.common.entity.FebsConstant;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.common.utils.DateUtil;
import cn.com.ecenter.pre.entity.*;
import cn.com.ecenter.pre.mapper.AcceptMapper;
import cn.com.ecenter.pre.mapper.ApasinfoMapper;
import cn.com.ecenter.pre.mapper.FormFileMapper;
import cn.com.ecenter.pre.mapper.NodeMapper;
import cn.com.ecenter.pre.service.AttrService;
import cn.com.ecenter.pre.service.NodeService;
import cn.com.ecenter.pre.service.PhaseService;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.system.service.IDeptService;
import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.entity.NytFlowInfoEntity;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import cn.com.ecenter.xzspxt.mapper.NytAreaMapper;
import cn.com.ecenter.xzspxt.service.NytFlowInfoService;
import cn.com.ecenter.xzspxt.service.NytQlsxService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final NodeMapper nodeMapper;

    private final ApasinfoMapper apasinfoMapper;

    private final AttrService attrService;

    private final NytQlsxService nytQlsxService;

    private final PhaseService phaseService;

    private final IDeptService deptService;

    private final NytFlowInfoService nytFlowInfoService;

    private final NytAreaMapper nytAreaMapper;

    private final AcceptMapper acceptMapper;

    private final FormFileMapper formFileMapper;

    //private final formfile

    /**
     * 添加环节信息
     *
     * @param apasinfo  申报信息
     */
    @Override
    @Transactional
    public void save(ApasinfoEntity apasinfo, Map<String, Object> paramMap) {
        int nodeIndex = (int) paramMap.get("nodeIndex"); // 环节坐标
        String opinion = (String) paramMap.get("opinion"); // 审批意见
        String nodeFile = (String) paramMap.get("nodeFile"); //环节附件id
        String action = (String) paramMap.get("action"); //业务动作
        Long tongId = apasinfo.getTongid();
        AcceptEntity accept = acceptMapper.getByProjid(apasinfo.getProjid());
        List<NytFlowInfoEntity> flow = nytFlowInfoService.getOrderActivity(tongId + "");
        if (flow != null && flow.size() > 0) {
            NytQlsxEntity qlsx = nytQlsxService.selByTongId(apasinfo.getTongid() + "");
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            Dept dept = deptService.selectById(user.getDeptId() + "").get(0);
            NytAreaEntity area = nytAreaMapper.queryByAdcode(dept.getAdministrationCode());
            // 装载环节实体属性值
            NodeEntity node = new NodeEntity();
            node.setUnid(UUID.randomUUID().toString());
            node.setProjid(apasinfo.getProjid()); // 申报号
            if (StringUtils.isBlank(action)) {
                node.setAction(FebsConstant.NODE_SUBMIT); // 业务动作
            } else {
                node.setAction(action); // 业务动作
            }
            node.setNodeName(flow.get(nodeIndex).getNote());   // 办理环节名称
            node.setName(user.getName());   // 审批人姓名
            node.setHanderDeptname(area.getName() + "" + user.getDeptName()); // 办理人所属部门名称
            node.setHanderDeptid(dept.getUniqueCoding());   // 办理人所属部门编码
            node.setAreacode(dept.getAdministrationCode());   // 办理人所属行政区划编码
            node.setOpinion(opinion); // 审批意见
            String timeStr = DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN);
            String nodeStartTime = paramMap.containsKey("nodeStartTime") ? (String) paramMap.get("nodeStartTime") : accept.getAcceptTime();
            node.setStartTime(nodeStartTime); // 环节开始时间
            node.setEndTime(timeStr); // 环节结束时间
            node.setCreateTime(timeStr); // 数据产生时间
            node.setSyncStatus("I"); // 同步状态
            node.setDataversion(1); // 版本号
            node.setServicecode(qlsx.getRowguid()); // 权利事项编码
            node.setTongid(apasinfo.getTongid()); // tongId
            node.setExtend2(nodeFile); // 环节附件id
            node.setBelongsystem(""); // 所属系统对接编码
            node.setServiceDeptid(""); // 权利事项最终审部门编码
            node.setXkLydw(""); // 数据来源单位
            node.setXkLydwdm(""); // 数据来源单位统一社会信用代码
            nodeMapper.save(node);
        }
    }

    /**
     * 办理点击事件保存办件信息，材料信息，添加阶段信息和环节信息
     *
     * @param apasinfo  申报信息
     * @param tableData 材料数据
     * @param paramMap  参数集合
     * @return
     */
    @Override
    @Transactional
    public FebsResponse node(ApasinfoEntity apasinfo, List<AttrEntity> tableData, Map<String, Object> paramMap) {
        String handlestart = (String) paramMap.get("handlestart");
        String phaseCode = (String) paramMap.get("phaseCode");
        apasinfoMapper.updateByProId(apasinfo);
        attrService.update(tableData);
        NytQlsxEntity qlsx = nytQlsxService.selByTongId(apasinfo.getTongid() + "");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Dept dept = deptService.selectById(user.getDeptId() + "").get(0);
        save(apasinfo, paramMap);
        phaseService.save(apasinfo, handlestart, phaseCode, dept);
        return new FebsResponse().success();
    }

    /**
     * 进入环节后每次点击办理添加环节信息
     *
     * @param apasinfo  申报信息
     * @param paramMap  参数集合
     * @return
     */
    @Override
    @Transactional
    public FebsResponse nodeNext(ApasinfoEntity apasinfo, Map<String, Object> paramMap) {
        save(apasinfo,paramMap);
        return new FebsResponse().success();
    }

    @Override
    public List<NodeEntity> getByProjid(String projid) {
        List<NodeEntity> list = nodeMapper.getByProjid(projid);
        for (NodeEntity node : list) {
            if (StringUtils.isNotBlank(node.getExtend2())) {
                String[] fileArr = node.getExtend2().split(",");
                node.setFileList(formFileMapper.getById(fileArr));
            }
        }
        return list;
    }
}
