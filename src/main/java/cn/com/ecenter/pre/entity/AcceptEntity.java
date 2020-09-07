package cn.com.ecenter.pre.entity;

import lombok.Data;

import java.util.Date;

/**
 * 受理信息
 */
@Data
public class AcceptEntity {
    /**
     * 申报号
     */
    private String projid;
    /**
     * 受理人员
     */
    private String acceptMan;
    /**
     * 受理人员所属部门名称
     */
    private String handerDeptname;
    /**
     * 受理人员所属部门编码
     */
    private String handerDeptid;
    /**
     * 受理人员所属部门的所在行政区划编码
     */
    private String areacode;
    /**
     * 受理时间
     */
    private String acceptTime;
    /**
     * 承诺期限
     */
    private String promisevalue;
    /**
     * 承诺期限单位
     */
    private String promisetype;
    /**
     * 承诺办结时间
     */
    private String promiseEtime;
    /**
     * 所属系统对接编码
     */
    private String belongsystem;
    /**
     * 备用字段
     */
    private String extend;
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
