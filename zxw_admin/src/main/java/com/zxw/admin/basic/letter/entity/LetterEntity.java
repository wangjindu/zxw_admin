package com.zxw.admin.basic.letter.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.letter.entity
 * @date 2020/6/13 11:32
 */
public class LetterEntity implements Serializable {

    private String letterId;
    private int letterType;
    private String letterContent;
    private String letterPhone;
    private Date addTime;

    private String addtime;

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public LetterEntity() {
    }

    public LetterEntity(String letterId, int letterType, String letterContent, String letterPhone, Date addTime) {
        this.letterId = letterId;
        this.letterType = letterType;
        this.letterContent = letterContent;
        this.letterPhone = letterPhone;
        this.addTime = addTime;
    }

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId;
    }

    public int getLetterType() {
        return letterType;
    }

    public void setLetterType(int letterType) {
        this.letterType = letterType;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public String getLetterPhone() {
        return letterPhone;
    }

    public void setLetterPhone(String letterPhone) {
        this.letterPhone = letterPhone;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
