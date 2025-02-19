package com.ruoyi.project.business.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.HeartTree;
import com.ruoyi.project.business.service.HeartTreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-心灵树洞信息管理")
@RequestMapping("/system/heartTree")
public class AdminHeartTreeController {

    @Resource
    private HeartTreeService heartTreeService;

    @ApiOperation("分页查询出心灵树洞的信息")
    @GetMapping("/selectAllHeartTree")
    public R<IPage<HeartTree>> selectAllHeartTree(@RequestParam(defaultValue = "1") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(heartTreeService.selectAllHeartTree(pageNo,pageSize));
    }

    @ApiOperation("修改心灵树洞信息")
    @PostMapping("/updateHeartTree")
    public R<Boolean> updateHeartTree(@RequestBody HeartTree heartTree){
        return R.ok(heartTreeService.updateById(heartTree));
    }

    @ApiOperation("查看心灵树洞的详情")
    @GetMapping("/selectHeartTreeDetails")
    public R<HeartTree> selectHeartTreeDetails(@RequestParam Integer treeId) {
        return R.ok(heartTreeService.selectHeartTreeDetails(treeId));
    }
}
