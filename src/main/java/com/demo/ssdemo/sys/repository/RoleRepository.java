package com.demo.ssdemo.sys.repository;

import com.demo.ssdemo.sys.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: OZY
 * @createTime: 2019-08-14 12:58
 * @description:
 * @version: 1.0.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Override
    List<Role> findAll();
}
