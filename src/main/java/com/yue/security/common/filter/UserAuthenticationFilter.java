package com.yue.security.common.filter;
import com.google.common.collect.Lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yue.security.bean.user.LoginUserPo;
import com.yue.security.bean.user.SysRolePo;
import com.yue.security.common.core.Result;
import com.yue.security.common.core.RsaKeyProperties;
import com.yue.security.common.utils.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.common.filter
 * @ClassName: UserAuthenticationFilter
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 17:33
 * @Version: 1.0
 */
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private RsaKeyProperties prop;

    public UserAuthenticationFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        this.authenticationManager = authenticationManager;
        this.prop = prop;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginUserPo sysUser = new ObjectMapper().readValue(request.getInputStream(), LoginUserPo.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
            return authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Result fail = Result.fail(HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误！");
                out.write(new ObjectMapper().writeValueAsString(fail));
                out.flush();
                out.close();
            } catch (Exception outEx) {
                outEx.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 获取登录成功后的user信息，生成token
        LoginUserPo user = (LoginUserPo) authResult.getPrincipal();
        user.setRoles(Lists.newArrayList());
        String token = JWTUtils.generateTokenExpireInSeconds(user, prop.getPrivateKey(), 30 * 60);
        response.addHeader("token", token);
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            Result success = Result.success(token);
            out.write(new ObjectMapper().writeValueAsString(success));
            out.flush();
            out.close();
        } catch (Exception outEx) {
            outEx.printStackTrace();
        }
    }
}
