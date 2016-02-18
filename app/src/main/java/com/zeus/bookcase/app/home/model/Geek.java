package com.zeus.bookcase.app.home.model;

import java.io.Serializable;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class Geek implements Serializable {

    private String name;

    private String title;

    private int photo;

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
}
