package cn.com.ecenter.pre.controller;

import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.service.AcceptService;
import cn.com.ecenter.pre.service.TransactService;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.system.service.IDeptService;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
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
import java.util.Map;

/**
 * 办结信息表
 */
@Slf4j
@RestController
@RequestMapping("transact")
@RequiredArgsConstructor
public class TransactController extends BaseController {

    private final TransactService transactService;


    /**
     * 办结点击事件修改办件状态，添加阶段信息和办结信息
     *
     * @param apasinfo
     * @param phaseCode
     * @return
     */
    @PostMapping("updteAndSave")
    public FebsResponse updteAndSave(
            @RequestParam(value = "apasinfo", required = false) String apasinfo,
            @RequestParam(value = "phaseCode", required = false) String phaseCode,
            @RequestParam(value = "nodeIndex", required = false) Integer nodeIndex,
            @RequestParam(value = "opinion", required = false) String opinion,
            @RequestParam(value = "nodeStartTime", required = false) String nodeStartTime) {
        ApasinfoEntity apasin = JSON.parseObject(apasinfo, new TypeReference<ApasinfoEntity>() {
        });
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("phaseCode", phaseCode);
        paramMap.put("nodeIndex", nodeIndex);
        paramMap.put("opinion", opinion);
        paramMap.put("nodeStartTime", nodeStartTime);

        return transactService.updteAndSave(apasin, paramMap);
    }


}
