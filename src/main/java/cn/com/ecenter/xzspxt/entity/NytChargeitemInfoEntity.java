package cn.com.ecenter.xzspxt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class NytChargeitemInfoEntity {

	/**
	 * 主键ID
	 */
@TableId(value = "id",type= IdType.AUTO)
private Integer id;
	/**
	 * 0：未变化，1：变化
	 */
private String changeFlag;
	/**
	 * 收费项目标识
	 */
private String chargeitemGuid;
	/**
	 * 收费项目名称
	 */
private String chargeitemName;
	/**
	 * 收费标准
	 */
private String chargeitemBasis;
	/**
	 * 减免说明
	 */
private String reductionDesc;
	/**
	 * 排序
	 */
private Integer ordernum;
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

	public String getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}

	public String getChargeitemGuid() {
		return chargeitemGuid;
	}

	public void setChargeitemGuid(String chargeitemGuid) {
		this.chargeitemGuid = chargeitemGuid;
	}

	public String getChargeitemName() {
		return chargeitemName;
	}

	public void setChargeitemName(String chargeitemName) {
		this.chargeitemName = chargeitemName;
	}

	public String getChargeitemBasis() {
		return chargeitemBasis;
	}

	public void setChargeitemBasis(String chargeitemBasis) {
		this.chargeitemBasis = chargeitemBasis;
	}

	public String getReductionDesc() {
		return reductionDesc;
	}

	public void setReductionDesc(String reductionDesc) {
		this.reductionDesc = reductionDesc;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
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
