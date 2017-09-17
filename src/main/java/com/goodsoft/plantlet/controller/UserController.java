package com.goodsoft.plantlet.controller;

import com.goodsoft.plantlet.domain.entity.user.User;
import com.goodsoft.plantlet.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * function 用户管理访问入口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@RestController
public class UserController {

    @Resource
    private UserService service;

    /**
     * 用户授权接口
     *
     * @param uName 用户名
     * @param pwd   密码
     * @return 响应结果
     */
    @RequestMapping(value = "/login.action.do", method = RequestMethod.POST)
    public Object loginController(@RequestParam("uName") String uName, @RequestParam("pwd") String pwd) {
        return this.service.loginService(uName, pwd);
    }

    /**
     * 用户注册接口
     *
     * @param msg 注册信息
     * @return 响应结果
     */
    @RequestMapping(value = "/register.action.do", method = RequestMethod.POST)
    public Object loginController(User msg) {
        return this.service.registerServiece(msg);
    }
}
