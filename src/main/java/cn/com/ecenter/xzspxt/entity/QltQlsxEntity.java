package cn.com.ecenter.xzspxt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import lombok.Data;
import java.util.Date;

@Data
@TableName("qlt_qlsx")
@Excel("权利事项主表")
public class QltQlsxEntity {

	/**
	 * 权力唯一标识
	 */
private String rowguid;
	/**
	 * 写入同步时间
	 */
private Date updateDate;
	/**
	 * 权力更新类型
	 */
private String updateType;
	/**
	 * 权力事项类型
	 */
private String qlKind;
	/**
	 * 权力属性
	 */
private String qlAtt;
	/**
	 * 权力编码
	 */
private String qlFullId;
	/**
	 * 权力主项编码
	 */
private String qlMainitemId;
	/**
	 * 权力子项编码
	 */
private String qlSubitemId;
	/**
	 * 事权层级
	 */
private String shiquancj;
	/**
	 * 行政区划代码
	 */
private String belongxiaqucode;
	/**
	 * 实施机关组织机构代码
	 */
private String qlDepOrgcdoe;
	/**
	 * 部门组织机构代码
	 */
private String ouorgcode;
	/**
	 * 部门编码
	 */
private String ouguid;
	/**
	 * 权力来源
	 */
private String itemsource;
	/**
	 * 权力来源方式
	 */
private String itemsourcetype;
	/**
	 * 委托下放
	 */
private String entrust;
	/**
	 * 委托下放说明
	 */
private String entrustdes;
	/**
	 * 版本号
	 */
private String versionNumber;
	/**
	 * 权力入库时间
	 */
private Date versionDate;
	/**
	 * 权力状态生效时间
	 */
private Date qlEffectTime;
	/**
	 * 权力内部编码
	 */
private String qlInnerCode;
	/**
	 * 
	 */
private String qlName;
	/**
	 * 权力状态
	 */
private String qlState;
	/**
	 * 法定依据
	 */
private String lawbasis;
	/**
	 * 法定期限
	 */
private Integer anticipateDay;
	/**
	 * 法定期限单位
	 */
private String anticipateType;
	/**
	 * 承诺期限
	 */
private Integer promiseDay;
	/**
	 * 征收标准
	 */
private String feebasis;
	/**
	 * 是否涉及征收（税）费减免的审批
	 */
private String isLevywaiver;
	/**
	 * 办事者到办事地点最少次数
	 */
private Integer applyerminCount;
	/**
	 * 是否列为国家、省、市、县级行政审批制度改革试点
	 */
private String isPilot;
	/**
	 * 是否可以适用处罚简易程序
	 */
private String isSimplepunish;
	/**
	 * 实施机关
	 */
private String qlDep;
	/**
	 * 受理机构
	 */
private String acpInstitution;
	/**
	 * 决定机构
	 */
private String decInstitution;
	/**
	 * 实施或牵头的处（科）室名称
	 */
private String leadDept;
	/**
	 * 办件类型
	 */
private String bjtype;
	/**
	 * 本级审批性质
	 */
private String benjispxz;
	/**
	 * 办理频率
	 */
private String handleFrequency;
	/**
	 * 涉及的内容
	 */
private String contentInvolve;
	/**
	 * 行政相对人性质
	 */
private String xingzhenxdrxz;
	/**
	 * 适用对象说明
	 */
private String applicableObject;
	/**
	 * 行政相对人权利和义务
	 */
private String xingzhenxdrxy;
	/**
	 * 审批条件
	 */
private String applyCondition;
	/**
	 * 有无数量限制
	 */
private String countLimit;
	/**
	 * 数量限制情况说明
	 */
private String countNote;
	/**
	 * 禁止性要求
	 */
private String banRequirement;
	/**
	 * 事项审查类型
	 */
private String shixiangsctype;
	/**
	 * 事项审查类型说明
	 */
private String shixiangsclx;
	/**
	 * 
	 */
private String banjianFinishfiles;
	/**
	 * 
	 */
private String linkTel;
	/**
	 * 
	 */
private String superviseTel;
	/**
	 * 申请方式
	 */
private String applyType;
	/**
	 * 联系电话
	 */
private String applyTypeTel;
	/**
	 * 邮箱
	 */
private String applyTypeMail;
	/**
	 * 传真
	 */
private String applyTypeFax;
	/**
	 * 办理方式
	 */
private String handleType;
	/**
	 * 
	 */
private String webapplyurl;
	/**
	 * 网上咨询网址
	 */
private String webconsulturl;
	/**
	 * 手机端法人认证地址
	 */
private String mbfarenadd;
	/**
	 * 手机端个人认证标识
	 */
private String mbgerenflag;
	/**
	 * 不适宜开展网上申报
	 */
private String nosuitApply;
	/**
	 * 是否接入统一办件库
	 */
private String isUnifydo;
	/**
	 * 是否是上级主管部门统建系统
	 */
private String isUpunify;
	/**
	 * 涉密或敏感
	 */
private String isCs;
	/**
	 * 极少业务
	 */
private String isVlb;
	/**
	 * 未接入统一办件库其他原因说明
	 */
private String unifydodes;
	/**
	 * 星级服务（网上办理业务模式）
	 */
private String webapplymode;
	/**
	 * 是否收费
	 */
private String chargeFlag;
	/**
	 * 收费依据
	 */
private String chargeBasis;
	/**
	 * 行业主题分类
	 */
private String hangyeclasstype;
	/**
	 * 法人关注点
	 */
private String rightclassQiyezt;
	/**
	 * 面向法人的对象分类
	 */
private String rightclassQiyedx;
	/**
	 * 个人关注点
	 */
private String rightclassGerensx;
	/**
	 * 面向个人的对象分类
	 */
private String rightclassGerendx;
	/**
	 * 其他行政权力子类型
	 */
private String qlSubKind;
	/**
	 * 
	 */
private String imFlowUrl;
	/**
	 * 内部流程信息
	 */
private String inFlowInfo;
	/**
	 * 外部流程图
	 */
private String outFlowUrl;
	/**
	 * 业务申报材料
	 */
private String materialInfo;
	/**
	 * 收费项目
	 */
private String chargeitemInfo;
	/**
	 * 常见问题解答
	 */
private String qaInfo;
	/**
	 * 自由裁量
	 */
private String factInfo;
	/**
	 * 受理地点信息
	 */
private String acceptAddressInfo;
	/**
	 * 
	 */
private String farenurl;
	/**
	 * 送达方式
	 */
private String serviceMode;
	/**
	 * 送达时限
	 */
private String serviceDay;
	/**
	 * 时限说明
	 */
private String destime;
	/**
	 * 
	 */
private String gerenflag;
	/**
	 * 是否统建系统
	 */
private String isTongjian;
	/**
	 * 乡镇延伸事项的部门源事项
	 */
private String qlInnerCodeItem;
	/**
	 * 最少上门次数说明
	 */
private String applyerminCountDesc;
	/**
	 * 事项所属业务类型
	 */
private String outypecode;
	/**
	 * 
	 */
private String outFlowDesc;
	/**
	 * 
	 */
private String banjianFinishtype;
	/**
	 * 
	 */
private String isSpecialpro;
	/**
	 * 
	 */
private String baknote;
	/**
	 * 删除数据标识
	 */
private String state2;
	/**
	 * 交换标识
	 */
private String syncSign;
	/**
	 * 交换时间
	 */
private Date syncDate;
	/**
	 * 交换错误描述
	 */
private String syncErrorDesc;
	/**
	 * 
	 */
private String appwebapplyurl;
	/**
	 * 移动端网上预约地址
	 */
private String appappointmenturl;
	/**
	 * 电脑端网上预约地址
	 */
private String appointmenturl;
	/**
	 * 是否网上预约
	 */
private String isWebappointment;
	/**
	 * 网上预约时段
	 */
private String webappointmentperiod;
	/**
	 * 主要内容
	 */
private String maincontext;
	/**
	 * 共同实施单位
	 */
private String doDept;
	/**
	 * 相关附件
	 */
private String  relatedguid;
	/**
	 * 相关附件信息
	 */
private String  related;
	/**
	 * 
	 */
private String isExpress;
	/**
	 * 
	 */
private String ispyc;
	/**
	 * 
	 */
private String lbsx;
	/**
	 * 公共服务事项子类型
	 */
private String serviceSubKind;
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
	/**
	 * 备用5
	 */
private String bak5;
	/**
	 * 备用6
	 */
private String bak6;
	/**
	 * 备用7
	 */
private String bak7;
	/**
	 * 备用8
	 */
private String bak8;
	/**
	 * 备用9
	 */
private String bak9;
	/**
	 * 备用10
	 */
private String bak10;
	/**
	 * 备用11
	 */
private String bak11;
	/**
	 * 备用12
	 */
private String bak12;
	/**
	 * 备用13
	 */
private String bak13;
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
private Date sendtime;
	/**
	 * 
	 */
private Long tongid;
	/**
	 * 是否投资（投资为1，否则为0）
	 */
private String isTouzip;
	/**
	 * 不接入统一办件库审核是否生效（是为1，否为0）
	 */
private String nounifyDo;
	/**
	 * 不接入统一办件库其他原因
	 */
private String ununifydoOther;
	/**
	 * 是否个性化流程（仅用于乡镇延伸事项）
	 */
private String ishasownflow;
	/**
	 * 办事指南URL
	 */
private String bsznUrl;
	/**
	 * 不适宜网上申报原因描述
	 */
private String nosuitReasondesc;
	/**
	 * 业务审查规范
	 */
private String businessRegulate;

