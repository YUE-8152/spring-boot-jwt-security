package com.yue.security.bean.user;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.bean.user
 * @ClassName: SysRolePo
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 16:51
 * @Version: 1.0
 */
public class SysRolePo implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = -3996454655065856913L;
    private Integer id;

    private String roleName;

    private String roleDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
