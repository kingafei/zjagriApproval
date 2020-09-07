package cn.com.ecenter.pre.controller;

import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.entity.NodeEntity;
import cn.com.ecenter.pre.service.AcceptService;
import cn.com.ecenter.pre.service.NodeService;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.system.service.IDeptService;
import cn.com.ecenter.xzspxt.entity.NytFlowInfoEntity;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import cn.com.ecenter.xzspxt.service.NytFlowInfoService;
import cn.com.ecenter.xzspxt.service.NytQlsxService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 环节信息表
 */
@Slf4j
@RestController
@RequestMapping("node")
@RequiredArgsConstructor
public class NodeController extends BaseController {

    private final NodeService nodeService;

    private final NytQlsxService nytQlsxService;

    private final IDeptService deptService;

    private final NytFlowInfoService nytFlowInfoService;




    /**
     * 办理点击事件保存办件信息，材料信息，添加阶段信息和环节信息
     *
     * @param apasinfo    申报信息
     * @param tableData   材料数据
     * @param handlestart 办件状态
     * @param phaseCode   办件阶段
     * @param nodeIndex   环节坐标
     * @param opinion   审批意见
     * @param action 业务动作
     * @return
     */
    @PostMapping("node")
    public FebsResponse node(
            @RequestParam(value = "apasinfo", required = false) String apasinfo,
            @RequestParam(value = "tableData", required = false) String tableData,
            @RequestParam(value = "handlestart", required = false) String handlestart,
            @RequestParam(value = "phaseCode", required = false) String phaseCode,
            @RequestParam(value = "nodeIndex", required = false) Integer nodeIndex,
            @RequestParam(value = "nodeFile", required = false) String nodeFile,
            @RequestParam(value = "opinion", required = false) String opinion,
            @RequestParam(value = "action", required = false) String action) {
        ApasinfoEntity apasin = JSON.parseObject(apasinfo, new TypeReference<ApasinfoEntity>() {
        });
        ArrayList<AttrEntity> attrList = JSON.parseObject(tableData, new TypeReference<ArrayList<AttrEntity>>() {
        });
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("handlestart", handlestart);
        paramMap.put("phaseCode", phaseCode);
        paramMap.put("nodeIndex", nodeIndex);
        paramMap.put("opinion", opinion);
        paramMap.put("nodeFile", nodeFile);
        paramMap.put("action", action);
        return nodeService.node(apasin, attrList, paramMap);
    }


    /**
     * 进入环节后每次点击办理添加环节信息
     *
     * @param apasinfo    申报信息
     * @param nodeIndex   环节坐标
     * @param opinion   审批意见
     * @param nodeStartTime 当前环节开始时间（上个环节结束时间)
     * @param action 业务动作
     * @return
     */
    @PostMapping("nodeNext")
    public FebsResponse nodeNext(
            @RequestParam(value = "apasinfo", required = false) String apasinfo,
            @RequestParam(value = "nodeIndex", required = false) Integer nodeIndex,
            @RequestParam(value = "opinion", required = false) String opinion,
            @RequestParam(value = "nodeFile", required = false) String nodeFile,
            @RequestParam(value = "nodeStartTime", required = false) String nodeStartTime,
            @RequestParam(value = "action", required = false) String action) {
        ApasinfoEntity apasin = JSON.parseObject(apasinfo, new TypeReference<ApasinfoEntity>() {
        });
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nodeIndex", nodeIndex);
        paramMap.put("opinion", opinion);
        paramMap.put("nodeFile", nodeFile);
        paramMap.put("nodeStartTime", nodeStartTime);
        paramMap.put("action", action);
        return nodeService.nodeNext(apasin, paramMap);
    }

    /**
     * 根据申报号环节信息
     * @param tongid
     * @param projid
     * @return
     */
    @PostMapping("getByProjid")
    public FebsResponse getByProjid(String tongid, String projid) {
        FebsResponse feb = new FebsResponse();
        List<NodeEntity> nodes = nodeService.getByProjid(projid);
        List<NytFlowInfoEntity> flows = nytFlowInfoService.getOrderActivity(tongid);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("nodes",nodes );
        resultMap.put("flows", flows);
        feb.data(resultMap);
        return feb;
    }


}