	public String getRowguid() {
		return rowguid;
	}

	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getQlKind() {
		return qlKind;
	}

	public void setQlKind(String qlKind) {
		this.qlKind = qlKind;
	}

	public String getQlAtt() {
		return qlAtt;
	}

	public void setQlAtt(String qlAtt) {
		this.qlAtt = qlAtt;
	}

	public String getQlFullId() {
		return qlFullId;
	}

	public void setQlFullId(String qlFullId) {
		this.qlFullId = qlFullId;
	}

	public String getQlMainitemId() {
		return qlMainitemId;
	}

	public void setQlMainitemId(String qlMainitemId) {
		this.qlMainitemId = qlMainitemId;
	}

	public String getQlSubitemId() {
		return qlSubitemId;
	}

	public void setQlSubitemId(String qlSubitemId) {
		this.qlSubitemId = qlSubitemId;
	}

	public String getShiquancj() {
		return shiquancj;
	}

	public void setShiquancj(String shiquancj) {
		this.shiquancj = shiquancj;
	}

	public String getBelongxiaqucode() {
		return belongxiaqucode;
	}

	public void setBelongxiaqucode(String belongxiaqucode) {
		this.belongxiaqucode = belongxiaqucode;
	}

	public String getQlDepOrgcdoe() {
		return qlDepOrgcdoe;
	}

