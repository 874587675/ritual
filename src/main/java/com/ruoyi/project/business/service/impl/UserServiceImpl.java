package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.service.UserService;
import com.ruoyi.project.business.util.aliyun.sms.SmsUtil;
import com.ruoyi.project.system.service.SysConfigService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Pattern;

import static com.ruoyi.common.constant.UserConstants.EMAIL_REGEX;

@Service
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


    @Override
    public String loginByUsername(String username, String password, String code, String uuid) {

        // 验证码校验
        validateCaptcha(username, code, uuid);
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
}
