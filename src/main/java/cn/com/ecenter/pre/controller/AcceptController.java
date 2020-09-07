package cn.com.ecenter.pre.controller;

import cn.com.ecenter.common.controller.BaseController;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.service.AcceptService;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 受理信息表
 */
@Slf4j
@RestController
@RequestMapping("accept")
@RequiredArgsConstructor
@CrossOrigin // 接口跨域
public class AcceptController extends BaseController {

    private final AcceptService acceptService;

    private final NytQlsxService nytQlsxService;

    private final IDeptService deptService;

    /**
     * 添加受理信息
     * @param apasinfo 申报信息
     * @return
     */
    @PostMapping("save")
    public FebsResponse save(ApasinfoEntity apasinfo) {
        NytQlsxEntity qlsx = nytQlsxService.selByTongId(apasinfo.getTongid() + "");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Dept dept = deptService.selectById(user.getDeptId()+"").get(0);
        acceptService.save(apasinfo, qlsx, user, dept);
        return new FebsResponse().success();
    }


    /**
     * 受理点击事件保存办件信息，材料信息，添加阶段信息和受理信息
     * @param apasinfo
     * @param tableData
     * @param handlestart
     * @param phaseCode
     * @return
     */
    @PostMapping("accept")
    public FebsResponse acceppt(
            @RequestParam(value ="apasinfo", required=false) String apasinfo,
            @RequestParam(value ="tableData", required=false) String tableData,
            @RequestParam(value ="handlestart", required=false) String handlestart,
            @RequestParam(value ="phaseCode", required=false) String phaseCode) {
        ApasinfoEntity apasin = JSON.parseObject(apasinfo, new TypeReference<ApasinfoEntity>(){});
        ArrayList<AttrEntity> attrList = JSON.parseObject(tableData, new TypeReference<ArrayList<AttrEntity>>(){});

        return  acceptService.acceppt(apasin, attrList, handlestart, phaseCode);
    }

    /**
     * 根据申报号查询受理信息
     * @param projid
     * @return
     */
    @PostMapping("getByProjid")
    public FebsResponse getByProjid(String projid) {
        FebsResponse feb = new FebsResponse();
        feb.data(acceptService.getByProjid(projid));
        return feb;
    }


}
