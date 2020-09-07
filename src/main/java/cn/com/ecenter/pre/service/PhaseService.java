package cn.com.ecenter.pre.service;

import cn.com.ecenter.pre.entity.ApasinfoEntity;
import cn.com.ecenter.system.entity.Dept;

public interface PhaseService {
    /**
     * 添加阶段信息
     * @param apasinfo 申报信息
     * @param handlestart 办件状态
     * @param phaseCode 阶段编码
     * @param dept 部门
     */
    void  save(ApasinfoEntity apasinfo, String handlestart, String phaseCode, Dept dept);

}
