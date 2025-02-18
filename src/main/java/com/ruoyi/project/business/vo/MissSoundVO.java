package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.MissSound;
import lombok.Data;

@Data
public class MissSoundVO extends MissSound {
    /**
     * 手机验证码
     */
    private String code;
}
