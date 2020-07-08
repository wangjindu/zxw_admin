package com.zxw.admin.basic.sign.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.sign.entity
 * @date 2020/6/28 17:20
 */
public class SignEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String signId;
    private Integer signType;
    private String signName;
    private String signPhone;
    private Date addTime;

    private String addtime;

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public SignEntity(String signId, Integer signType, String signName, String signPhone, Date addTime) {
        this.signId = signId;
        this.signType = signType;
        this.signName = signName;
        this.signPhone = signPhone;
        this.addTime = addTime;
    }

    public SignEntity() {
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getSignPhone() {
        return signPhone;
    }

    public void setSignPhone(String signPhone) {
        this.signPhone = signPhone;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
