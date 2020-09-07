package cn.com.ecenter.pre.controller;

import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.service.PhaseService;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.system.service.IDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 办件主表相关操作
 */
@Slf4j
@RestController
@RequestMapping("phase")
@RequiredArgsConstructor
@CrossOrigin
public class PhaseController extends BaseController {

    private final PhaseService phaseService;

    private final IDeptService deptService;

    /**
     * 添加办理阶段信息
     * @param apasinfo 申报信息
     * @param handlestart 办件状体
     * @param phaseCode 办件阶段
     * @return
     */
    @PostMapping("save")
    public FebsResponse update(ApasinfoEntity apasinfo, String handlestart, String phaseCode) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Dept dept = deptService.selectById(user.getDeptId()+"").get(0);
        phaseService.save(apasinfo, handlestart, phaseCode, dept);
        return new FebsResponse().success();
    }
}
