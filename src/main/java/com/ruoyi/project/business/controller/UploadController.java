package com.ruoyi.project.business.controller;


import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.util.aliyun.oss.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RequestMapping("/upload")
@RestController
@Api(tags = "上传文件模块")
public class UploadController {
    @Resource
    private OssUtil ossUtil;
    @ApiOperation("上传图片")
    @PostMapping("/uploadImage")
    public R<String> uploadImage(@RequestParam MultipartFile file) {
        String url = ossUtil.uploadFileByType(file,"image").getUrl();
        return R.ok(url);
    }
}
