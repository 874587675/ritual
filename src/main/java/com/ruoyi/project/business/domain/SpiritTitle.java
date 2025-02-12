package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_spirit_title")
public class SpiritTitle {
    /**
     * 祠堂馆灵牌背景表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 灵牌背景表名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 灵牌背景表描述
     */
    @TableField(value = "decription")
    private String decription;

    /**
     * 灵牌主题背景
     */
    @TableField(value = "title_url")
    private String titleUrl;

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
