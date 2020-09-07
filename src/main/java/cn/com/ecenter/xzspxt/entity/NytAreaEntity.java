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

private List<NytAreaEntity> children;
}
