package com.yue.security.bean.user;

import java.util.Date;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.bean.user
 * @ClassName: TokenInfo
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 18:14
 * @Version: 1.0
 */
public class TokenInfo<T> {
    private String id;
    private T userInfo;
    private Date expiration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
