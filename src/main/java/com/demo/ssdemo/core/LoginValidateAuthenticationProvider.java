package com.demo.ssdemo.core;

import com.demo.ssdemo.sys.entity.Resource;
import com.demo.ssdemo.sys.entity.User;
import com.demo.ssdemo.sys.service.UserService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author OZY
 * @Date 2019/07/14 22:29
 * @Description 自定义登陆验证
 * @Version V1.0
 **/
@Component
public class LoginValidateAuthenticationProvider implements AuthenticationProvider {

    @javax.annotation.Resource
    private UserService userService;

    @javax.annotation.Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取输入phone
        String username = authentication.getName();
        String rawPassword = (String) authentication.getCredentials();

        //查询用户是否存在
        User user = (User) userService.loadUserByUsername(username);

        if (user.isEnabled()) {
            throw new DisabledException("该账户已被禁用，请联系管理员");

        } else if (user.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定");

        } else if (user.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期，请联系管理员");

        } else if (user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录");
        }

        //验证密码
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("输入密码错误!");
        }

        //设置权限信息
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Resource resource : user.getRole().getResources()) {
            //资源key作为权限标识
            grantedAuthorities.add(new SimpleGrantedAuthority(resource.getResourceKey()));
            user.setAuthorities(grantedAuthorities);
        }


        return new UsernamePasswordAuthenticationToken(user, rawPassword, user.getAuthorities());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        //确保authentication能转成该类
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
