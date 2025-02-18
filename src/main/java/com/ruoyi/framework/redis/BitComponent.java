package com.ruoyi.framework.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String SIGN_IN_PERIOD_KEY = "user:sign_in_period:%s";  // 用户签到周期的键


    /**
     * 判断用户今天是否已经签到
     *
     * @param userId 用户ID
     * @return 今天是否已签到
     */
    public boolean hasSignedInToday(String userId) {
        LocalDate today = LocalDate.now();  //获取当前日期，没有时间 (如2022-01-01)
        String signInKey = String.format(SIGN_IN_KEY, userId);
        return Boolean.TRUE.equals(redisTemplate.opsForValue().getBit(signInKey, today.toEpochDay()));
    }

    /**
     * 用户签到并记录
     *
     * @param userId 用户ID
     * @return 是否签到成功
     */
    public boolean signIn(String userId) {
        LocalDate today = LocalDate.now();
        String signInKey = String.format(SIGN_IN_KEY, userId);
        String lastSignInKey = String.format(LAST_SIGN_IN_KEY, userId);
        String signInPeriodKey = String.format(SIGN_IN_PERIOD_KEY, userId);

        // 获取上次签到日期
        String lastSignInDateStr = (String) redisTemplate.opsForValue().get(lastSignInKey);
        LocalDate lastSignInDate = lastSignInDateStr != null ? LocalDate.parse(lastSignInDateStr) : null;

        // 获取当前签到周期（每7天一个周期）
        String currentPeriod = (String) redisTemplate.opsForValue().get(signInPeriodKey);
        boolean isNewPeriod = (currentPeriod == null || !currentPeriod.equals(today.toString()));

        if (isNewPeriod) {
            log.info("进入新周期，重置签到!"); // 如果是新周期，重置签到周期
            redisTemplate.delete(signInKey);  // 删除上一个周期的签到数据
            redisTemplate.opsForValue().set(signInPeriodKey, today.toString());  // 设置新的周期起始日期
        }

        // 计算从上次签到到今天的天数差
        long daysBetween = lastSignInDate != null ? ChronoUnit.DAYS.between(lastSignInDate, today) : 1;
        log.info("上次签到到今天的天数差: " + daysBetween);
        if (daysBetween == 1) {
            log.info("连续签到!");
            redisTemplate.opsForValue().setBit(signInKey, today.toEpochDay(), true);// 连续签到，更新位图
        } else if (daysBetween > 1) {
            log.info("中断签到!");
            redisTemplate.delete(signInKey);    // 中断签到，重置位图
            redisTemplate.opsForValue().setBit(signInKey, today.toEpochDay(), true);
        } else {
            log.info("第一次签到!");
            redisTemplate.opsForValue().setBit(signInKey, today.toEpochDay(), true);// 第一次签到
        }
        // 更新最后签到日期
        redisTemplate.opsForValue().set(lastSignInKey, today.toString());

        // 如果连续签到天数达到 7 天，重置周期
        int continuousDays = getContinuousSignInDays(userId);
        if (continuousDays == 7) {
            log.info("连续签到达到7天，重置为新的周期!");
            redisTemplate.opsForValue().set(signInPeriodKey, today.toString());  // 更新周期
        }

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
        // 返回连续签到天数，最多为 7 天
        return Math.min(continuousDays, 7);
    }

    /**
     * 获取用户当月签到的总天数
     *
     * @param userId 用户ID
     * @return 当月总签到天数
     */
    public long getTotalSignInDaysThisMonth(String userId) {
        String signInKey = String.format(SIGN_IN_KEY, userId);
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        long firstDayEpochDay = firstDayOfMonth.toEpochDay();
        long todayEpochDay = LocalDate.now().toEpochDay();

        long totalSignInDays = 0;
        for (long i = firstDayEpochDay; i <= todayEpochDay; i++) {
            if (Boolean.TRUE.equals(redisTemplate.opsForValue().getBit(signInKey, i))) {
                totalSignInDays++;
            }
        }
        return totalSignInDays;
    }


}
