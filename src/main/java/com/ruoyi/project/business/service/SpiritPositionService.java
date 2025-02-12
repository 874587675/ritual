package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.SpiritPosition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.SpiritPositionVO;

import java.util.List;

public interface SpiritPositionService extends IService<SpiritPosition>{

    List<SpiritPosition> selectSpiritPositionByMuseumId(Integer museumId);

    String insertSpiritPosition(SpiritPosition spiritPosition);

    String updateSpiritPosition(List<SpiritPositionVO> spiritPositionVOList);
}
