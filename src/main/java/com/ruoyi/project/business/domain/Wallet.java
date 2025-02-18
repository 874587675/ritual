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
@TableName(value = "t_wallet")
public class Wallet {
    /**
     * 用户钱包表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 用户余额
     */
    @TableField(value = "balance")
    private BigDecimal balance;

    /**
     * 用户编号ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 用户open_id
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 钱包状态
     */
    @TableField(value = "wallet_status")
    private Integer walletStatus;

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
