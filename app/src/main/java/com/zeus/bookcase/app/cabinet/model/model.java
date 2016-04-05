package com.zeus.bookcase.app.cabinet.model;

import android.net.Uri;

/**
 * Created by zeus_coder on 2016/3/16.
 */
public class Model {
    private final String description;
    private final String userName;
    private final String avatarUrl;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return userName;
    }

    public Uri getAvatarUri() {
        return Uri.parse(avatarUrl);
    }

    public Model(String userName, String avatarUrl, String description) {
        this.description = description;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
    }
}
