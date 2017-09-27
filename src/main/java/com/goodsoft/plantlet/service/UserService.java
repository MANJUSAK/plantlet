package com.goodsoft.plantlet.service;

import com.goodsoft.plantlet.domain.entity.user.User;
import com.goodsoft.plantlet.domain.entity.result.Status;

/**
 * function 用户管理业务接口类
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public interface UserService {
    //用户授权业务接口方法
    public <T> T loginService(String uName, String pwd);

    //用户注册业务接口方法
    public Status registerServiece(User msg);
}
