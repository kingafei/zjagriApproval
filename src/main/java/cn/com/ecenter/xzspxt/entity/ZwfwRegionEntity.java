package cn.com.ecenter.xzspxt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ZwfwRegionEntity {

	/**
	 * 主键id
	 */
private String id;
	/**
	 * 组织编码
	 */
private String orgcoding;
	/**
	 * 统一社会信用代码
	 */
private String uncode;
	/**
	 * 组织名称
	 */
private String name;
	/**
	 * 组织唯一编码
	 */
private String uniquecoding;
	/**
	 * 旧地区编码
	 */
private String regioncode;
	/**
	 * 上级id
	 */
private String pid;
	/**
	 * 新地区编码
	 */
private String AdCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgcoding() {
		return orgcoding;
	}

	public void setOrgcoding(String orgcoding) {
		this.orgcoding = orgcoding;
	}

	public String getUncode() {
		return uncode;
	}

	public void setUncode(String uncode) {
		this.uncode = uncode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniquecoding() {
		return uniquecoding;
	}

	public void setUniquecoding(String uniquecoding) {
		this.uniquecoding = uniquecoding;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAdCode() {
		return AdCode;
	}

	public void setAdCode(String adCode) {
		AdCode = adCode;
	}
}
