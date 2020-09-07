package cn.com.ecenter.pre.service.impl;

import cn.com.ecenter.common.entity.FebsConstant;
import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.common.utils.DateUtil;
import cn.com.ecenter.pre.entity.AcceptEntity;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.mapper.AcceptMapper;
import cn.com.ecenter.pre.mapper.ApasinfoMapper;
import cn.com.ecenter.pre.service.AcceptService;
import cn.com.ecenter.pre.service.AttrService;
import cn.com.ecenter.pre.service.PhaseService;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.system.mapper.DeptMapper;
import cn.com.ecenter.system.service.IDeptService;
import cn.com.ecenter.xzspxt.entity.NytAreaEntity;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import cn.com.ecenter.xzspxt.mapper.NytAreaMapper;
import cn.com.ecenter.xzspxt.service.NytQlsxService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AcceptServiceImpl implements AcceptService {

    private final AcceptMapper acceptMapper;

    private final ApasinfoMapper apasinfoMapper;

    private final AttrService attrService;

    private final NytQlsxService nytQlsxService;

    private final PhaseService phaseService;

    private final IDeptService deptService;

    private final NytAreaMapper nytAreaMapper;

    @Override
    @Transactional
    public void save(ApasinfoEntity apasinfo, NytQlsxEntity qlsx, User user, Dept dept) {
        NytAreaEntity area = nytAreaMapper.queryByAdcode(dept.getAdministrationCode());
        AcceptEntity accept = new AcceptEntity();
        accept.setProjid(apasinfo.getProjid());
        accept.setAcceptMan(user.getName());
        accept.setHanderDeptname(area.getName() + "" + user.getDeptName());
        accept.setHanderDeptid(dept.getUniqueCoding());
        accept.setAreacode(dept.getAdministrationCode());
        String timeStr = DateUtil.getDateFormat(new Date(),DateUtil.FULL_TIME_SPLIT_PATTERN);
        accept.setAcceptTime(timeStr);
        accept.setPromisevalue(qlsx.getPromiseDay() + "");
        accept.setPromisetype(qlsx.getAnticipateType());
        accept.setPromiseEtime(DateUtil.getAfterDate(new Date(), apasinfo.getAnticipateType(),Integer.parseInt(apasinfo.getPromiseDay())));// 承诺办结时间
        accept.setBelongsystem(""); // 所属系统对接编码
        accept.setCreateTime(timeStr);
        accept.setSyncStatus("I");
        accept.setDataversion(1);
        accept.setServicecode(qlsx.getRowguid());
        accept.setServiceDeptid(""); //事项终审部门编码
        accept.setXkLydw(""); //数据来源单位
        accept.setXkLydwdm(""); //数据来源单位统一社会信用代码
        accept.setTongid(apasinfo.getTongid());
        acceptMapper.save(accept);
    }

    /**
     * 受理点击事件保存办件信息，材料信息，添加阶段信息和受理信息
     * @param apasinfo
     * @param tableData
     * @param handlestart
     * @param phaseCode
     * @return
     */
    @Override
    @Transactional
    public FebsResponse acceppt(ApasinfoEntity apasinfo, List<AttrEntity> tableData, String handlestart, String phaseCode) {
        apasinfoMapper.updateByProId(apasinfo);
        attrService.update(tableData);
        NytQlsxEntity qlsx = nytQlsxService.selByTongId(apasinfo.getTongid() + "");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Dept dept = deptService.selectById(user.getDeptId()+"").get(0);
        save(apasinfo, qlsx, user, dept);
        phaseService.save(apasinfo, handlestart, phaseCode, dept);
        return new FebsResponse().success();
    }

    @Override
    public AcceptEntity getByProjid(String projid) {
        return acceptMapper.getByProjid(projid);
    }
}
