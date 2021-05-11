package com.yue.security.bean.user;

public class UserContext {
    private static ThreadLocal<LoginUserPo> userHolder = new ThreadLocal<LoginUserPo>();

    public static void setUser(LoginUserPo loginUser) {
        userHolder.set(loginUser);
    }

    public static LoginUserPo getUser() {
        return userHolder.get();
    }
}
