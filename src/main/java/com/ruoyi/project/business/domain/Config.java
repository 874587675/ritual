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
@TableName(value = "t_config")
public class Config {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 配置键
     */
    @TableField(value = "config_key")
    private String configKey;

    /**
     * 配置名
     */
    @TableField(value = "config_name")
    private String configName;

    /**
     * 配置值
     */
    @TableField(value = "config_value")
    private String configValue;

    /**
     * 配置类型
     */
    @TableField(value = "config_type")
    private String configType;

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
