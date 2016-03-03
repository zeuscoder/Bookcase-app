package com.zeus.bookcase.app.user.model;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

/**
 * Created by zeus_coder on 2016/2/28.
 */
public class User extends BmobUser implements Serializable {

    private Boolean sex;
    private String nickName;

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
