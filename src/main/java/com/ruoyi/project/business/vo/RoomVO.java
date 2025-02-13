package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.MuseumInfo;
import com.ruoyi.project.business.domain.Room;
import lombok.Data;

/**
 * @ClassName:RoomVO
 * @description:
 * @author: zgc
 **/
@Data
public class RoomVO extends Room {
    /**
     * 纪念馆对象
     */
    private MuseumInfo museumInfo;
}
