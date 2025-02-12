package com.ruoyi.project.business.test;

import com.ruoyi.framework.redis.BitComponent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName:test
 * @description:
 * @author: zgc
 **/
@Service
public class Test {
    @Resource
    private BitComponent bitComponent;
    public Boolean run() throws Exception {
        String userId = "user123";

        // 模拟连续签到
        bitComponent.signIn(userId);
        System.out.println("连续签到天数: " + bitComponent.getContinuousSignInDays(userId));

        // 再次签到
        bitComponent.signIn(userId);
        System.out.println("连续签到天数: " + bitComponent.getContinuousSignInDays(userId));

        // 获取总签到天数
        System.out.println("总签到天数: " + bitComponent.getTotalSignInDays(userId));
        
        return true;
    }
}
