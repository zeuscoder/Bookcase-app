package com.zeus.bookcase.app.home.model;

import java.io.Serializable;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class Geek implements Serializable {

    private String uid;

    private String name;

    private String title;

    private int photo;

    private String image;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
