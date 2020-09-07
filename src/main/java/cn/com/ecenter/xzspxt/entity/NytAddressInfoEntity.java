package cn.com.ecenter.xzspxt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class NytAddressInfoEntity {

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
	 * 受理地点
	 */
private String address;
	/**
	 * 受理地点分类
	 */
private String addressKind;
	/**
	 * 受理接待时间
	 */
private String acceptTimedesc;
	/**
	 * 联系电话
	 */
private String phone;
	/**
	 * 测绘局地址ID
	 */
private String uuid;
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
	 * tongID
	 */
private Long tongid;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressKind() {
		return addressKind;
	}

	public void setAddressKind(String addressKind) {
		this.addressKind = addressKind;
	}

	public String getAcceptTimedesc() {
		return acceptTimedesc;
	}

	public void setAcceptTimedesc(String acceptTimedesc) {
		this.acceptTimedesc = acceptTimedesc;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
