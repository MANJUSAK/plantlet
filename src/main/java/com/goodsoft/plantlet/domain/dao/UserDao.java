package com.goodsoft.plantlet.domain.dao;

import com.goodsoft.plantlet.domain.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * function 用户管理数据dao层接口
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
@Repository
public interface UserDao {

    //用户授权dao方法
    public User loginDao(@Param("uName") String uName, @Param("pwd") String pwd) throws Exception;

    //查询用户名是否存在
    public String queryUnameDao(@Param("uName") String uName) throws Exception;

    //查询手机号是否存在
    public String queryTelDao(@Param("tel") long tel) throws Exception;

    //用户注册dao方法
    public void registerDao(User msg) throws Exception;
}
