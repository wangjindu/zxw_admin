package com.zxw.admin.system.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.system.user.entity
 * @date 2020/6/12 11:25
 */
public class UserEntity implements Serializable {

    private String userId;
    private String userName;
    private String userPwd;
    private Date addTime;

    public UserEntity(String userId, String userName, String userPwd, Date addTime) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.addTime = addTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
