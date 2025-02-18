package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "t_gift_item")
public class GiftItem {
    /**
     * 供奉物品表
     */
    @TableId(value = "id")
    public Integer id;

    /**
     * 物品主键ID
     */
    @TableField(value = "item_id")
    public Integer itemId;

    /**
     * 用户主键ID
     */
    @TableField(value = "user_id")
    public String userId;

    /**
     * 虚拟物品配置ID
     */
    @TableField(value = "option_id")
    private Integer optionId;

    /**
     * 讣告ID
     */
    @TableField(value = "obituary_id")
    public Integer obituaryId;
    
    /**
     * 用户名称
     */
    @TableField(value = "nick_name")
    public String nickName;

    /**
     * 房间主键ID
     */
    @TableField(value = "room_id")
    public Integer roomId;

    /**
     * 馆藏主键ID
     */
    @TableField(value = "museum_id")
    public Integer museumId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    public Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    public Date updateTime;

    /**
     * 物品积分
     */
    @TableField(value = "score")
    public Double score;

    /**
     * 供奉数量
     */
    @TableField(value = "count")
    private Integer count;
    
    /**
     * 订单编号ID
     */
    @TableField(value = "order_id")
    private String orderId;
    
    /**
     * 订单状态（1未支付 2已支付 3已取消 4已退款 5已关闭）
     */
    @TableField(value = "order_status")
    private Integer orderStatus;
    
    /**
     * 物品过期时间
     */
    @TableField(value = "expire_time")
    private Date expireTime;
    
    
}
