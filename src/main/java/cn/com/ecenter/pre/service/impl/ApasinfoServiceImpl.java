package cn.com.ecenter.pre.service.impl;

import cn.com.ecenter.common.entity.FebsResponse;
import cn.com.ecenter.common.entity.QueryRequest;
import cn.com.ecenter.common.utils.DateUtil;
import cn.com.ecenter.pre.entity.AcceptEntity;
import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.AttrEntity;
import cn.com.ecenter.pre.entity.PhaseEntity;
import cn.com.ecenter.pre.entity.QlsxPreEntity;
import cn.com.ecenter.pre.mapper.*;
import cn.com.ecenter.pre.service.ApasinfoService;
import cn.com.ecenter.pre.service.AttrService;
import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.system.entity.User;
import cn.com.ecenter.xzspxt.entity.NytQlsxEntity;
import cn.com.ecenter.system.mapper.DeptMapper;
import cn.com.ecenter.xzspxt.mapper.NytQlsxMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.micrometer.shaded.org.pcollections.PSet;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApasinfoServiceImpl implements ApasinfoService {

    private final ApasinfoMapper apasinfoMapper;

    @Autowired
    private NytQlsxMapper nytQlsxMapper;

    @Autowired
    private QlsxPreMapper qlsxPreMapper;

    @Autowired
    private PhaseMapper phaseMapper;

    @Autowired
    private DeptMapper deptMappert;


    @Autowired
    private AcceptMapper acceptMapper;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrMapper attrMapper;


    @Override
    public IPage<ApasinfoEntity> getTables(String handlestart, String servicename, String userId, QueryRequest request, String databaseType) {
        Page<ApasinfoEntity> page = new Page<>(request.getPageNum(), request.getPageSize());
        String[] handlestarts = StringUtils.isBlank(handlestart) ? null : handlestart.split(",");
        return apasinfoMapper.getTables(page, handlestarts, servicename, userId, databaseType);
    }

    @Override
    public ApasinfoEntity getById(String projid) {
        return apasinfoMapper.selectByPrimaryKey(projid);
    }

    @Override
    public List<ApasinfoEntity> getByCardId(String cardId) {
        return apasinfoMapper.getByCardId(cardId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(ApasinfoEntity apasinfo) {
        apasinfoMapper.updateByProId(apasinfo);
    }

    @Override
    @Transactional
    public FebsResponse save(ApasinfoEntity apasinfo, ArrayList<AttrEntity> arrs) {

        FebsResponse fb = new FebsResponse();

        Integer v = new Random().nextInt(999999);

        apasinfo.setProjpwd(v.toString());
        apasinfo.setProjid(v.toString());
        apasinfo.setHandlestart("5");
        Integer save = apasinfoMapper.insertSelective(apasinfo);
        if (save == 1) {

//                获取添加子表所需要的对象
            NytQlsxEntity nytQlsxEntity = nytQlsxMapper.selectByTongId(apasinfo.getTongid().toString());
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            Long id = user.getDeptId();
            List<Dept> depts = deptMappert.selectByID(id.toString());
            Dept dept = depts.get(0);
//                  添加材料
            if (arrs != null && arrs.size() > 0) {
                saveAttr(arrs, apasinfo);
            }
//                  添加第三方关联关系
            saveQlsxPre(apasinfo, nytQlsxEntity);
//                  添加阶段等于 收件
            savePhase(apasinfo, "2", "01", user, dept);
//                  添加阶段等于  受理
            savePhase(apasinfo, "5", "02", user, dept);
//                  添加受理表
            saveAccept(apasinfo, user, dept, nytQlsxEntity);
            fb.success().message("添加成功！");
        } else {
            fb.message("添加失败~");
        }
        return fb;
    }

    //    添加 事项 与 申报信息  第三方关联关系表
    @Transactional
    Integer saveQlsxPre(ApasinfoEntity apasinfo, NytQlsxEntity nytQlsxEntity) {

        QlsxPreEntity entity = new QlsxPreEntity();
//        根据tongID查询 事项
        entity.setRowguid(nytQlsxEntity.getRowguid());
        entity.setTongid(apasinfo.getTongid().toString());
        entity.setProjid(apasinfo.getProjid());
        int i = qlsxPreMapper.insertSelective(entity);

        return i;
    }


    //    添加办件阶段
    @Transactional
    void savePhase(ApasinfoEntity apasinfo, String handlestart, String phaseCode, User user, Dept dept) {

        PhaseEntity phase = new PhaseEntity();

        phase.setUnid(UUID.randomUUID().toString().replaceAll("-", ""));
        phase.setProjid(apasinfo.getProjid());
        phase.setPhaseCode(phaseCode);
        phase.setHandlestart(handlestart);
        phase.setNodeName("我是默认值");  // 环节名称

//            设置当前环节所属行政区划编码

        String code = dept.getAdministrationCode();
        phase.setBelongsystem(apasinfo.getBelongsystem());  //所属系统
        phase.setAreacode(code);

        phase.setCreateTime(apasinfo.getCreateTime());
        phase.setSyncStatus(apasinfo.getSyncStatus());
        phase.setDataversion(apasinfo.getDataversion());
        phase.setServicecode(apasinfo.getServicecode());
        phase.setServiceDeptid(apasinfo.getServiceDeptid());
        phase.setXkLydw(apasinfo.getXkLydw());
        phase.setXkLydwdm(apasinfo.getXkLydwdm());
        phase.setTongid(apasinfo.getTongid());
        phaseMapper.save(phase);

    }

    //添加受理信息
    @Transactional
    void saveAccept(ApasinfoEntity apasinfo, User user, Dept dept, NytQlsxEntity qlsx) {
        AcceptEntity accept = new AcceptEntity();


        accept.setProjid(apasinfo.getProjid());//apasinfo.getProjid()
        accept.setAcceptMan(user.getName());
        accept.setHanderDeptname(user.getDeptName());
        accept.setHanderDeptid(dept.getUniqueCoding());
        accept.setAreacode(dept.getAdministrationCode());
        String timeStr = DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN);
        accept.setAcceptTime(timeStr);
        accept.setPromisevalue(qlsx.getPromiseDay() + "");
        accept.setPromisetype(qlsx.getAnticipateType());
        accept.setPromiseEtime("");// 承诺办结时间
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
     * 修改办件信息，材料信息
     *
     * @param apasinfo
     * @param tableData
     * @return
     */
    @Override
    @Transactional
    public FebsResponse updateAll(ApasinfoEntity apasinfo, List<AttrEntity> tableData) {
        apasinfoMapper.updateByProId(apasinfo);
        attrService.update(tableData);
        return new FebsResponse().success();
    }


    @Transactional
    public void saveAttr(List<AttrEntity> list, ApasinfoEntity apasinfo) {
        for (AttrEntity attr : list) {
            if (null != attr.getUnid()) {
                attr.setUnid(UUID.randomUUID().toString());
                attr.setProjid(apasinfo.getProjid());
                attr.setAttrid("");//材料标识
                attr.setTaketime(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));

                attr.setBelongsystem("");//  所属系统对接编码
                attr.setAreacode(apasinfo.getAreacode());
                attr.setCreateTime(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
                attr.setDataversion(1);
                attr.setServicecode(apasinfo.getServicecode());
                attr.setServiceDeptid(apasinfo.getServiceDeptid());
                attrMapper.insertSelective(attr);

            }
        }
    }
}
