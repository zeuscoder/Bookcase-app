package com.zeus.bookcase.app.cabinet.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by zeus_coder on 2016/4/1.
 */
public class Transaction extends BmobObject implements Serializable {

    //书籍id
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
