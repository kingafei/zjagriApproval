package cn.com.ecenter.xzspxt.entity;

import java.math.BigDecimal;
import lombok.Data;
import java.util.Date;

@Data
public class NytDepartEntity {

	/**
	 * 部门id,主键自增
	 */
private Integer id;
	/**
	 * 地址
	 */
private String address;
	/**
	 * 创建人标识id
	 */
private Integer fkCreateUid;
	/**
	 * 创建人姓名
	 */
private String createName;
	/**
	 * 负责人id
	 */
private Integer fkManagerUid;
	/**
	 * 负责人姓名
	 */
private String managerName;
	/**
	 * 组织名称
	 */
private String name;
	/**
	 * 备注说明
	 */
private String remarks;
	/**
	 * 上级部门id
	 */
private String idxParentId;
	/**
	 * 部门简称
	 */
private String shortName;
	/**
	 * 排序
	 */
private BigDecimal sort;
	/**
	 * 状态，0：有效、1：注销
	 */
private Integer state;
	/**
	 * 电话
	 */
private String telephone;
	/**
	 * 区号
	 */
private String areaCode;
	/**
	 * 邮编
	 */
private String zipCode;
	/**
	 * 组织oid
	 */
private String organizationOid;
	/**
	 * 统一信用代码
	 */
private String unifiedCreditCode;
	/**
	 * 原组织编码
	 */
private String orgLevel;
	/**
	 * 组织唯一编码
	 */
private String uniqueCoding;
	/**
	 * 行政区划编码+部门序号
	 */
private String administrationCode;
	/**
	 * 行政等级
	 */
private BigDecimal administrationLevel;
	/**
	 * 0正常/1删除
	 */
private Integer isDelete;
	/**
	 * 创建时间
	 */
private Date createTime;
	/**
	 * 更新时间
	 */
private Date updateTime;
	/**
	 * 备用1
	 */
private String bak1;
	/**
	 * 备用2
	 */
private String bak2;
	/**
	 * 备用3
	 */
private String bak3;
	/**
	 * 备用4
	 */
private String bak4;

}