	public void setQlDepOrgcdoe(String qlDepOrgcdoe) {
		this.qlDepOrgcdoe = qlDepOrgcdoe;
	}

	public String getOuorgcode() {
		return ouorgcode;
	}

	public void setOuorgcode(String ouorgcode) {
		this.ouorgcode = ouorgcode;
	}

	public String getOuguid() {
		return ouguid;
	}

	public void setOuguid(String ouguid) {
		this.ouguid = ouguid;
	}

	public String getItemsource() {
		return itemsource;
	}

	public void setItemsource(String itemsource) {
		this.itemsource = itemsource;
	}

	public String getItemsourcetype() {
		return itemsourcetype;
	}

	public void setItemsourcetype(String itemsourcetype) {
		this.itemsourcetype = itemsourcetype;
	}

	public String getEntrust() {
		return entrust;
	}

	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}

	public String getEntrustdes() {
		return entrustdes;
	}

	public void setEntrustdes(String entrustdes) {
		this.entrustdes = entrustdes;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Date getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}

	public Date getQlEffectTime() {
		return qlEffectTime;
	}

	public void setQlEffectTime(Date qlEffectTime) {
		this.qlEffectTime = qlEffectTime;
	}

	public String getQlInnerCode() {
		return qlInnerCode;
	}

	public void setQlInnerCode(String qlInnerCode) {
		this.qlInnerCode = qlInnerCode;
	}

	public String getQlName() {
		return qlName;
	}

	public void setQlName(String qlName) {
		this.qlName = qlName;
	}

	public String getQlState() {
		return qlState;
	}

	public void setQlState(String qlState) {
		this.qlState = qlState;
	}

	public String getLawbasis() {
		return lawbasis;
	}

	public void setLawbasis(String lawbasis) {
		this.lawbasis = lawbasis;
	}

	public Integer getAnticipateDay() {
		return anticipateDay;
	}

	public void setAnticipateDay(Integer anticipateDay) {
		this.anticipateDay = anticipateDay;
	}

	public String getAnticipateType() {
		return anticipateType;
	}

	public void setAnticipateType(String anticipateType) {
		this.anticipateType = anticipateType;
	}

	public Integer getPromiseDay() {
		return promiseDay;
	}

	public void setPromiseDay(Integer promiseDay) {
		this.promiseDay = promiseDay;
	}

	public String getFeebasis() {
		return feebasis;
	}

	public void setFeebasis(String feebasis) {
		this.feebasis = feebasis;
	}

	public String getIsLevywaiver() {
		return isLevywaiver;
	}

	public void setIsLevywaiver(String isLevywaiver) {
		this.isLevywaiver = isLevywaiver;
	}

	public Integer getApplyerminCount() {
		return applyerminCount;
	}

	public void setApplyerminCount(Integer applyerminCount) {
		this.applyerminCount = applyerminCount;
	}

	public String getIsPilot() {
		return isPilot;
	}

	public void setIsPilot(String isPilot) {
		this.isPilot = isPilot;
	}

	public String getIsSimplepunish() {
		return isSimplepunish;
	}

	public void setIsSimplepunish(String isSimplepunish) {
		this.isSimplepunish = isSimplepunish;
	}

	public String getQlDep() {
		return qlDep;
	}

	public void setQlDep(String qlDep) {
		this.qlDep = qlDep;
	}

	public String getAcpInstitution() {
		return acpInstitution;
	}

	public void setAcpInstitution(String acpInstitution) {
		this.acpInstitution = acpInstitution;
	}

	public String getDecInstitution() {
		return decInstitution;
	}

	public void setDecInstitution(String decInstitution) {
		this.decInstitution = decInstitution;
	}

	public String getLeadDept() {
		return leadDept;
	}

	public void setLeadDept(String leadDept) {
		this.leadDept = leadDept;
	}

	public String getBjtype() {
		return bjtype;
	}

	public void setBjtype(String bjtype) {
		this.bjtype = bjtype;
	}

	public String getBenjispxz() {
		return benjispxz;
	}

	public void setBenjispxz(String benjispxz) {
		this.benjispxz = benjispxz;
	}

	public String getHandleFrequency() {
		return handleFrequency;
	}

	public void setHandleFrequency(String handleFrequency) {
		this.handleFrequency = handleFrequency;
	}

	public String getContentInvolve() {
		return contentInvolve;
	}

	public void setContentInvolve(String contentInvolve) {
		this.contentInvolve = contentInvolve;
	}

	public String getXingzhenxdrxz() {
		return xingzhenxdrxz;
	}

	public void setXingzhenxdrxz(String xingzhenxdrxz) {
		this.xingzhenxdrxz = xingzhenxdrxz;
	}

	public String getApplicableObject() {
		return applicableObject;
	}

	public void setApplicableObject(String applicableObject) {
		this.applicableObject = applicableObject;
	}

	public String getXingzhenxdrxy() {
		return xingzhenxdrxy;
	}

	public void setXingzhenxdrxy(String xingzhenxdrxy) {
		this.xingzhenxdrxy = xingzhenxdrxy;
	}

	public String getApplyCondition() {
		return applyCondition;
	}

	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}

	public String getCountLimit() {
		return countLimit;
	}

	public void setCountLimit(String countLimit) {
		this.countLimit = countLimit;
	}

	public String getCountNote() {
		return countNote;
	}

	public void setCountNote(String countNote) {
		this.countNote = countNote;
	}

	public String getBanRequirement() {
		return banRequirement;
	}

	public void setBanRequirement(String banRequirement) {
		this.banRequirement = banRequirement;
	}

	public String getShixiangsctype() {
		return shixiangsctype;
	}

	public void setShixiangsctype(String shixiangsctype) {
		this.shixiangsctype = shixiangsctype;
	}

	public String getShixiangsclx() {
		return shixiangsclx;
	}

	public void setShixiangsclx(String shixiangsclx) {
		this.shixiangsclx = shixiangsclx;
	}

	public String getBanjianFinishfiles() {
		return banjianFinishfiles;
	}

	public void setBanjianFinishfiles(String banjianFinishfiles) {
		this.banjianFinishfiles = banjianFinishfiles;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getSuperviseTel() {
		return superviseTel;
	}

	public void setSuperviseTel(String superviseTel) {
		this.superviseTel = superviseTel;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getApplyTypeTel() {
		return applyTypeTel;
	}

	public void setApplyTypeTel(String applyTypeTel) {
		this.applyTypeTel = applyTypeTel;
	}

	public String getApplyTypeMail() {
		return applyTypeMail;
	}

	public void setApplyTypeMail(String applyTypeMail) {
		this.applyTypeMail = applyTypeMail;
	}

	public String getApplyTypeFax() {
		return applyTypeFax;
	}

	public void setApplyTypeFax(String applyTypeFax) {
		this.applyTypeFax = applyTypeFax;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getWebapplyurl() {
		return webapplyurl;
	}

	public void setWebapplyurl(String webapplyurl) {
		this.webapplyurl = webapplyurl;
	}

	public String getWebconsulturl() {
		return webconsulturl;
	}

	public void setWebconsulturl(String webconsulturl) {
		this.webconsulturl = webconsulturl;
	}

	public String getMbfarenadd() {
		return mbfarenadd;
	}

	public void setMbfarenadd(String mbfarenadd) {
		this.mbfarenadd = mbfarenadd;
	}

	public String getMbgerenflag() {
		return mbgerenflag;
	}

	public void setMbgerenflag(String mbgerenflag) {
		this.mbgerenflag = mbgerenflag;
	}

	public String getNosuitApply() {
		return nosuitApply;
	}

	public void setNosuitApply(String nosuitApply) {
		this.nosuitApply = nosuitApply;
	}

	public String getIsUnifydo() {
		return isUnifydo;
	}

	public void setIsUnifydo(String isUnifydo) {
		this.isUnifydo = isUnifydo;
	}

	public String getIsUpunify() {
		return isUpunify;
	}

	public void setIsUpunify(String isUpunify) {
		this.isUpunify = isUpunify;
	}

	public String getIsCs() {
		return isCs;
	}

	public void setIsCs(String isCs) {
		this.isCs = isCs;
	}

	public String getIsVlb() {
		return isVlb;
	}

	public void setIsVlb(String isVlb) {
		this.isVlb = isVlb;
	}

	public String getUnifydodes() {
		return unifydodes;
	}

	public void setUnifydodes(String unifydodes) {
		this.unifydodes = unifydodes;
	}

	public String getWebapplymode() {
		return webapplymode;
	}

	public void setWebapplymode(String webapplymode) {
		this.webapplymode = webapplymode;
	}

	public String getChargeFlag() {
		return chargeFlag;
	}

	public void setChargeFlag(String chargeFlag) {
		this.chargeFlag = chargeFlag;
	}

	public String getChargeBasis() {
		return chargeBasis;
	}

	public void setChargeBasis(String chargeBasis) {
		this.chargeBasis = chargeBasis;
	}

	public String getHangyeclasstype() {
		return hangyeclasstype;
	}

	public void setHangyeclasstype(String hangyeclasstype) {
		this.hangyeclasstype = hangyeclasstype;
	}

	public String getRightclassQiyezt() {
		return rightclassQiyezt;
	}

	public void setRightclassQiyezt(String rightclassQiyezt) {
		this.rightclassQiyezt = rightclassQiyezt;
	}

	public String getRightclassQiyedx() {
		return rightclassQiyedx;
	}

	public void setRightclassQiyedx(String rightclassQiyedx) {
		this.rightclassQiyedx = rightclassQiyedx;
	}

	public String getRightclassGerensx() {
		return rightclassGerensx;
	}

	public void setRightclassGerensx(String rightclassGerensx) {
		this.rightclassGerensx = rightclassGerensx;
	}

	public String getRightclassGerendx() {
		return rightclassGerendx;
	}

	public void setRightclassGerendx(String rightclassGerendx) {
		this.rightclassGerendx = rightclassGerendx;
	}

	public String getQlSubKind() {
		return qlSubKind;
	}

	public void setQlSubKind(String qlSubKind) {
		this.qlSubKind = qlSubKind;
	}

	public String getImFlowUrl() {
		return imFlowUrl;
	}

	public void setImFlowUrl(String imFlowUrl) {
		this.imFlowUrl = imFlowUrl;
	}

	public String getInFlowInfo() {
		return inFlowInfo;
	}

	public void setInFlowInfo(String inFlowInfo) {
		this.inFlowInfo = inFlowInfo;
	}

	public String getOutFlowUrl() {
		return outFlowUrl;
	}

	public void setOutFlowUrl(String outFlowUrl) {
		this.outFlowUrl = outFlowUrl;
	}

	public String getMaterialInfo() {
		return materialInfo;
	}

	public void setMaterialInfo(String materialInfo) {
		this.materialInfo = materialInfo;
	}

	public String getChargeitemInfo() {
		return chargeitemInfo;
	}

	public void setChargeitemInfo(String chargeitemInfo) {
		this.chargeitemInfo = chargeitemInfo;
	}

	public String getQaInfo() {
		return qaInfo;
	}

	public void setQaInfo(String qaInfo) {
		this.qaInfo = qaInfo;
	}

	public String getFactInfo() {
		return factInfo;
	}

	public void setFactInfo(String factInfo) {
		this.factInfo = factInfo;
	}

	public String getAcceptAddressInfo() {
		return acceptAddressInfo;
	}

	public void setAcceptAddressInfo(String acceptAddressInfo) {
		this.acceptAddressInfo = acceptAddressInfo;
	}

	public String getFarenurl() {
		return farenurl;
	}

	public void setFarenurl(String farenurl) {
		this.farenurl = farenurl;
	}

	public String getServiceMode() {
		return serviceMode;
	}

	public void setServiceMode(String serviceMode) {
		this.serviceMode = serviceMode;
	}

	public String getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(String serviceDay) {
		this.serviceDay = serviceDay;
	}

	public String getDestime() {
		return destime;
	}

	public void setDestime(String destime) {
		this.destime = destime;
	}

	public String getGerenflag() {
		return gerenflag;
	}

	public void setGerenflag(String gerenflag) {
		this.gerenflag = gerenflag;
	}

	public String getIsTongjian() {
		return isTongjian;
	}

	public void setIsTongjian(String isTongjian) {
		this.isTongjian = isTongjian;
	}

	public String getQlInnerCodeItem() {
		return qlInnerCodeItem;
	}

	public void setQlInnerCodeItem(String qlInnerCodeItem) {
		this.qlInnerCodeItem = qlInnerCodeItem;
	}

	public String getApplyerminCountDesc() {
		return applyerminCountDesc;
	}

	public void setApplyerminCountDesc(String applyerminCountDesc) {
		this.applyerminCountDesc = applyerminCountDesc;
	}

	public String getOutypecode() {
		return outypecode;
	}

	public void setOutypecode(String outypecode) {
		this.outypecode = outypecode;
	}

	public String getOutFlowDesc() {
		return outFlowDesc;
	}

	public void setOutFlowDesc(String outFlowDesc) {
		this.outFlowDesc = outFlowDesc;
	}

	public String getBanjianFinishtype() {
		return banjianFinishtype;
	}

	public void setBanjianFinishtype(String banjianFinishtype) {
		this.banjianFinishtype = banjianFinishtype;
	}

	public String getIsSpecialpro() {
		return isSpecialpro;
	}

	public void setIsSpecialpro(String isSpecialpro) {
		this.isSpecialpro = isSpecialpro;
	}

	public String getBaknote() {
		return baknote;
	}

	public void setBaknote(String baknote) {
		this.baknote = baknote;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getSyncSign() {
		return syncSign;
	}

	public void setSyncSign(String syncSign) {
		this.syncSign = syncSign;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getSyncErrorDesc() {
		return syncErrorDesc;
	}

	public void setSyncErrorDesc(String syncErrorDesc) {
		this.syncErrorDesc = syncErrorDesc;
	}

	public String getAppwebapplyurl() {
		return appwebapplyurl;
	}

	public void setAppwebapplyurl(String appwebapplyurl) {
		this.appwebapplyurl = appwebapplyurl;
	}

	public String getAppappointmenturl() {
		return appappointmenturl;
	}

	public void setAppappointmenturl(String appappointmenturl) {
		this.appappointmenturl = appappointmenturl;
	}

	public String getAppointmenturl() {
		return appointmenturl;
	}

	public void setAppointmenturl(String appointmenturl) {
		this.appointmenturl = appointmenturl;
	}

	public String getIsWebappointment() {
		return isWebappointment;
	}

	public void setIsWebappointment(String isWebappointment) {
		this.isWebappointment = isWebappointment;
	}

	public String getWebappointmentperiod() {
		return webappointmentperiod;
	}

	public void setWebappointmentperiod(String webappointmentperiod) {
		this.webappointmentperiod = webappointmentperiod;
	}

	public String getMaincontext() {
		return maincontext;
	}

	public void setMaincontext(String maincontext) {
		this.maincontext = maincontext;
	}

	public String getDoDept() {
		return doDept;
	}

	public void setDoDept(String doDept) {
		this.doDept = doDept;
	}

	public String getRelatedguid() {
		return relatedguid;
	}

	public void setRelatedguid(String relatedguid) {
		this.relatedguid = relatedguid;
	}

	public String getRelated() {
		return related;
	}

	public void setRelated(String related) {
		this.related = related;
	}

	public String getIsExpress() {
		return isExpress;
	}

	public void setIsExpress(String isExpress) {
		this.isExpress = isExpress;
	}

	public String getIspyc() {
		return ispyc;
	}

	public void setIspyc(String ispyc) {
		this.ispyc = ispyc;
	}

	public String getLbsx() {
		return lbsx;
	}

	public void setLbsx(String lbsx) {
		this.lbsx = lbsx;
	}

	public String getServiceSubKind() {
		return serviceSubKind;
	}

	public void setServiceSubKind(String serviceSubKind) {
		this.serviceSubKind = serviceSubKind;
	}

	public String getBak1() {
		return bak1;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	public String getBak2() {
		return bak2;
	}

	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}

	public String getBak3() {
		return bak3;
	}

	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

	public String getBak4() {
		return bak4;
	}

	public void setBak4(String bak4) {
		this.bak4 = bak4;
	}

	public String getBak5() {
		return bak5;
	}

	public void setBak5(String bak5) {
		this.bak5 = bak5;
	}

	public String getBak6() {
		return bak6;
	}

	public void setBak6(String bak6) {
		this.bak6 = bak6;
	}

	public String getBak7() {
		return bak7;
	}

	public void setBak7(String bak7) {
		this.bak7 = bak7;
	}

	public String getBak8() {
		return bak8;
	}

	public void setBak8(String bak8) {
		this.bak8 = bak8;
	}

	public String getBak9() {
		return bak9;
	}

	public void setBak9(String bak9) {
		this.bak9 = bak9;
	}

	public String getBak10() {
		return bak10;
	}

	public void setBak10(String bak10) {
		this.bak10 = bak10;
	}

	public String getBak11() {
		return bak11;
	}

	public void setBak11(String bak11) {
		this.bak11 = bak11;
	}

	public String getBak12() {
		return bak12;
	}

	public void setBak12(String bak12) {
		this.bak12 = bak12;
	}

	public String getBak13() {
		return bak13;
	}

	public void setBak13(String bak13) {
		this.bak13 = bak13;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Date getTongTime() {
		return tongTime;
	}

	public void setTongTime(Date tongTime) {
		this.tongTime = tongTime;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Long getTongid() {
		return tongid;
	}

	public void setTongid(Long tongid) {
		this.tongid = tongid;
	}

	public String getIsTouzip() {
		return isTouzip;
	}

	public void setIsTouzip(String isTouzip) {
		this.isTouzip = isTouzip;
	}

	public String getNounifyDo() {
		return nounifyDo;
	}

	public void setNounifyDo(String nounifyDo) {
		this.nounifyDo = nounifyDo;
	}

	public String getUnunifydoOther() {
		return ununifydoOther;
	}

	public void setUnunifydoOther(String ununifydoOther) {
		this.ununifydoOther = ununifydoOther;
	}

	public String getIshasownflow() {
		return ishasownflow;
	}

	public void setIshasownflow(String ishasownflow) {
		this.ishasownflow = ishasownflow;
	}

	public String getBsznUrl() {
		return bsznUrl;
	}

	public void setBsznUrl(String bsznUrl) {
		this.bsznUrl = bsznUrl;
	}

	public String getNosuitReasondesc() {
		return nosuitReasondesc;
	}

	public void setNosuitReasondesc(String nosuitReasondesc) {
		this.nosuitReasondesc = nosuitReasondesc;
	}

	public String getBusinessRegulate() {
		return businessRegulate;
	}

	public void setBusinessRegulate(String businessRegulate) {
		this.businessRegulate = businessRegulate;
	}
}
