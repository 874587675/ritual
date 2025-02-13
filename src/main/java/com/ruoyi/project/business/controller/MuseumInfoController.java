package com.ruoyi.project.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.aspectj.lang.annotation.RateLimiter;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.MuseumInfo;
import com.ruoyi.project.business.service.MuseumInfoService;
import com.ruoyi.project.business.vo.MuseumInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "馆藏信息管理模块")
@RequestMapping("/museumInfo")
public class MuseumInfoController {

    @Resource
    private MuseumInfoService museumInfoService;

    @ApiOperation("分页查询馆藏信息")
    @GetMapping("/selectMuseumInfoPage")
    public R<IPage<MuseumInfo>> selectMuseumInfoPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam Integer type) {
        return R.ok(museumInfoService.selectMuseumInfoPage(pageNo, pageSize, type));
    }

    @ApiOperation("分页查询今日生祭")
    @GetMapping("/selectTodayMuseumInfo")
    public R<IPage<MuseumInfo>> selectTodayMuseumInfo(@RequestParam(defaultValue = "1") Integer pageNo,
                                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        return R.ok(museumInfoService.selectTodayMuseumInfo(pageNo, pageSize));
    }

    @ApiOperation("最新纪念馆")
    @GetMapping("/selectNewMuseumInfo")
    public R<IPage<MuseumInfo>> selectNewMuseumInfo(@RequestParam(defaultValue = "1") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        return R.ok(museumInfoService.selectNewMuseumInfo(pageNo, pageSize));
    }

    @RateLimiter(key = "createMuseumInfoSelfByUserId", time = 10, count = 1)
    @ApiOperation("快速创建个人馆")
    @PostMapping("/createMuseumInfoSelfByUserId")
    public R<String> createMuseumInfoSelfByUserId(@RequestBody MuseumInfoVO museumInfoVO) {
        return R.ok(museumInfoService.createMuseumInfoSelfByUserId(museumInfoVO));
    }

    @RateLimiter(key = "createMuseumInfoFamilyByUserId", time = 10, count = 1)
    @ApiOperation("快速创建祠堂馆")
    @PostMapping("/createMuseumInfoFamilyByUserId")
    public R<String> createMuseumInfoFamilyByUserId(@RequestBody MuseumInfoVO museumInfoVO) {
        return R.ok(museumInfoService.createMuseumInfoFamilyByUserId(museumInfoVO));
    }

    @ApiOperation("分页查询个人纪念馆")
    @GetMapping("/selectMuseumInfoSelfByUserId")
    public R<IPage<MuseumInfo>> selectMuseumInfoSelfByUserId(@RequestParam(defaultValue = "1") Integer pageNo, 
                                                             @RequestParam(defaultValue = "10") Integer pageSize, 
                                                             @RequestParam String userId) {
        return R.ok(museumInfoService.selectMuseumInfoSelfByUserId(pageNo,pageSize,userId));
    }
    
    @ApiOperation("分页查询个人创建的祠堂馆")
    @GetMapping("/selectMuseumInfoFamilyByUserId")
    public R<IPage<MuseumInfoVO>> selectMuseumInfoFamilyByUserId(@RequestParam(defaultValue = "1") Integer pageNo, 
                                                              @RequestParam(defaultValue = "10") Integer pageSize, 
                                                              @RequestParam String userId) {
        return R.ok(museumInfoService.selectMuseumInfoFamilyByUserId(pageNo, pageSize, userId));
    }
    
    @ApiOperation("更新馆藏相册信息")
    @PostMapping("/updatePictureByMuseumId")
    public R<String> updatePictureByMuseumId(@RequestParam Integer museumId, @RequestParam String picture) {
        return R.ok(museumInfoService.updatePictureByMuseumId(museumId, picture));
    }
    
    @ApiOperation("更新馆藏视频信息")
    @PostMapping("/updateVideoByMuseumId")
    public R<String> updateVideoByMuseumId(@RequestParam Integer museumId, @RequestParam String video) {
        return R.ok(museumInfoService.updateVideoByMuseumId(museumId, video));
    }
    
    @ApiOperation("查询个人纪念馆的详情")
    @GetMapping("/selectMuseumInfoDetailByMuseumId")
    public R<MuseumInfoVO> selectMuseumInfoDetailByMuseumId(@RequestParam Integer museumId) {
        return R.ok(museumInfoService.selectMuseumInfoDetailByMuseumId(museumId));
    }
    
    @ApiOperation("公开或隐藏纪念馆")
    @PostMapping("/updateMuseumStatusByMuseumId")
    public R<String> updateMuseumStatusByMuseumId(@RequestParam Integer museumId) {
        return R.ok(museumInfoService.updateMuseumStatusByMuseumId(museumId));
    }
    
    @ApiOperation("删除纪念馆")
    @PostMapping("/deleteMuseumInfoByMuseumId")
    public R<String> deleteMuseumInfoByMuseumId(@RequestParam Integer museumId) {
        return R.ok(museumInfoService.deleteMuseumInfoByMuseumId(museumId));
    }
    
    @ApiOperation("查询加入亲友团的纪念馆")
    @GetMapping("/selectJoinFamilyMuseumInfoByUserId")
    public R<IPage<MuseumInfo>> selectJoinFamilyMuseumInfoByUserId(@RequestParam(defaultValue = "1") Integer pageNo, 
                                                                 @RequestParam(defaultValue = "10") Integer pageSize, 
                                                                 @RequestParam String userId) {
        return R.ok(museumInfoService.selectJoinFamilyMuseumInfoByUserId(pageNo, pageSize, userId));
    }

    @ApiOperation("分页查询个人关注的纪念馆信息")
    @GetMapping("/selectMuseumWatchByUserId")
    public R<IPage<MuseumInfoVO>> selectMuseumWatchByUserId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam String userId){
        return R.ok(museumInfoService.selectMuseumWatchByUserId(pageNo, pageSize, userId));
    }
}

