package com.yue.security.bean.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.bean.user
 * @ClassName: LoginUserPo
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 16:50
 * @Version: 1.0
 */
public class LoginUserPo implements UserDetails, Serializable {
    private static final long serialVersionUID = 1207479408840127400L;
    private Integer id;

    private String userName;

    private String password;

    private List<SysRolePo> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRolePo> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRolePo> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auth = new ArrayList<>();
        String username = this.getUsername();
        if (username != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
            auth.add(authority);
        }
        return auth;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
