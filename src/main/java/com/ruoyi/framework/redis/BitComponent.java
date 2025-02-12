package com.ruoyi.framework.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @ClassName:BitComponent
 * @description:
 * @author: zgc
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
@Slf4j
public class BitComponent {
    
    @Autowired
    public RedisTemplate redisTemplate;

    private static final String SIGN_IN_KEY = "user:sign_in:%s"; // 用户签到位图的键
    private static final String LAST_SIGN_IN_KEY = "user:last_sign_in:%s"; // 用户最后签到日期的键

    /**
     * 用户签到
     *
     * @param userId 用户ID
     * @return 是否签到成功
     */
    public boolean signIn(String userId) {
        LocalDate today = LocalDate.now();
        String signInKey = String.format(SIGN_IN_KEY, userId);
        String lastSignInKey = String.format(LAST_SIGN_IN_KEY, userId);

        // 获取上次签到日期
        String lastSignInDateStr = (String) redisTemplate.opsForValue().get(lastSignInKey);
        LocalDate lastSignInDate = lastSignInDateStr != null ? LocalDate.parse(lastSignInDateStr) : null;
        
        // 检查是否已经签到
        if (lastSignInDate != null && lastSignInDate.isEqual(today)) {
            log.info("今天已经签到!");
            return false; // 今天已经签到
        }

        // 计算从上次签到到今天的天数差
        long daysBetween = lastSignInDate != null ? ChronoUnit.DAYS.between(lastSignInDate, today) : 1;
        
        log.info("daysBetween: " + daysBetween);
        if (daysBetween == 1) {
            // 连续签到，更新位图
            log.info("连续签到!");
            redisTemplate.opsForValue().setBit(signInKey, today.toEpochDay(), true);
        } else if (daysBetween > 1) {
            // 中断签到，重置位图
            log.info("中断签到!");
            redisTemplate.delete(signInKey);
            redisTemplate.opsForValue().setBit(signInKey, today.toEpochDay(), true);
        } else {
            // 第一次签到
            log.info("第一次签到!");
            redisTemplate.opsForValue().setBit(signInKey, today.toEpochDay(), true);
        }
        // 更新最后签到日期
        redisTemplate.opsForValue().set(lastSignInKey, today.toString());
        return true;
    }

    /**
     * 获取用户连续签到天数
     *
     * @param userId 用户ID
     * @return 连续签到天数
     */
    public int getContinuousSignInDays(String userId) {
        String signInKey = String.format(SIGN_IN_KEY, userId);
        LocalDate today = LocalDate.now();
        long todayEpochDay = today.toEpochDay();
        int continuousDays = 0;
        
        for (long i = todayEpochDay; i >= 0; i--) {
            if (Boolean.TRUE.equals(redisTemplate.opsForValue().getBit(signInKey, i))) {
                continuousDays++;
            } else {
                break;
            }
        }
        return continuousDays;
    }

    /**
     * 获取用户总签到天数
     *
     * @param userId 用户ID
     * @return 总签到天数
     */
    public Long getTotalSignInDays(String userId) {
        String signInKey = String.format(SIGN_IN_KEY, userId);
        return (Long) redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.bitCount(signInKey.getBytes()) // 执行 bitCount 操作
        );
    }
    
    
}
