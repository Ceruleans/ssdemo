package com.demo.ssdemo.sys.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author OZY
 * @Date 2019/08/09 20:41
 * @Description
 * @Version V1.0
 **/
@Controller
@RequestMapping("/")
public class UserController {

    /**
     * 登录页面跳转
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "login.html";
    }



    /**
     * index页跳转
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "index.html";
    }

    /**
     * menu1按钮
     * @return
     */
    @PreAuthorize("hasAuthority('menu1')")
    @GetMapping("menu1")
    @ResponseBody
    public String menu1() {
        return "menu1";
    }

    /**
     * menu2按钮
     * @return
     */
    @PreAuthorize("hasAuthority('menu2')")
    @GetMapping("menu2")
    @ResponseBody
    public String menu2() {
        return "menu2";
    }

    /**
     * menu3按钮
     * @return
     */
    @PreAuthorize("hasAuthority('menu3')")
    @GetMapping("menu3")
    @ResponseBody
    public String menu3() {
        return "menu3";
    }


}
