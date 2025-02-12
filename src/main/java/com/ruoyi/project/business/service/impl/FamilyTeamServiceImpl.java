package com.ruoyi.project.business.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.business.domain.FamilyTeam;
import com.ruoyi.project.business.mapper.FamilyTeamMapper;
import com.ruoyi.project.business.service.FamilyTeamService;
import com.ruoyi.project.business.util.aliyun.oss.OssUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class FamilyTeamServiceImpl extends ServiceImpl<FamilyTeamMapper, FamilyTeam> implements FamilyTeamService{
    @Resource
    private FamilyTeamMapper familyTeamMapper;
    @Resource
    private WxMaService wxMaService;
    @Resource
    private OssUtil ossUtil;
   
    @Override
    public String generateFamilyQRCode(Integer teamId,String userId) {
        try {
            // 获取亲友团的信息
            FamilyTeam familyTeam = familyTeamMapper.selectById(teamId);
            // 检查是否已有亲友团图片
            String existingQrCode = familyTeam.getQrCode();

            // 判断是否已经有生成的二维码
            if (StringUtils.isNotBlank(existingQrCode)) {
                return existingQrCode;
            }

            // 生成二维码
            BufferedImage qrCode = generateQRCode(teamId,userId);

            // 合成最终的邀请图
            BufferedImage finalImage = combineImageWithQRCode(qrCode);

            // 上传图片并获取图片URL
            String imageUrl = uploadImageToOss(finalImage);

            // 更新用户邀请二维码
            updateTeamQRCode(teamId, imageUrl);

            return imageUrl;
        } catch (Exception e) {
            log.error("生成用户邀请图异常", e);
            return "生成错误";
        }
    }

    private BufferedImage generateQRCode(Integer teamId,String userId) throws IOException, WxErrorException {
        // 通过微信服务生成二维码
        String qrCodeUrl = "pagesA/login/login?teamId=" + teamId + "&userId=" + userId;
        return ImageIO.read(wxMaService.getQrcodeService().createWxaCode(qrCodeUrl, 440));
    }

    private BufferedImage combineImageWithQRCode(BufferedImage qrCode) throws IOException {
        // 加载海报背景
        BufferedImage imageWithQr = ImageIO.read(new ClassPathResource("static/poster.png").getInputStream());

        // 在海报上绘制二维码
        Graphics2D graphics = imageWithQr.createGraphics();
        graphics.drawImage(qrCode, 130, 660, 500, 500, null);
        graphics.dispose();  // 完成绘制后释放资源

        return imageWithQr;
    }

    private String uploadImageToOss(BufferedImage finalImage) throws IOException {
        // 将图片转成字节流并上传到OSS
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(finalImage, "png", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return ossUtil.uploadFileByType(new MockMultipartFile("image.png", "image.png", "image/png", imageBytes),"image").getUrl();
    }

    private void updateTeamQRCode(Integer teamId, String imageUrl) {
        // 更新亲友团的邀请二维码信息
        familyTeamMapper.update(new LambdaUpdateWrapper<FamilyTeam>()
                .eq(FamilyTeam::getId, teamId)
                .set(FamilyTeam::getQrCode, imageUrl)
        );
    }

}
