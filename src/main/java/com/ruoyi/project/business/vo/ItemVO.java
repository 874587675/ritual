package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.Item;
import com.ruoyi.project.business.domain.ItemOption;
import lombok.Data;

/**
 * @ClassName:ItemVO
 * @description:
 * @author: zgc
 **/
@Data
public class ItemVO extends Item {
    
    private ItemOption itemOption;
}
