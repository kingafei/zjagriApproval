package cn.com.ecenter.pre.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 材料与附件关联表 Entity
 *
 * @author ecenter
 * @date 2020-09-04 17:02:56
 */
@Data
@TableName("pre_materal_file")
public class PreMateralFile {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 材料id&环节id
     */
    @TableField("materal_id")
    private Integer materalId;

    /**
     * 附件id
     */
    @TableField("file_id")
    private Integer fileId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

}
