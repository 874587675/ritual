package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.service.ObituaryNoticeService;
import com.ruoyi.project.business.vo.ObituaryNoticeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "讣告公告信息管理模块")
@RequestMapping("/obituaryNotice")
public class ObituaryNoticeController {

    @Resource
    private ObituaryNoticeService obituaryNoticeService;

    @ApiOperation("分页查询自己发布的讣告")
    @GetMapping("/selectObituaryNoticeByUserId")
    public R<ObituaryNoticeVO> selectObituaryNoticeByUserId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam String userId) {
        return R.ok(obituaryNoticeService.selectObituaryNoticeByUserId(pageNo, pageSize, userId));
    }
    
    @ApiOperation("详细查询发布的讣告")
    @GetMapping("/selectObituaryNoticeById")
    public R<ObituaryNoticeVO> selectObituaryNoticeById(@RequestParam Integer id){
        return R.ok(obituaryNoticeService.selectObituaryNoticeById(id));
    }
    
    @ApiOperation("新增自己发布的讣告")
    @PostMapping("/addObituaryNotice")
    public R<String> addObituaryNotice(@RequestBody ObituaryNoticeVO obituaryNoticeVO){
        return R.ok(obituaryNoticeService.addObituaryNotice(obituaryNoticeVO));
    }
    
    @ApiOperation("删除自己发布的讣告")
    @PostMapping("/deleteObituaryNotice")
    public R<String> deleteObituaryNotice(@RequestParam Integer id){
        return R.ok(obituaryNoticeService.deleteObituaryNotice(id));
    }
    
    @ApiOperation("新建点灯追思订单（下单")
    @PostMapping("/orderObituaryNotice")
    public R<Map<String, String>> orderObituaryNotice(@RequestBody WeChatJsapiPayVO weChatJsapiPayVO){
        return R.ok(obituaryNoticeService.orderObituaryNotice(weChatJsapiPayVO));
    }
    
    @ApiOperation("支付点灯追思订单")
    @PostMapping("/payObituaryNoticeCallback")
    public R<String> payObituaryNoticeCallback(@RequestBody HttpServletRequest request){
        return R.ok(obituaryNoticeService.payObituaryNoticeCallback(request));
    }
    
    @ApiOperation("查询讣告赠送礼物信息")
    @GetMapping("/queryObituaryNoticeForGiftItems")
    public R<ObituaryNoticeVO> queryObituaryNoticeForGiftItems(@RequestParam Integer id){
        return R.ok(obituaryNoticeService.queryObituaryNoticeForGiftItems(id));
    }
}
