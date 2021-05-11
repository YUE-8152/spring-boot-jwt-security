package com.yue.security.service.impl;

import com.google.common.collect.Lists;

import com.yue.security.bean.test.SysUserPo;
import com.yue.security.bean.user.LoginUserPo;
import com.yue.security.dao.test.TestMapper;
import com.yue.security.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @ProjectName: springboot-authority
 * @Package: com.yue.base.service.impl.mp
 * @ClassName: SysUserServiceImpl
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 15:34
 * @Version: 1.0
 */
@DubboService
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private TestMapper mapper;

    @Override
    public List selectList() {
        return mapper.selectAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUserPo sysUserPo = mapper.selectByUsername(s);
        LoginUserPo po = new LoginUserPo();
        po.setId(sysUserPo.getUserId());
        po.setUserName(sysUserPo.getUserName());
        po.setPassword(sysUserPo.getPassword());
        po.setRoles(Lists.newArrayList());
        return po;
    }
}
