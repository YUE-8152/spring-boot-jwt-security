package com.yue.security.common.config;

import com.yue.security.common.core.RsaKeyProperties;
import com.yue.security.common.filter.AuthenticationTokenFilter;
import com.yue.security.common.filter.UserAuthenticationFilter;
import com.yue.security.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.common.config
 * @ClassName: WebSecurityConfig
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 18:34
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @DubboReference
    private SysUserService userService;

    @Autowired
    private RsaKeyProperties prop;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //指定认证对象的来源
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    //SpringSecurity配置信息
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()   // 关闭csrf验证(防止跨站请求伪造攻击)
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .anyRequest()  //其他任何请求
                .authenticated() //都需要身份认证
                .and()
                .addFilter(new AuthenticationTokenFilter(super.authenticationManager(), prop))  // Token解析并生成authentication身份信息过滤器
                .addFilter(new UserAuthenticationFilter(super.authenticationManager(), prop))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 将session策略设置为无状态的,通过token进行登录认证
        http.logout() //用户注销, 清空session
                .logoutUrl("/logout");//自定义注销请求路径
//                .logoutSuccessHandler(new UrlLogoutSuccessHandler()); //注销成功处理器(前后端分离)：返回状态码200
    }
}
