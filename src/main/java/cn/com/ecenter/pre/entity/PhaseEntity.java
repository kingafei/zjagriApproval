package cn.com.ecenter.pre.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PhaseEntity {

    /**
     * 唯一标识
     */
    private String unid;
    /**
     * 申报号
     */
    private String projid;
    /**
     * 阶段编号
     */
    private String phaseCode;
    /**
     * 办件状态
     */
    private String handlestart;
    /**
     * 环节名称
     */
    private String nodeName;
    /**
     * 所属系统对接编码
     */
    private String belongsystem;
    /**
     * 当前环节所属行政区划编码
     */
    private String areacode;
    /**
     * 数据产生时间
     */
    private String createTime;
    /**
     * 同步状态
     */
    private String syncStatus;
    /**
     * 版本号
     */
    private Integer dataversion;
    /**
     * 权力事项编码
     */
    private String servicecode;
    /**
     * 事项终审部门编码
     */
    private String serviceDeptid;
    /**
     * 数据来源单位
     */
    private String xkLydw;
    /**
     * 数据来源单位统一社会信用代码
     */
    private String xkLydwdm;
    /**
     * 备用字段2
     */
    private String extend2;
    /**
     * 备用字段3
     */
    private String extend3;
    /**
     * 备用字段4
     */
    private String extend4;
    /**
     * 备用字段5
     */
    private String extend5;
    /**
     *
     */
    private String op;
    /**
     *
     */
    private Date tongTime;
    /**
     *
     */
    private Long tongid;

}
