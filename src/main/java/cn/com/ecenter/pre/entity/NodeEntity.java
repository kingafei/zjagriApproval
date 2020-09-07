package cn.com.ecenter.pre.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 环节信息
 */
@Data
public class NodeEntity {
    /**
     * 唯一标识
     */
    private String unid;
    /**
     * 申报号
     */
    private String projid;
    /**
     * 业务动作
     */
    private String action;
    /**
     * 办理环节名称
     */
    private String nodeName;
    /**
     * 审批人姓名
     */
    private String name;
    /**
     * 办理人所属部门名称
     */
    private String handerDeptname;
    /**
     * 办理人所属部门编码
     */
    private String handerDeptid;
    /**
     * 办理人所属部门的所在行政区划编码
     */
    private String areacode;
    /**
     * 审批意见
     */
    private String opinion;
    /**
     * 环节开始时间
     */
    private String startTime;
    /**
     * 环节结束时间
     */
    private String endTime;
    /**
     * 备注
     */
    private String remark;
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


    private List<FormFileEntity> fileList;
}
