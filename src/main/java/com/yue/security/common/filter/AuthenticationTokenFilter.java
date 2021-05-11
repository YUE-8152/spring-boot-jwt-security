package com.yue.security.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yue.security.bean.user.LoginUserPo;
import com.yue.security.bean.user.TokenInfo;
import com.yue.security.bean.user.UserContext;
import com.yue.security.common.core.Result;
import com.yue.security.common.core.RsaKeyProperties;
import com.yue.security.common.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.common.filter
 * @ClassName: AuthenticationFilter
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 17:32
 * @Version: 1.0
 */
public class AuthenticationTokenFilter extends BasicAuthenticationFilter {
    private RsaKeyProperties prop;

    public AuthenticationTokenFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        super(authenticationManager);
        this.prop = prop;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        if (!StringUtils.isNoneBlank(token)) {
            //如果携带错误的token，则给用户提示请登录！
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Result fail = Result.fail(HttpServletResponse.SC_FORBIDDEN, "请先登录！");
            out.write(new ObjectMapper().writeValueAsString(fail));
            out.flush();
            out.close();
        } else {
            //如果携带了正确格式的token要先得到token
            //验证tken是否正确
            try {
                TokenInfo<LoginUserPo> tokenInfo = JWTUtils.getTokenInfo(token, prop.getPublicKey(), LoginUserPo.class);
                LoginUserPo user = tokenInfo.getUserInfo();
                if (user != null) {
                    UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                    UserContext.setUser(user);
                    chain.doFilter(request, response);
                }
            } catch (Exception e) {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Result fail = Result.fail(HttpServletResponse.SC_UNAUTHORIZED, "无效token！");
                out.write(new ObjectMapper().writeValueAsString(fail));
                out.flush();
                out.close();
            }
        }
    }
}
