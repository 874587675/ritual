package com.ruoyi.project.business.constant;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

/**
 * @ClassName:RoomEnum
 * @description:
 * @author: zgc
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RoomEnum {
    //房间状态
    //1 开放
    //2 关闭
    //3 封禁 
    //4 私密
    OPEN(1, "开放"),
    CLOSE(2, "关闭"),
    FORBIDDEN(3, "封禁"),
    SECRET(4, "私密");
    
    private Integer code;
    private String desc;
    
}
