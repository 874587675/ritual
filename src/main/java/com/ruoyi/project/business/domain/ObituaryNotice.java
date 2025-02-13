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
@TableName(value = "t_obituary_notice")
public class ObituaryNotice {
    /**
     * 讣告通知表主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 讣告名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 讣告内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 用户主键ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 纪念馆ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

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