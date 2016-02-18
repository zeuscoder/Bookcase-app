package com.zeus.bookcase.app.user.model;

import java.io.Serializable;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class Express implements Serializable {

    private String thing;

    private String time;

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
