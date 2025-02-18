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
@TableName(value = "t_obituary_back")
public class ObituaryBack {
    /**
     * 讣告模板背景表主键
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 背景名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 文字内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 背景类型 1图片模板，2文字模板
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 背景地址
     */
    @TableField(value = "back_url")
    private String backUrl;

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
