package com.ruoyi.common.utils.random;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Component
public class RandomUtils {
    private static final String NUMERIC = "0123456789";
    private static final String ALPHABETIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALPHANUMERIC = NUMERIC + ALPHABETIC;

    private static final SecureRandom random = new SecureRandom();

    /**
     * 生成指定长度的随机数字字符串
     *
     * @param length 生成的位数
     * @return 随机数字字符串
     */
    public String generateNumeric(int length) {
        return generateRandomString(NUMERIC, length);
    }

    /**
     * 生成指定长度的随机字母字符串
     *
     * @param length 生成的位数
     * @return 随机字母字符串
     */
    public String generateAlphabetic(int length) {
        return generateRandomString(ALPHABETIC, length);
    }

    /**
     * 生成指定长度的随机字母和数字字符串
     *
     * @param length 生成的位数
     * @return 随机字母和数字字符串
     */
    public String generateAlphanumeric(int length) {
        return generateRandomString(ALPHANUMERIC, length);
    }

    /**
     * 生成随机字符串
     *
     * @param characterSet 字符集
     * @param length       生成的位数
     * @return 随机字符串
     */
    private String generateRandomString(String characterSet, int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            result.append(characterSet.charAt(index));
        }
        return result.toString();
    }
}
