package com.zeus.bookcase.app.cabinet.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by zeus_coder on 2016/4/1.
 */
public class Transaction extends BmobObject implements Serializable {

    //书籍id
    private String bid;
    //拥有者id
    private String uid;
    //交换者id
    private String tid;
    //交换条件
    private String condition;
    //是否可交易
    private Boolean isdeal;
    //是否已交易
    private Boolean hasdeal;

    private String owner;

    private String image;

    private String title;

    private String author;

    private String state;

    private String like;

    private String summary;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean getIsdeal() {
        return isdeal;
    }

    public void setIsdeal(Boolean isdeal) {
        this.isdeal = isdeal;
    }

    public Boolean getHasdeal() {
        return hasdeal;
    }

    public void setHasdeal(Boolean hasdeal) {
        this.hasdeal = hasdeal;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
