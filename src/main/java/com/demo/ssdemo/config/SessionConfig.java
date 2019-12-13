package com.demo.ssdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author OZY
 * @Date 2019-08-12 10:35
 * @Description
 * @Version V1.0
 **/
@Configuration
@EnableRedisHttpSession
public class SessionConfig {

}
