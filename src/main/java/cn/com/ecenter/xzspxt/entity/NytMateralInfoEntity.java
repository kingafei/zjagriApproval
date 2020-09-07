package cn.com.ecenter.xzspxt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class NytMateralInfoEntity {

	/**
	 * 主键ID
	 */
@TableId(value = "id",type= IdType.AUTO)
private Integer id;
	/**
	 * 变化标识 0：未变化，1：变化
	 */
private String changeFlag;
	/**
	 * 材料标识
	 */
private String materialGuid;
	/**
	 * 材料名称
	 */
private String materialName;
	/**
	 * 材料形式，代码项：材料形式
	 */
private String materialFormat;
	/**
	 * 材料详细要求
	 */
private String materialDetail;
	/**
	 * 材料必要性，代码项：材料必要性
	 */
private String necessity;
	/**
	 * 材料必要性描述
	 */
private String necessityDesc;
	/**
	 * 材料出具单位
	 */
private String materialRes;
	/**
	 * 空白表格文件名称（完整文件名，带后缀）
	 */
private String filenamek;
	/**
	 * 空白表格文件附件链接地址（公有云地址）
	 */
private String fileurlk;
	/**
	 * 示例表格文件名称（完整文件名，带后缀）
	 */
private String filename;
	/**
	 * 示例文件附件链接地址（公有云地址）
	 */
private String fileurl;
	/**
	 * 备注
	 */
private String bak;
	/**
	 * 
	 */
private String populartypes;
	/**
	 * 
	 */
private String conditions;
	/**
	 * 
	 */
private String isRq;
	/**
	 * 
	 */
private String ispublish;
	/**
	 * 
	 */
private String filecontentk;
	/**
	 * 
	 */
private String filecontent;
	/**
	 * 
	 */
private String materialType;
	/**
	 * 
	 */
private String sourceType;
	/**
	 * 
	 */
private String sourceExplain;
	/**
	 * 
	 */
private String pageNum;
	/**
	 * 
	 */
private String pageFormat;
	/**
	 * 
	 */
private String fillExplian;
	/**
	 * 
	 */
private String acceptStand;
	/**
	 * 
	 */
private String byLaw;
	/**
	 * 
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

	public String getMaterialGuid() {
		return materialGuid;
	}

	public void setMaterialGuid(String materialGuid) {
		this.materialGuid = materialGuid;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialFormat() {
		return materialFormat;
	}

	public void setMaterialFormat(String materialFormat) {
		this.materialFormat = materialFormat;
	}

	public String getMaterialDetail() {
		return materialDetail;
	}

	public void setMaterialDetail(String materialDetail) {
		this.materialDetail = materialDetail;
	}

	public String getNecessity() {
		return necessity;
	}

	public void setNecessity(String necessity) {
		this.necessity = necessity;
	}

	public String getNecessityDesc() {
		return necessityDesc;
	}

	public void setNecessityDesc(String necessityDesc) {
		this.necessityDesc = necessityDesc;
	}

	public String getMaterialRes() {
		return materialRes;
	}

	public void setMaterialRes(String materialRes) {
		this.materialRes = materialRes;
	}

	public String getFilenamek() {
		return filenamek;
	}

	public void setFilenamek(String filenamek) {
		this.filenamek = filenamek;
	}

	public String getFileurlk() {
		return fileurlk;
	}

	public void setFileurlk(String fileurlk) {
		this.fileurlk = fileurlk;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public String getBak() {
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

	public String getPopulartypes() {
		return populartypes;
	}

	public void setPopulartypes(String populartypes) {
		this.populartypes = populartypes;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getIsRq() {
		return isRq;
	}

	public void setIsRq(String isRq) {
		this.isRq = isRq;
	}

	public String getIspublish() {
		return ispublish;
	}

	public void setIspublish(String ispublish) {
		this.ispublish = ispublish;
	}

	public String getFilecontentk() {
		return filecontentk;
	}

	public void setFilecontentk(String filecontentk) {
		this.filecontentk = filecontentk;
	}

	public String getFilecontent() {
		return filecontent;
	}

	public void setFilecontent(String filecontent) {
		this.filecontent = filecontent;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceExplain() {
		return sourceExplain;
	}

	public void setSourceExplain(String sourceExplain) {
		this.sourceExplain = sourceExplain;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageFormat() {
		return pageFormat;
	}

	public void setPageFormat(String pageFormat) {
		this.pageFormat = pageFormat;
	}

	public String getFillExplian() {
		return fillExplian;
	}

	public void setFillExplian(String fillExplian) {
		this.fillExplian = fillExplian;
	}

	public String getAcceptStand() {
		return acceptStand;
	}

	public void setAcceptStand(String acceptStand) {
		this.acceptStand = acceptStand;
	}

	public String getByLaw() {
		return byLaw;
	}

	public void setByLaw(String byLaw) {
		this.byLaw = byLaw;
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
