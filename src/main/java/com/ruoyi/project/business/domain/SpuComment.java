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
@TableName(value = "t_spu_comment")
public class SpuComment {
    /**
     * 商品评价表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 订单主键ID
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 商品spu主键ID
     */
    @TableField(value = "spu_id")
    private Integer spuId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 用户头像
     */
    @TableField(value = "user_avatar")
    private String userAvatar;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户评分
     */
    @TableField(value = "rate")
    private String rate;

    /**
     * 用户评价
     */
    @TableField(value = "description")
    private String description;

    /**
     * 评价图片
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 评价视频
     */
    @TableField(value = "video")
    private String video;

    /**
     * 评价状态
     */
    @TableField(value = "`status`")
    private Integer status;

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
