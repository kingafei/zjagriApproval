package cn.com.ecenter.pre.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AttrEntity {

    /**
     * 唯一标识
     */
    private String unid;
    /**
     * 申报号
     */
    private String projid;
    /**
     * 材料名称
     */
    private String attrname;
    /**
     * 材料标识
     */
    private String attrid;
    /**
     * 材料序号
     */
    private String sortid;
    /**
     * 收取方式
     */
    private String taketype;
    /**
     * 是否收取
     */
    private String istake;
    /**
     * 收取数量
     */
    private String amount;
    /**
     * 收取时间
     */
    private String taketime;
    /**
     * 备注
     */
    private String memo;
    /**
     * 所属系统对接编码
     */
    private String belongsystem;
    /**
     * 事项终审部门行政区划编码
     */
    private String areacode;
    /**
     * 备用字段
     */
    private String extend;
    /**
     * 数据产生时间
     */
    private String createTime;
    /**
     * 附件存储路径
     */
    private String fileurl;
    /**
     * 附件名称
     */
    private String filename;
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

    private List<FormFileEntity> list = new ArrayList<>();

}
