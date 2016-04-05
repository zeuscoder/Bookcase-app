package com.zeus.bookcase.app.home.model.annotation;

import java.io.Serializable;

/**
 * Created by zeus_coder on 2016/4/3.
 */
public class Author_user implements Serializable {

    private String name;

    private boolean is_banned;

    private boolean is_suicide;

    private String url;

    private String avatar;

    private String uid;

    private String alt;

    private String type;

    private String id;

    private String large_avatar;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setIs_banned(boolean is_banned){
        this.is_banned = is_banned;
    }
    public boolean getIs_banned(){
        return this.is_banned;
    }
    public void setIs_suicide(boolean is_suicide){
        this.is_suicide = is_suicide;
    }
    public boolean getIs_suicide(){
        return this.is_suicide;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    public String getAvatar(){
        return this.avatar;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    public void setAlt(String alt){
        this.alt = alt;
    }
    public String getAlt(){
        return this.alt;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setLarge_avatar(String large_avatar){
        this.large_avatar = large_avatar;
    }
    public String getLarge_avatar(){
        return this.large_avatar;
    }

}
