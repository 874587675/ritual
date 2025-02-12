package com.ruoyi.project.business.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @ClassName:MuseumEnum
 * @description:
 * @author: zgc
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MuseumEnum {
//1-名人馆
//2-伟人馆
//3-英雄馆
//4-事件馆
//5-院士馆
//6-私人馆
//7-祠堂馆
    FAMOUS(1, "名人馆"),
    GREAT(2, "伟人馆"),
    HERO(3, "英雄馆"),
    EVENT(4, "事件馆"),
    ACADEMICIAN(5, "院士馆"),
    PRIVATE(6, "私人馆"),
    SHRINE(7, "祠堂馆");
    
    private Integer code;
    private String desc;
}
