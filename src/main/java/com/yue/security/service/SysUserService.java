package com.yue.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @ProjectName: springboot-authority
 * @Package: com.yue.base.service.impl.mp
 * @ClassName: SysUserService
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 15:32
 * @Version: 1.0
 */
public interface SysUserService extends UserDetailsService {
    List selectList();
}
