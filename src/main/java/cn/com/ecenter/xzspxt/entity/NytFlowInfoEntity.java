package cn.com.ecenter.xzspxt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class NytFlowInfoEntity {

	/**
	 * 主键ID
	 */
@TableId(value = "id",type= IdType.AUTO)
private Integer id;
	/**
	 * 流程内部ID（内部关联使用）
	 */
private String flowId;
	/**
	 * 岗位信息变化标识0：未变化，1：变化
	 */
private String changeFlagP;
	/**
	 * 环节信息变化标识 0：未变化，1：变化
	 */
private String changeFlagA;
	/**
	 * 流程名称
	 */
private String flowTitle;
	/**
	 * 岗位唯一标识
	 */
private String positionId;
	/**
	 * 岗位名称
	 */
private String positionName;
	/**
	 * 岗位职责
	 */
private String positionDuty;
	/**
	 * 办理科（处）室
	 */
private String department;
	/**
	 * 办理人员及联系电话
	 */
private String person;
	/**
	 * 办公室人员ID
	 */
private String personGuids;
	/**
	 * 办公室人员姓名
	 */
private String personNames;
	/**
	 * 环节ID（内部关联使用）
	 */
private String activityId;
	/**
	 * 环节类型:U-用户参与；D-系统过程处理
	 */
private String activityType;
	/**
	 * 环节名称
	 */
private String activityTitle;
	/**
	 * 环节办理时限，填写数字
	 */
private String handleTimelimt;
	/**
	 * 环节办理时限的单位，填写数字代码，内容为：1：工作日；2：月；3：年
	 */
private String handleTimelimttype;
	/**
	 * 关联附件文档表
	 */
private String fileId;
	/**
	 * 发起环节id
	 */
private String sourceActivityId;
	/**
	 * 到达环节id
	 */
private String targetActivityId;
	/**
	 * 环节名称（环节流转关系的）
	 */
private String note;
	/**
	 * 外键ID,和主表关联
	 */
private String pId;
	/**
	 * 创建时间
	 */
private Date createTime;
	/**
	 * 更新时间
	 */
private Date updateTime;
	/**
	 * 备注
	 */
private String remarks;
	/**
	 * 0-未删除，1-已删； 默认0
	 */
private String isDelete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getChangeFlagP() {
		return changeFlagP;
	}

	public void setChangeFlagP(String changeFlagP) {
		this.changeFlagP = changeFlagP;
	}

	public String getChangeFlagA() {
		return changeFlagA;
	}

	public void setChangeFlagA(String changeFlagA) {
		this.changeFlagA = changeFlagA;
	}

	public String getFlowTitle() {
		return flowTitle;
	}

	public void setFlowTitle(String flowTitle) {
		this.flowTitle = flowTitle;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionDuty() {
		return positionDuty;
	}

	public void setPositionDuty(String positionDuty) {
		this.positionDuty = positionDuty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPersonGuids() {
		return personGuids;
	}

	public void setPersonGuids(String personGuids) {
		this.personGuids = personGuids;
	}

	public String getPersonNames() {
		return personNames;
	}

	public void setPersonNames(String personNames) {
		this.personNames = personNames;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getHandleTimelimt() {
		return handleTimelimt;
	}

	public void setHandleTimelimt(String handleTimelimt) {
		this.handleTimelimt = handleTimelimt;
	}

	public String getHandleTimelimttype() {
		return handleTimelimttype;
	}

	public void setHandleTimelimttype(String handleTimelimttype) {
		this.handleTimelimttype = handleTimelimttype;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getSourceActivityId() {
		return sourceActivityId;
	}

	public void setSourceActivityId(String sourceActivityId) {
		this.sourceActivityId = sourceActivityId;
	}

	public String getTargetActivityId() {
		return targetActivityId;
	}

	public void setTargetActivityId(String targetActivityId) {
		this.targetActivityId = targetActivityId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
}
