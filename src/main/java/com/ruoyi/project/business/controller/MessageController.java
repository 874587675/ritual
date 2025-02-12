package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Message;
import com.ruoyi.project.business.service.MessageService;
import com.ruoyi.project.business.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "消息模块管理")
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @ApiOperation("房间内留言")
    @PostMapping("/addMessage")
    public R<String> addMessage(@RequestBody MessageVO messageVO) {
        return R.ok(messageService.addMessage(messageVO));
    }

    @ApiOperation("分页查询顶级留言")
    @GetMapping("/getTopMessages")
    public R<IPage<Message>> getTopMessages(@RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @RequestParam Integer roomId) {
        return R.ok(messageService.getTopMessages(pageNo, pageSize, roomId));
    }

    @ApiOperation("分页查询子留言")
    @GetMapping("/getSubMessages")
    public R<IPage<MessageVO>> getSubMessages(@RequestParam(defaultValue = "1") Integer pageNo,
                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                              @RequestParam Integer parentId) {
        return R.ok(messageService.getSubMessages(pageNo, pageSize, parentId));
    }
    
    
    
    @ApiOperation("删除留言(逻辑删除)")
    @PostMapping("/deleteMessage")
    public R<String> deleteMessage(@RequestParam Integer id) {
        return R.ok(messageService.deleteMessage(id));
    }

}
