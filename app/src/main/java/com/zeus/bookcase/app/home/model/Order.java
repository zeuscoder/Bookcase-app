package com.zeus.bookcase.app.home.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by zeus_coder on 2016/4/3.
 */
public class Order extends BmobObject implements Serializable {

    private String uid;

    private String bid;

    private String price;

    private String count;

    private String pay;

    private String message;

    private String state;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
