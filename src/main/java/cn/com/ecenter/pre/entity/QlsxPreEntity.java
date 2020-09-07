package cn.com.ecenter.pre.entity;

import lombok.Data;
import java.util.Date;

@Data
public class QlsxPreEntity {

	/**
	 * 主键
	 */
private Long id;
	/**
	 * 事项唯一标识
	 */
private String rowguid;
	/**
	 * 事项tongID
	 */
private String tongid;
	/**
	 * 办件申报号
	 */
private String projid;
	/**
	 * 事项id
	 */
private Long sxid;
	/**
	 * 办件id
	 */
private Long bjid;

}
