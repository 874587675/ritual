package com.ruoyi.project.business.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.*;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.project.business.domain.FamilyTeam;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.service.UserService;
import com.ruoyi.project.business.util.aliyun.oss.OssUtil;
import com.ruoyi.project.business.util.aliyun.sms.SmsUtil;
import com.ruoyi.project.system.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import static com.ruoyi.common.constant.UserConstants.EMAIL_REGEX;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private TokenService tokenService;
    @Resource
    private SmsUtil smsUtil;
    @Resource(name = "customAuthenticationManager")
    private AuthenticationManager authenticationManager;
    @Resource
    private SysConfigService configService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private WxMiniAppService wxMiniAppService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WxMaService wxMaService;
    @Resource
    private OssUtil ossUtil;


    @Override
    public String loginByUsername(String username, String password, String code, String uuid) {
        // 验证码校验
//        validateCaptcha(username, code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);

        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);

            if (captcha == null) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new CaptchaExpireException();
            }
            redisCache.deleteObject(verifyKey);
            if (!code.equalsIgnoreCase(captcha)) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        }
    }

    /**
     * 登录前置校验
     *
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
            throw new BlackListException();
        }
    }

    @Override
    public String loginByPhone(String phone, String code) {
        //检查短信验证码
        if (!smsUtil.verifyPhoneCode(phone, code)) {
            throw new ServiceException("输入的验证码有误，请稍后再试");
        }
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone).or().eq(User::getHistoryPhone, phone));
        if (user == null) {
            throw new ServiceException("手机号不存在，请先注册或绑定手机号");
        }
        return tokenService.createToken(new LoginUser(user));
    }

    @Override
    public String registerByUsername(String username, String password, String code, String uuid) {
        String message = "";
        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            validateCaptcha(username, code, uuid);
        }
        if (StringUtils.isEmpty(username)) {
            message = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            message = "用户密码不能为空";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            message = "账户长度必须在3到20个字符之间";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            message = "密码长度必须在6到20个字符之间";
        } else if (!this.checkUsername(username)) {
            message = "注册账号'" + username + "'失败，此注册账号已存在";
        } else {
            User user = User.builder()
                    .username(username)
                    .password(SecurityUtils.encryptPassword(password)).build();
            boolean regFlag = this.save(user);
            if (!regFlag) {
                message = "注册失败,请联系系统管理人员";
            } else {
                message = "注册成功";
            }
        }
        return message;
    }

    @Override
    public String registerByPhone(String phone, String password, String code) {
        String message = "";
        if (StringUtils.isEmpty(phone)) {
            message = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            message = "用户密码不能为空";
        } else if (phone.length() != UserConstants.MOBILE_MIN_LENGTH) {
            message = "手机号码长度必须是11个字符";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            message = "密码长度必须在6到20个字符之间";
        } else if (!this.checkPhone(phone)) {
            message = "注册账号'" + phone + "'失败，此注册手机号已存在";
        } else {
            User user = User.builder()
                    .phone(phone)
                    .password(SecurityUtils.encryptPassword(password)).build();
            boolean regFlag = this.save(user);
            if (!regFlag) {
                message = "注册失败,请联系系统管理人员";
            } else {
                message = "注册成功";
            }
        }
        return message;
    }

    @Override
    public String registerByEmail(String email, String password, String code) {
        String message = "";
        if (StringUtils.isEmpty(email)) {
            message = "邮箱名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            message = "用户密码不能为空";
        } else if (Pattern.compile(EMAIL_REGEX).matcher(email).matches()) {
            message = "邮箱格式不正确";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            message = "密码长度必须在6到20个字符之间";
        } else if (!this.checkEmail(email)) {
            message = "注册账号'" + email + "'失败，此注册邮箱已存在";
        } else {
            User user = User.builder()
                    .email(email)
                    .password(SecurityUtils.encryptPassword(password)).build();
            boolean regFlag = this.save(user);
            if (!regFlag) {
                message = "注册失败,请联系系统管理人员";
            } else {
                message = "注册成功";
            }
        }
        return message;
    }

    @Override
    public Boolean checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new ServiceException("用户名不能为空");
        }
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return user != null;
    }

    @Override
    public Boolean checkPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new ServiceException("手机号不能为空");
        }
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone).or().eq(User::getHistoryPhone, phone));
        return user != null;
    }

    @Override
    public Boolean checkEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new ServiceException("邮箱不能为空");
        }
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
        return user != null;
    }

    @Override
    public String loginByWx(String code, String phone, String phoneCode) throws WxErrorException {
        // 通过 code 获取 openid 和 session_key
        WxMaJscode2SessionResult sessionInfo = wxMiniAppService.getSessionInfo(code);
        String openId = sessionInfo.getOpenid();

        //session_key 是敏感信息，不能直接返回给小程序端。
        //建议在服务器端生成自定义登录态（如 JWT），并将登录态返回给小程序端。
//        String sessionKey = sessionInfo.getSessionKey();

        if (!smsUtil.verifyPhoneCode(phone, phoneCode)) {
            throw new ServiceException("输入的验证码有误，请稍后再试");
        }
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone).or().eq(User::getHistoryPhone, phone));
        if (user == null) {
            user = User.builder().phone(phone).openId(openId).build();
            // 保存用户信息
            save(user);
        }
        return tokenService.createToken(new LoginUser(user));
    }

    @Override
    @Transactional
    public String updateUserInfoByUserId(User user) {
        try {
            User userResult = getById(user.getId());
            if (userResult == null) {
                log.error("该用户不存在 用户ID：{}", user.getId());
                throw new ServiceException("该用户不存在");
            } else {
                boolean updateResult = updateById(user);
                if (updateResult) {
                    log.info("用户信息修改成功,用户ID：{}", user.getId());
                    return "用户信息修改成功";
                } else {
                    log.error("用户信息修改失败,用户ID：{}", user.getId());
                    return "用户信息修改失败";
                }
            }
        } catch (Exception e) {
            log.error("修改用户信息发生异常,用户ID：{}，异常：{}", user.getId(), e.getMessage());
            throw new ServiceException("修改用户信息发生异常");
        }
    }

    @Override
    public String generateShareQRCode(String userId) {
        try {
            // 获取亲友团的信息
            User user = userMapper.selectById(userId);
            // 检查是否已有亲友团图片
            String existingQrCode = user.getQrCode();

            // 判断是否已经有生成的二维码
            if (StringUtils.isNotBlank(existingQrCode)) {
                return existingQrCode;
            }

            // 生成二维码
            BufferedImage qrCode = generateQRCode(userId);

            // 合成最终的邀请图
            BufferedImage finalImage = combineImageWithQRCode(qrCode);

            // 上传图片并获取图片URL
            String imageUrl = uploadImageToOss(finalImage);

            // 更新用户邀请二维码
            updateTeamQRCode(userId,imageUrl);

            return imageUrl;
        } catch (Exception e) {
            log.error("生成用户邀请图异常", e);
            return "生成错误";
        }
    }

    private BufferedImage generateQRCode(String userId) throws IOException, WxErrorException {
        // 通过微信服务生成二维码
        String qrCodeUrl = "pagesA/login/login?&userId=" + userId;
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

    private void updateTeamQRCode(String userId, String imageUrl) {
        // 更新亲友团的邀请二维码信息
        userMapper.update(new LambdaUpdateWrapper<User>()
                .eq(User::getId, userId)
                .set(User::getQrCode, imageUrl)
        );
    }
}
