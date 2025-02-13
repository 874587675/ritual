package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.HeartTree;
import com.ruoyi.project.business.domain.Message;
import com.ruoyi.project.business.service.HeartTreeService;
import com.ruoyi.project.business.vo.HeartTreeVO;
import com.ruoyi.project.business.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "心灵树洞信息管理")
@RequestMapping("/heartTree")
public class HeartTreeController {

    @Resource
    private HeartTreeService heartTreeService;

    @ApiOperation("分页查询出心灵树洞的信息")
    @GetMapping("/selectAllHeartTree")
    public R<IPage<HeartTree>> selectAllHeartTree(@RequestParam(defaultValue = "1") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(heartTreeService.selectAllHeartTree(pageNo,pageSize));
    }
    
    @ApiOperation("发布心灵树洞信息")
    @PostMapping("/addHeartTree")
    public R<String> addHeartTree(@RequestBody HeartTreeVO heartTreeVO) {
        return R.ok(heartTreeService.addHeartTree(heartTreeVO));
    }
    
    @ApiOperation("查看心灵树洞的详情")
    @GetMapping("/selectHeartTreeDetails")
    public R<HeartTree> selectHeartTreeDetails(@RequestParam Integer treeId) {
        return R.ok(heartTreeService.selectHeartTreeDetails(treeId));
    }
    
    @ApiOperation("分页查看心灵树洞的顶级评论留言")
    @GetMapping("/selectTopMessages")
    public R<IPage<Message>> selectTopMessages(@RequestParam(defaultValue = "1") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam Integer treeId) {
        return R.ok(heartTreeService.selectTopMessages(pageNo, pageSize, treeId));
    }

    @ApiOperation("分页查看心灵树洞查询子留言")
    @GetMapping("/getSubMessages")
    public R<IPage<MessageVO>> getSubMessages(@RequestParam(defaultValue = "1") Integer pageNo,
                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                              @RequestParam Integer topId) {
        return R.ok(heartTreeService.getSubMessages(pageNo, pageSize, topId));
    }
    
    @ApiOperation("删除个人发布的心灵树洞")
    @PostMapping("/deleteHeartTree")
    public R<String> deleteHeartTree(@RequestParam Integer treeId) {
        return R.ok(heartTreeService.deleteHeartTree(treeId));
    }
}
