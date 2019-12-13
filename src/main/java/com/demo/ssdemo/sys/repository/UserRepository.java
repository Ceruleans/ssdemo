package com.demo.ssdemo.sys.repository;

import com.demo.ssdemo.sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author OZY
 * @Date 2019/08/09 20:42
 * @Description
 * @Version V1.0
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

}
