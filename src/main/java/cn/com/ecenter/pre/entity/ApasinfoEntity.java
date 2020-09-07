package cn.com.ecenter.pre.entity;

import lombok.Data;
import java.util.Date;

@Data
public class ApasinfoEntity {

	/**
	 * 申报号
	 */
private String projid;
	/**
	 * 查询密码
	 */
private String projpwd;
	/**
	 * 权力事项的唯一标识,从权力事项库中获取，如果是垂管事项，请填写权力基本码
	 */
private String servicecode;
	/**
	 * 权力事项的基本码,从权力事项库中获取，如：许可-00001-001
	 */
private String servicecodeId;
	/**
	 * 事项终审部门编码
	 */
private String serviceDeptid;
	/**
	 * 办理方式
	 */
private String busMode;
	/**
	 * 办理方式说明
	 */
private String busModeDesc;
	/**
	 * 权力事项版本号
	 */
private Integer serviceversion;
	/**
	 * 权力事项名称
	 */
private String servicename;
	/**
	 * 申报名称
	 */
private String projectname;
	/**
	 * 办件类型
	 */
private String infotype;
	/**
	 * 申报数量
	 */
private String projnum;
	/**
	 * 业务类型
	 */
private String busType;
	/**
	 * 关联业务标识
	 */
private String relBusId;
	/**
	 * 申报者名称
	 */
private String applyname;
	/**
	 * 申报者证件类型
	 */
private String applyCardtype;
	/**
	 * 申报者证件号码
	 */
private String applyCardnumber;
	/**
	 * 联系人/代理人姓名
	 */
private String contactman;
	/**
	 * 联系人/代理人证件类型
	 */
private String contactmanCardtype;
	/**
	 * 联系人/代理人证件号码
	 */
private String contactmanCardnumber;
	/**
	 * 联系人手机号码
	 */
private String telphone;

	/**
	 * 联系人电话号码
	 */
private String dtelphone;
	/**
	 * 邮编
	 */
private String postcode;
	/**
	 * 通讯地址
	 */
private String address;
	/**
	 * 法定代表人
	 */
private String legalman;
	/**
	 * 法定代表人身份证号
	 */
private String xkFrSfzh;
	/**
	 * 收件部门编码
	 */
private String deptid;
	/**
	 * 收件部门名称
	 */
private String deptname;
	/**
	 * 实施机构统一社会信用代码
	 */
private String ssOrgcode;
	/**
	 * 创建用户类型
	 */
private String receiveuseidType;
	/**
	 * 创建用户标识
	 */
private String receiveUseid;
	/**
	 * 创建用户名称
	 */
private String receiveName;
	/**
	 * 申报来源
	 */
private String applyfrom;
	/**
	 * 审批类型
	 */
private String approveType;
	/**
	 * 项目性质
	 */
private String applyPropertiy;
	/**
	 * 申报时间
	 */
private String receivetime;
	/**
	 * 投资项目关联号
	 */
private String belongto;
	/**
	 * 收件部门所属行政区划编码
	 */
private String areacode;
	/**
	 * 数据状态
	 */
private String datastate;
	/**
	 * 所属系统对接编码
	 */
private String belongsystem;
	/**
	 * 法定代表人证件类型
	 */
private String extend;
	/**
	 * 数据产生时间
	 */
private String createTime;
	/**
	 * 数据来源单位
	 */
private String xkLydw;
	/**
	 * 数据来源单位统一社会信用代码
	 */
private String xkLydwdm;
	/**
	 * 同步状态
	 */
private String syncStatus;
	/**
	 * 版本号
	 */
private Integer dataversion;
	/**
	 * op
	 */
private String op;
	/**
	 * tongTime
	 */
private Date tongTime;
	/**
	 * 与事项表关联
	 */
private Long tongid;
	/**
	 * 备用1
	 */
private String extend2;
	/**
	 * 备用2
	 */
private String extend3;
	/**
	 * 备用3
	 */
private String extend4;
	/**
	 * 备用4
	 */
private String extend5;
	/**
	 * 办件状态
	 */
private String handlestart;
	/**
	 * 申报人类型（0个人，1企业，2非企业）
	 */
private String applyType;
	/**
	 * 是否投资
	 */
private String isTouzip;
	/**
	 * 受理时间
	 */
private String acceptTime;
	/**
	 * 承诺期限---受理信息表
	 */
private String promiseDay;
	/**
	 * 期限单位（工作日	1，月	2，年	3，天	4）---受理信息表
	 */
private String anticipateType;
	/**
	 * 承诺办结时间---受理信息表
	 */
private String promiseEtime;

}
