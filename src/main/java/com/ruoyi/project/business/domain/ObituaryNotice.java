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
    @TableId(value = "id")
    private Integer id;

    /**
     * 讣告逝者名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 发布人用户主键ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 逝者头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 发布人手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 讣告内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 讣告逝者称谓
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 逝者日期
     */
    @TableField(value = "death_date")
    private Date deathDate;

    /**
     * 追悼日期
     */
    @TableField(value = "memorial_date")
    private Date memorialDate;

    /**
     * 追悼地址
     */
    @TableField(value = "memorial_address")
    private String memorialAddress;

    /**
     * 纪念馆ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

    /**
     * 讣告背景模板
     */
    @TableField(value = "obituary_back_id")
    private String obituaryBackId;

    /**
     * 回忆往昔
     */
    @TableField(value = "picture")
    private String picture;

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
