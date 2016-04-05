package com.zeus.bookcase.app.user.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by zeus_coder on 2016/3/14.
 */
public class Count extends BmobObject implements Serializable {

    private Integer points;
    private Double money;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
