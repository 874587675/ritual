package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_item_option")
public class ItemOption {
    /**
     * 虚拟物品配置表主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 配置物品名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 配置物品描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 配置物品的有效天数
     */
    @TableField(value = "validate_days")
    private Integer validateDays;

    /**
     * 配置物品价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}
