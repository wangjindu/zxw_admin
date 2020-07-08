package com.zxw.admin.website.zsjz.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.zsjz.entity
 * @date 2020/6/11 15:32
 */
public class ZsjzEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String zsjzId;
    private String zsjzTitle;
    private String zsjzIcona;
    private String zsjzIconb;
    private String zsjzIconc;
    private String zsjzIcond;
    private String zsjzSummary;
    private String zsjzContent;
    private String zsjzClass;
    private String zsjzJob;
    private String endTime;
    private String zsjzUrl;
    private String zsjzName;
    private Integer zsjzTime;
    private String zsjzA;
    private String zsjzB;
    private String zsjzC;
    private Date addTime;
    private Long enCount;
    private Integer zsjzType;
    private Integer searchKey;

    private String imga;
    private String imgb;
    private String imgc;
    private String imgd;

    private String addtime;

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public ZsjzEntity() {
    }

    public String getZsjzId() {
        return zsjzId;
    }

    public ZsjzEntity(String zsjzId, String zsjzTitle, String zsjzIcona, String zsjzIconb, String zsjzIconc, String zsjzIcond, String zsjzSummary, String zsjzContent, String zsjzClass, String zsjzJob, String endTime, String zsjzUrl, String zsjzName, Integer zsjzTime, String zsjzA, String zsjzB, String zsjzC, Date addTime, Long enCount, Integer zsjzType, Integer searchKey) {
        this.zsjzId = zsjzId;
        this.zsjzTitle = zsjzTitle;
        this.zsjzIcona = zsjzIcona;
        this.zsjzIconb = zsjzIconb;
        this.zsjzIconc = zsjzIconc;
        this.zsjzIcond = zsjzIcond;
        this.zsjzSummary = zsjzSummary;
        this.zsjzContent = zsjzContent;
        this.zsjzClass = zsjzClass;
        this.zsjzJob = zsjzJob;
        this.endTime = endTime;
        this.zsjzUrl = zsjzUrl;
        this.zsjzName = zsjzName;
        this.zsjzTime = zsjzTime;
        this.zsjzA = zsjzA;
        this.zsjzB = zsjzB;
        this.zsjzC = zsjzC;
        this.addTime = addTime;
        this.enCount = enCount;
        this.zsjzType = zsjzType;
        this.searchKey = searchKey;
    }

    public void setZsjzId(String zsjzId) {
        this.zsjzId = zsjzId;
    }

    public String getZsjzTitle() {
        return zsjzTitle;
    }

    public void setZsjzTitle(String zsjzTitle) {
        this.zsjzTitle = zsjzTitle;
    }

    public String getZsjzIcona() {
        return zsjzIcona;
    }

    public void setZsjzIcona(String zsjzIcona) {
        this.zsjzIcona = zsjzIcona;
    }

    public String getZsjzIconb() {
        return zsjzIconb;
    }

    public void setZsjzIconb(String zsjzIconb) {
        this.zsjzIconb = zsjzIconb;
    }

    public String getZsjzIconc() {
        return zsjzIconc;
    }

    public void setZsjzIconc(String zsjzIconc) {
        this.zsjzIconc = zsjzIconc;
    }

    public String getZsjzIcond() {
        return zsjzIcond;
    }

    public void setZsjzIcond(String zsjzIcond) {
        this.zsjzIcond = zsjzIcond;
    }

    public String getZsjzSummary() {
        return zsjzSummary;
    }

    public void setZsjzSummary(String zsjzSummary) {
        this.zsjzSummary = zsjzSummary;
    }

    public String getZsjzContent() {
        return zsjzContent;
    }

    public void setZsjzContent(String zsjzContent) {
        this.zsjzContent = zsjzContent;
    }

    public String getZsjzClass() {
        return zsjzClass;
    }

    public void setZsjzClass(String zsjzClass) {
        this.zsjzClass = zsjzClass;
    }

    public String getZsjzJob() {
        return zsjzJob;
    }

    public void setZsjzJob(String zsjzJob) {
        this.zsjzJob = zsjzJob;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getZsjzUrl() {
        return zsjzUrl;
    }

    public void setZsjzUrl(String zsjzUrl) {
        this.zsjzUrl = zsjzUrl;
    }

    public String getZsjzName() {
        return zsjzName;
    }

    public void setZsjzName(String zsjzName) {
        this.zsjzName = zsjzName;
    }

    public Integer getZsjzTime() {
        return zsjzTime;
    }

    public void setZsjzTime(Integer zsjzTime) {
        this.zsjzTime = zsjzTime;
    }

    public String getZsjzA() {
        return zsjzA;
    }

    public void setZsjzA(String zsjzA) {
        this.zsjzA = zsjzA;
    }

    public String getZsjzB() {
        return zsjzB;
    }

    public void setZsjzB(String zsjzB) {
        this.zsjzB = zsjzB;
    }

    public String getZsjzC() {
        return zsjzC;
    }

    public void setZsjzC(String zsjzC) {
        this.zsjzC = zsjzC;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Long getEnCount() {
        return enCount;
    }

    public void setEnCount(Long enCount) {
        this.enCount = enCount;
    }

    public Integer getZsjzType() {
        return zsjzType;
    }

    public void setZsjzType(Integer zsjzType) {
        this.zsjzType = zsjzType;
    }

    public Integer getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(Integer searchKey) {
        this.searchKey = searchKey;
    }

    public String getImga() {
        return imga;
    }

    public void setImga(String imga) {
        this.imga = imga;
    }

    public String getImgb() {
        return imgb;
    }

    public void setImgb(String imgb) {
        this.imgb = imgb;
    }

    public String getImgc() {
        return imgc;
    }

    public void setImgc(String imgc) {
        this.imgc = imgc;
    }

    public String getImgd() {
        return imgd;
    }

    public void setImgd(String imgd) {
        this.imgd = imgd;
    }
}
