package cn.com.ecenter.pre.service.impl;

import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.common.utils.DateUtil;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.TransactEntity;
import cn.com.ecenter.pre.mapper.AcceptMapper;
import cn.com.ecenter.pre.mapper.ApasinfoMapper;
import cn.com.ecenter.pre.mapper.TransactMapper;
import cn.com.ecenter.pre.service.NodeService;
import cn.com.ecenter.pre.service.PhaseService;
import cn.com.ecenter.pre.service.TransactService;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactServiceImpl implements TransactService {

    private final TransactMapper transactMapper;

    private final ApasinfoMapper apasinfoMapper;

    private final NytQlsxService nytQlsxService;

    private final PhaseService phaseService;

    private final IDeptService deptService;

    private final NytAreaMapper nytAreaMapper;

    private final NodeService nodeService;

    @Override
    @Transactional
    public void save(ApasinfoEntity apasinfo, NytQlsxEntity qlsx, User user, Dept dept) {
        NytAreaEntity area = nytAreaMapper.queryByAdcode(dept.getAdministrationCode());
        TransactEntity transact = new TransactEntity();
        transact.setProjid(apasinfo.getProjid()); // 申报号
        transact.setTransactUser(user.getUsername()); //办结人员姓名
        transact.setHanderDeptname(area.getName() + "" + user.getDeptName()); //办结人所属部门名称
        transact.setHanderDeptid(dept.getUniqueCoding()); //办结人所属部门编码
        transact.setAreacode(dept.getAdministrationCode()); //办结人所属部门的所在行政区划编码
        String timeStr = DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN);
        transact.setTransactTime(timeStr); //办结日期
        transact.setTransactResult(apasinfo.getHandlestart()); //办理结果
        transact.setTransactDescribe(""); //办理结果描述
        transact.setXkXkjgdm(dept.getUnifiedCreditCode()); //许可机关统一社会信用代码
        transact.setXkXkws(""); //行政许可决定文书名称
        transact.setXkXkzs(qlsx.getBanjianFinishfiles()); //许可证书名称
        transact.setXkYxqz(""); //有效期自
        transact.setXkYxqzi(""); //有效期至
        transact.setResultCode(""); //结果编号
        transact.setCreateTime(timeStr); //数据产生时间
        transact.setSyncStatus("I"); //同步状态
        transact.setDataversion(1); //版本号
        transact.setServicecode(qlsx.getRowguid()); //权力事项编码
        transact.setBelongsystem(""); //所属系统对接编码
        transact.setServiceDeptid(""); //事项终审部门编码
        transact.setXkLydw(""); //数据来源单位
        transact.setXkLydwdm(""); //数据来源单位统一社会信用代码
        transact.setTongid(apasinfo.getTongid());
       transactMapper.save(transact);
    }

    /**
     * 办结点击事件修改办件状态，添加阶段信息和办结信息
     * @param apasinfo
     * @param paramMap
     * @return
     */
    @Override
    @Transactional
    public FebsResponse updteAndSave(ApasinfoEntity apasinfo, Map<String, Object> paramMap) {
        apasinfoMapper.updateByProId(apasinfo);
        NytQlsxEntity qlsx = nytQlsxService.selByTongId(apasinfo.getTongid() + "");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Dept dept = deptService.selectById(user.getDeptId()+"").get(0);
        String phaseCode = (String) paramMap.get("phaseCode");
        save(apasinfo, qlsx, user, dept);
        phaseService.save(apasinfo, apasinfo.getHandlestart(), phaseCode, dept);
        nodeService.nodeNext(apasinfo, paramMap);
        return new FebsResponse().success();
    }

}
