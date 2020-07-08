package com.zxw.admin.website.news.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.website.news
 * @date 2020/6/12 8:42
 */
public class NewsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String newsId;
    private String newsScource;
    private int newsType;
    private String newsTitle;
    private String newsIcon;
    private String newsSummary;
    private String newsContent;
    private Date addTime;
    private long readCount;
    private Integer searchKey;

    private String img;
    private String addtime;

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public NewsEntity(String newsId, String newsScource, int newsType, String newsTitle, String newsIcon, String newsSummary, String newsContent, Date addTime, Integer searchKey) {
        this.newsId = newsId;
        this.newsScource = newsScource;
        this.newsType = newsType;
        this.newsTitle = newsTitle;
        this.newsIcon = newsIcon;
        this.newsSummary = newsSummary;
        this.newsContent = newsContent;
        this.addTime = addTime;
        this.searchKey = searchKey;
    }

    public NewsEntity(String newsId, String newsScource, int newsType, String newsTitle, String newsIcon, String newsSummary, String newsContent, Date addTime, long readCount, Integer searchKey) {
        this.newsId = newsId;
        this.newsScource = newsScource;
        this.newsType = newsType;
        this.newsTitle = newsTitle;
        this.newsIcon = newsIcon;
        this.newsSummary = newsSummary;
        this.newsContent = newsContent;
        this.addTime = addTime;
        this.readCount = readCount;
        this.searchKey = searchKey;
    }

    public NewsEntity(String newsId, String newsScource, int newsType, String newsTitle, String newsIcon, String newsSummary, String newsContent, Date addTime, long readCount, Integer searchKey, String img) {
        this.newsId = newsId;
        this.newsScource = newsScource;
        this.newsType = newsType;
        this.newsTitle = newsTitle;
        this.newsIcon = newsIcon;
        this.newsSummary = newsSummary;
        this.newsContent = newsContent;
        this.addTime = addTime;
        this.readCount = readCount;
        this.searchKey = searchKey;
        this.img = img;
    }

    public NewsEntity() {
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsScource() {
        return newsScource;
    }

    public void setNewsScource(String newsScource) {
        this.newsScource = newsScource;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsIcon() {
        return newsIcon;
    }

    public void setNewsIcon(String newsIcon) {
        this.newsIcon = newsIcon;
    }

    public String getNewsSummary() {
        return newsSummary;
    }

    public void setNewsSummary(String newsSummary) {
        this.newsSummary = newsSummary;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public long getReadCount() {
        return readCount;
    }

    public void setReadCount(long readCount) {
        this.readCount = readCount;
    }

    public Integer getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(Integer searchKey) {
        this.searchKey = searchKey;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
