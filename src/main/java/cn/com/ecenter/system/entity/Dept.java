package cn.com.ecenter.system.entity;

import cn.com.ecenter.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Data
@TableName("t_dept")
@Excel("部门信息表")
public class Dept implements Serializable {

    private static final long serialVersionUID = 5702271568363798328L;

    public static final Long TOP_NODE = 0L;

    /**
     * 部门 ID
     */
    @TableId(value = "DEPT_ID", type = IdType.AUTO)
    private Long deptId;

    /**
     * 上级部门 ID
     */
    @TableField("PARENT_ID")
    private Long parentId;

    /**
     * 部门名称
     */
    @TableField("DEPT_NAME")
    @NotBlank(message = "{required}")
    @ExcelField(value = "部门名称")
    private String deptName;

    /**
     * 排序
     */
    @TableField("ORDER_NUM")
    private Long orderNum;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField("MODIFY_TIME")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date modifyTime;

    @TableField("organization_oid")
    @ExcelField(value = "组织oid")
    private String organizationOid;

    @TableField("unified_credit_code")
    @ExcelField(value = "统一信用代码")
    private String unifiedCreditCode;

    @TableField("org_live")
    @ExcelField(value = "原组织编码")
    private String orgLive;

    @TableField("unique_coding")
    @ExcelField(value = "组织唯一编码")
    private String uniqueCoding;

    @TableField("administration_code")
    @ExcelField(value = "行政区划编码+部门序号")
    private String administrationCode;

    @TableField("is_delete")
    @ExcelField(value = "是否删除（0-正常，1删除）")
    private String isDelete;

    @TableField("regioncode")
    @ExcelField(value = "原组织编码")
    private String regioncode;

    @TableField("bik1")
    @ExcelField(value = "备用字段")
    private String bik1;

}
