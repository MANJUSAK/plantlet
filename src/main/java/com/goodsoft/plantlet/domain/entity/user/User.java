package com.goodsoft.plantlet.domain.entity.user;

import java.beans.Transient;
import java.util.Objects;

/**
 * function 用户实体
 * Created by 严彬荣 on 2017/9/17.
 * version v1.0
 */
public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1533791607305696188L;
    private String uid;//用户编号
    private String userName;//用户名
    private String passWord;//密码
    private long tel;//手机号

    public User() {
        super();
    }

    public User(String uid, String userName, String passWord, long tel) {
        this.uid = uid;
        this.userName = userName;
        this.passWord = passWord;
        this.tel = tel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Transient
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return tel == user.tel &&
                Objects.equals(uid, user.uid) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(passWord, user.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, userName, passWord, tel);
    }
}
