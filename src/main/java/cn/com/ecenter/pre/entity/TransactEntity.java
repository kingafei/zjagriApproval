package cn.com.ecenter.pre.entity;

import lombok.Data;

import java.util.Date;

/**
 * 办结信息
 */
@Data
public class TransactEntity {
    /**
     * 申报号
     */
    private String projid;
    /**
     * 办结人员姓名
     */
    private String transactUser;
    /**
     * 办结人所属部门名称
     */
    private String handerDeptname;
    /**
     * 办结人所属部门编码
     */
    private String handerDeptid;
    /**
     * 办结人所属部门的所在行政区划编码
     */
    private String areacode;
    /**
     *
     */
    private String transactTime;
    /**
     * 办理结果
     */
    private String transactResult;
    /**
     * 办理结果描述
     */
    private String transactDescribe;
    /**
     * 许可机关统一社会信用代码
     */
    private String xkXkjgdm;
    /**
     * 行政许可决定文书名称
     */
    private String xkXkws;
    /**
     * 许可证书名称
     */
    private String xkXkzs;
    /**
     * 有效期自
     */
    private String xkYxqz;
    /**
     * 有效期至
     */
    private String xkYxqzi;
    /**
     * 结果编号
     */
    private String resultCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 所属系统对接编码
     */
    private String belongsystem;
    /**
     * 结果证照名称
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
