package com.yue.security.service.impl;

import com.yue.security.Tester;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ProjectName: wx-mp-base
 * @Package: com.yx.mp.base.service.impl
 * @ClassName: WxMpTest
 * @Author: YUE
 * @Description:
 * @Date: 2021/4/21 10:06
 * @Version: 1.0
 */
class StringEncryptorTest extends Tester {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void testStringEncryptor() {
        System.out.println("mysql-url:" + stringEncryptor.encrypt("jdbc:mysql://127.0.0.1:3306/mytest"));
        System.out.println("mysql-username:" + stringEncryptor.encrypt("root"));
        System.out.println("mysql-password:" + stringEncryptor.encrypt("123456"));
    }
}