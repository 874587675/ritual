package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "t_balance_change")
public class BalanceChange {
    /**
     * 钱包余额变动记录表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 钱包编号ID
     */
    @TableField(value = "wallet_id")
    private Integer walletId;

    /**
     * 余额变动名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 余额变动金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 余额变动的状态
1-未审核
2-审核通过
3-审核不通过
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 余额变动的类型
1 提现
2 佣金到账
3 商城扣款
     */
    @TableField(value = "`type`")
    private Integer type;

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
