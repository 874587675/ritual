package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.service.GiftItemService;
import com.ruoyi.project.business.vo.GiftItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "虚拟物品记录管理")
@RequestMapping("/giftItem")
public class GiftItemController {

    @Resource
    private GiftItemService giftItemService;

    @ApiOperation("分页查询当前房间的供奉虚拟物品记录")
    @GetMapping("/selectGiftItemByMuseumId")
    public R<IPage<GiftItem>> selectGiftItemByMuseumId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam Integer museumId) {
        return R.ok(giftItemService.selectGiftItemByMuseumId(pageNo,pageSize,museumId));
    }
    
    @ApiOperation("查询当前房间的积分排行榜")
    @GetMapping("/selectGiftItemRankByMuseumId")
    public R<List<GiftItemVO>> selectGiftItemRankByMuseumId(@RequestParam Integer museumId) {
        return R.ok(giftItemService.selectGiftItemRankByMuseumId(museumId));
    }
    
    @ApiOperation("查询所有用户的总积分排行榜")
    @GetMapping("/selectGiftItemRank")
    public R<List<GiftItemVO>> selectGiftItemRank() {
        return R.ok(giftItemService.selectGiftItemRank());
    }
    
    @ApiOperation("查询当前纪念馆点亮蜡烛数量")
    @GetMapping("/selectLightedCandlesCountByMuseumId")
    public R<Long> selectLightedCandlesCountByMuseumId(@RequestParam Integer museumId) {
        return R.ok(giftItemService.selectLightedCandlesCountByMuseumId(museumId));
    }
    
    @ApiOperation("分页查询个人供奉的虚拟礼物记录")
    @GetMapping("/selectItemByUserId")
    public R<IPage<GiftItemVO>> selectItemByUserId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam String userId) {
        return R.ok(giftItemService.selectItemByUserId(pageNo, pageSize, userId));
    }
    
    
}
