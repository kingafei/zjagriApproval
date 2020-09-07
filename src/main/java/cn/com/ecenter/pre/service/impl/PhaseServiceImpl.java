package cn.com.ecenter.pre.service.impl;

import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.pre.entity.PhaseEntity;
import cn.com.ecenter.pre.mapper.PhaseMapper;
import cn.com.ecenter.pre.service.PhaseService;
import cn.com.ecenter.system.entity.Dept;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhaseServiceImpl implements PhaseService {

    private final PhaseMapper phaseMapper;

    /**
     * 添加阶段信息
     * @param apasinfo 申报信息
     * @param handlestart 办件状态
     * @param phaseCode 阶段编码
     * @param dept 部门
     */
    @Override
    @Transactional
    public void save(ApasinfoEntity apasinfo, String handlestart, String phaseCode, Dept dept) {
        PhaseEntity phase = new PhaseEntity();
        phase.setUnid(UUID.randomUUID().toString().replaceAll("-", ""));
        phase.setProjid(apasinfo.getProjid());
        phase.setPhaseCode(phaseCode);
        phase.setHandlestart(handlestart);
        phase.setNodeName(""); // 环节名称
        phase.setBelongsystem(""); //所属系统对接编码
        phase.setAreacode(dept.getAdministrationCode()); //当前环节所属行政区划编码
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
}
