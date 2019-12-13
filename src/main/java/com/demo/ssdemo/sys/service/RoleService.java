package com.demo.ssdemo.sys.service;

import com.demo.ssdemo.sys.entity.Role;
import com.demo.ssdemo.sys.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: OZY
 * @createTime: 2019-08-14 12:57
 * @description:
 * @version: 1.0.0
 */
@Service
public class RoleService {

    @Resource
    private RoleRepository roleRepository;


    public List<Role> findAll(){
        return roleRepository.findAll();
    }

}
