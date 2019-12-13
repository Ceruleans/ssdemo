package com.demo.ssdemo.sys.service;

import com.demo.ssdemo.sys.entity.User;
import com.demo.ssdemo.sys.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author OZY
 * @Date 2019/08/09 20:42
 * @Description
 * @Version V1.0
 **/
@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("不存在该用户!");
        }
        return user;
    }


}
