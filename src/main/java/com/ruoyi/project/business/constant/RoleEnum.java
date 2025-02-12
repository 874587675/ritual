package com.ruoyi.project.business.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @ClassName:RoleEnum
 * @description:
 * @author: zgc
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RoleEnum {
    //成员角色
    //1房主
    //2管理员
    //3普通成员
    OWNER(1, "房主"),
    ADMIN(2, "管理员"),
    MEMBER(3, "普通成员");
    
    private Integer code;
    private String desc;
    
}
