package com.goodsoft.plantlet.service.lmpl;

import com.goodsoft.plantlet.domain.dao.UserDao;
import com.goodsoft.plantlet.domain.entity.result.Result;
import com.goodsoft.plantlet.domain.entity.result.Status;
import com.goodsoft.plantlet.domain.entity.result.StatusEnum;
import com.goodsoft.plantlet.domain.entity.user.User;
import com.goodsoft.plantlet.service.UserService;
import com.goodsoft.plantlet.util.UUIDUtil;
import com.horizon.util.encrypt.DESEDE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * function 用户管理业务接口实现类
 * Created by 严彬荣 on 2017/9/17.
 * varsion v1.0
 */
@SuppressWarnings("ALL")
@Service
public class UserServcielmpl implements UserService {

    @Resource
    private UserDao dao;
    //实例化日志管理类
    private Logger logger = LoggerFactory.getLogger(UserServcielmpl.class);
    //实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();


    /**
     * 用户授权业务方法
     *
     * @param uName 用户名
     * @param pwd   密码
     * @param <T>   泛型
     * @return 授权结果
     */
    @Override
    public <T> T loginService(String uName, String pwd) {
        //密码解密
        String passWord = DESEDE.encryptIt(pwd);
        User data = null;
        try {
            data = this.dao.loginDao(uName, passWord);
        } catch (Exception e) {
            this.logger.error(e.toString());
            return (T) new Status(StatusEnum.SERVER_ERROR.getCODE(), StatusEnum.SERVER_ERROR.getEXPLAIN());
        }
        if (data != null) {
            return (T) new Result(0, data);
        }
        return (T) new Status(StatusEnum.CHECKUSER.getCODE(), StatusEnum.CHECKUSER.getEXPLAIN());
    }

    /**
     * 用户注册业务方法
     *
     * @param msg 注册信息
     * @return 注册结果
     */
    @Transactional
    @Override
    public Status registerServiece(User msg) {
        //密码加密
        msg.setPassWord(DESEDE.encryptIt(msg.getPassWord()));
        msg.setUid(this.uuid.getUUID().toString());
        String data = null;
        try {
            data = this.dao.queryUnameDao(msg.getUserName());
            if (data != null) {
                return new Status(StatusEnum.USERNAME.getCODE(), StatusEnum.USERNAME.getEXPLAIN());
            }
            data = this.dao.queryTelDao(msg.getTel());
            if (data != null) {
                return new Status(StatusEnum.USERTEL.getCODE(), StatusEnum.USERTEL.getEXPLAIN());
            }
            this.dao.registerDao(msg);
            return new Status(StatusEnum.SUCCESS.getCODE(), StatusEnum.SUCCESS.getEXPLAIN());
        } catch (Exception e) {
            this.logger.error(e.toString());
            return new Status(StatusEnum.DEFEAT.getCODE(), StatusEnum.DEFEAT.getEXPLAIN());
        }
    }
}
