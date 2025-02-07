package com.ruoyi.project.business.util.aliyun.oss;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Component
@EnableFileStorage
@Slf4j
@RequiredArgsConstructor
public class OssUtil {
    @Resource
    private FileStorageService fileStorageService;
    
    private final Tika tika;

    public FileInfo uploadFileByType(MultipartFile file, String type) {
        // 使用 Tika 来获取文件的 MIME 类型
        String mimeType = getMimeType(file);
        log.info("文件类型：{}", mimeType);
        // 判断文件类型是否符合要求
        if (mimeType == null || !mimeType.startsWith(type)) {
            throw new ServiceException("只允许上传" + type + "文件");
        }
        // 根据传入的类型参数选择存储路径
        String pathPrefix = determinePathPrefix(type);
        // 创建存储路径
        String path = pathPrefix + DateUtil.format(new Date(), "yyyy-MM-dd") + "/";
        // 文件保存时使用的文件名
        String filename = file.getOriginalFilename();
        // 调用文件存储服务上传文件
        return fileStorageService.of(file)
                .setPath(path)  // 设置文件路径
                .setSaveFilename(filename)  // 设置文件名
                .upload();
    }

    /**
     * 使用 Tika 来检测文件的 MIME 类型
     */
    private String getMimeType(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            return tika.detect(inputStream);
        } catch (IOException e) {
            throw new ServiceException("文件类型检测失败: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close(); //注意:这里要手动关闭输入流，不然在清理缓存文件时会报错
                } catch (IOException e) {
                    // 日志记录关闭流时的异常
                }
            }
        }
    }

    /**
     * 根据文件类型决定存储的主路径
     */
    private String determinePathPrefix(String type) {
        switch (type) {
            case "image":
                return "images/";
            case "video":
                return "videos/";
            case "audio":
                return "audios/";
            case "document":
                return "documents/";
            case "archive":
                return "archives/";
            case "file":
                return "files/";
            default:
                throw new ServiceException("不支持该文件类型");
        }
    }

    
}
