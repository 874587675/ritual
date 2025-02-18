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
@TableName(value = "t_collect_produce")
public class CollectProduce {
    /**
     * 商品收藏表主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 商品SpuId主键
     */
    @TableField(value = "spu_id")
    private Integer spuId;

    /**
     * 收藏状态（0 未收藏 1已收藏）
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

    /**
     * 用户主键ID
     */
    @TableField(value = "user_id")
    private String userId;
}
