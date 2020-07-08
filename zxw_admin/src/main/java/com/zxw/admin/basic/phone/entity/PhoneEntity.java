package com.zxw.admin.basic.phone.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.phone.entity
 * @date 2020/6/16 16:10
 */
public class PhoneEntity implements Serializable {

    private String phoneId;
    private String phoneNum;
    private Date addTime;

    private String addtime;

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
