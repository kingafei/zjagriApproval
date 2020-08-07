package cn.com.ecenter.xzspxt.entity.po;

import lombok.Data;
import java.util.Date;

@Data
public class NytDataDictEntity {

	/**
	 * 主键id
	 */
private Integer pkId;
	/**
	 * 中文注释
	 */
private String comment;
	/**
	 * 字典标识
	 */
private String dictSign;
	/**
	 * 字典值
	 */
private String dictValue;
	/**
	 * 字典值_注释
	 */
private String valueComment;
	/**
	 * 字典值_排序
	 */
private Byte valueSort;
	/**
	 * 创建人id
	 */
private String fkUid;
	/**
	 * 状态 0显示、1隐藏
	 */
private Byte state;
	/**
	 * 状态 0正常、1删除
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

}
