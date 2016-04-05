package com.zeus.bookcase.app.user.model;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

/**
 * Created by zeus_coder on 2016/2/28.
 */
public class User extends BmobUser implements Serializable {

    private Boolean gender;
    private String nickName;
    private String image;
    private String level;
    private Count count;



    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
