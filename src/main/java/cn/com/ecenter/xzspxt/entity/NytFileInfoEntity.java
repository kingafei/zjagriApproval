package cn.com.ecenter.xzspxt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class NytFileInfoEntity {

	/**
	 * 主键ID
	 */
@TableId(value = "id",type= IdType.AUTO)
private Integer id;
	/**
	 * 岗位文书名称
	 */
private String filename;
	/**
	 * 发给行政相对人或内部存档
	 */
private String filekind;
	/**
	 * 文件内容
	 */
private String filecontent;
	/**
	 * 文书链接地址（公有云地址）
	 */
private String fileurl;
	/**
	 * 外键ID,和环节流程表关联
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
	 * 发起环节id
	 */
private String remarks;
	/**
	 * tongID
	 */
private Long tongid;
	/**
	 * 环节名称（环节流转关系的）
	 */
private String isDelete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilekind() {
		return filekind;
	}

	public void setFilekind(String filekind) {
		this.filekind = filekind;
	}

	public String getFilecontent() {
		return filecontent;
	}

	public void setFilecontent(String filecontent) {
		this.filecontent = filecontent;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
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
