package com.yue.security.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.common.utils
 * @ClassName: BCryptPasswordEncoderUtils
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/11 10:37
 * @Version: 1.0
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123456";
        String pwd = encodePassword(password);
        System.out.println(pwd);
    }
}
