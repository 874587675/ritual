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
@TableName(value = "t_family_member")
public class FamilyMember {
    /**
     * 亲友团成员表主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 成员ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 亲友团ID
     */
    @TableField(value = "team_id")
    private Integer teamId;

    /**
     * 成员名称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 成员状态
     */
    @TableField(value = "join_status")
    private Integer joinStatus;

    /**
     * 成员角色
     * 1-管理员
     * 2-普通成员
     */
    @TableField(value = "`role`")
    private Integer role;

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
