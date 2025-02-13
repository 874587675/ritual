package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_museum_info")
@Builder
public class MuseumInfo {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 祭祀馆类型（
     * 1-名人馆
     * 2-伟人馆
     * 3-英雄馆
     * 4-事件馆
     * 5-院士馆
     * 6-私人馆
     * 7-祠堂馆
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 人物名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 人物简介
     */
    @TableField(value = "introduce")
    private String introduce;

    /**
     * 人物头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 相册
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 视频
     */
    @TableField(value = "video")
    private String video;
    
    /**
     * 热度
     */
    @TableField(value = "popularity")
    private Integer popularity;

    /**
     * 曝光度
     */
    @TableField(value = "exposure")
    private Integer exposure;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 出生日期（公历）
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "birth_date")
    private Date birthDate;

    /**
     * 出生日期（农历）
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "birth_date_lunar")
    private Date birthDateLunar;

    /**
     * 诞辰周年
     */
    @JsonIgnore
    @TableField(value = "birth_count")
    private Integer birthCount;

    /**
     * 出生日期是否是农历（0公历，1农历）
     */
    @TableField(value = "is_lunar_birth")
    private Integer isLunarBirth;

    /**
     * 去世日期（公历）
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "death_date")
    private Date deathDate;

    /**
     * 去世日期（农历）
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "death_date_lunar")
    private Date deathDateLunar;

    /**
     * 忌日周年
     */
    @JsonIgnore
    @TableField(value = "death_count")
    private Integer deathCount;

    /**
     * 去世日期是否是农历（0公历，1农历）
     */
    @TableField(value = "is_lunar_death")
    private Integer isLunarDeath;

    /**
     * 死亡证明
     */
    @TableField(value = "death_verify")
    private String deathVerify;
    
    /**
     * 诞辰还是忌日（0-什么都不是 1-诞辰 2-忌日）
     */
    @TableField(value = "status")
    private Integer status;
    
    /**
     * 背景模板主键ID
     */
    @TableField(value = "museum_back_id")
    private Integer museumBackId;

    /**
     * 音乐主键ID
     */
    @TableField(value = "museum_sound_id")
    private Integer museumSoundId;
    
    /**
     * 祠堂祖籍位置
     */
    @TableField(value = "`location`")
    private String location;

    /**
     * 家族名称
     */
    @TableField(value = "family_name")
    private String familyName;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 供奉灵位数量
     */
    @TableField(value = "memorial_num")
    private Integer memorialNum;

    /**
     * 预留手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 是否公开（0 隐藏 ， 1公开）
     */
    @TableField(value = "is_public")
    private Integer isPublic;

    /**
     * 用户编号ID
     */
    @TableField(value = "user_id")
    private String userId;
    
    /**
     * 灵牌主题背景ID
     */
    @TableField(value = "spirit_title_id")
    private Integer spiritTitleId;

    /**
     * 逻辑删除（0未删除 1已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
