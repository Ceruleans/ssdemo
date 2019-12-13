package com.demo.ssdemo.config;

import com.demo.ssdemo.core.LoginValidateAuthenticationProvider;
import com.demo.ssdemo.core.handler.LoginFailureHandler;
import com.demo.ssdemo.core.handler.LoginSuccessHandler;
import com.demo.ssdemo.core.handler.PerAccessDeniedHandler;
import com.demo.ssdemo.sys.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author OZY
 * @Date 2019/08/08 13:59
 * @Description
 * @Version V1.0
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 数据源
     */
    @Resource
    private DataSource dataSource;

    /**
     * 用户业务层
     */
    @Resource
    private UserService userService;

    /**
     * 自定义认证
     */
    @Resource
    private LoginValidateAuthenticationProvider loginValidateAuthenticationProvider;

    /**
     * 登录成功handler
     */
    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    /**
     * 登录失败handler
     */
    @Resource
    private LoginFailureHandler loginFailureHandler;

    /**
     * 权限不足handler
     */
    @Resource
    private PerAccessDeniedHandler perAccessDeniedHandler;


    /**
     * 权限核心配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //基础设置
        http.httpBasic()//配置HTTP基本身份验证
            .and()
                .authorizeRequests()
                .anyRequest().authenticated()//所有请求都需要认证
            .and()
                .formLogin() //登录表单
                .loginPage("/login")//登录页面url
                .loginProcessingUrl("/login")//登录验证url
                .defaultSuccessUrl("/index")//成功登录跳转
                .successHandler(loginSuccessHandler)//成功登录处理器
                .failureHandler(loginFailureHandler)//失败登录处理器
                .permitAll()//登录成功后有权限访问所有页面
            .and()
                .exceptionHandling().accessDeniedHandler(perAccessDeniedHandler)//设置权限不足handler
            .and()
                .rememberMe()//记住我功能
                .userDetailsService(userService)//设置用户业务层
                .tokenRepository(persistentTokenRepository())//设置持久化token
                .tokenValiditySeconds(24 * 60 * 60); //记住登录1天(24小时 * 60分钟 * 60秒)

        //关闭csrf跨域攻击防御
        http.csrf().disable();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里要设置自定义认证
        auth.authenticationProvider(loginValidateAuthenticationProvider);
    }


    /**
     * BCrypt加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我功能，持久化的token服务
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //数据源设置
        tokenRepository.setDataSource(dataSource);
        //启动的时候创建表，这里只执行一次，第二次就注释掉，否则每次启动都重新创建表
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}
