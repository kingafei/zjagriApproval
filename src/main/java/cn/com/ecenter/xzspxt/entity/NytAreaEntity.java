package cn.com.ecenter.xzspxt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class NytAreaEntity {

	/**
	 * 主键id
	 */
@TableId(value = "id",type= IdType.AUTO)
private Integer id;
	/**
	 * 父键id
	 */
private Integer parentId;
	/**
	 * 地区名称
	 */
private String name;
	/**
	 * 地区国标编码
	 */
private String code;
	/**
	 * 地区id路径
	 */
private String codeUrl;
	/**
	 * 排序
	 */
private Integer forOrder;
	/**
	 * 等级（省1，市2，县3，镇4）
	 */
private String level;
	/**
	 * 删除标识（未删除0，已删除1）
	 */
private String isDelete;
	/**
	 * 创建人id
	 */
private String fkUid;
	/**
	 * 创建时间
	 */
private Date createTime;
	/**
	 * 更新时间
	 */
private Date updateTime;
	/**
	 * 状态：（显示0，隐藏1）
	 */
private String state;

private String orgcoding;

private String uncode;

private String uniquecoding;

private String adcode;

private String regioncode;
	/**
	 * 备用1
	 */
private String bak1;
	/**
	 *备用2
	 */
private String bak2;
	/**
	 *备用3
	 */
private String bak3;
	/**
	 *备用4
	 */
private String bak4;

private List<NytAreaEntity> treeList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public Integer getForOrder() {
		return forOrder;
	}

	public void setForOrder(Integer forOrder) {
		this.forOrder = forOrder;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getFkUid() {
		return fkUid;
	}

	public void setFkUid(String fkUid) {
		this.fkUid = fkUid;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getUniquecoding() {
		return uniquecoding;
	}

	public void setUniquecoding(String uniquecoding) {
		this.uniquecoding = uniquecoding;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
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

	public List<NytAreaEntity> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<NytAreaEntity> treeList) {
		this.treeList = treeList;
	}
}
