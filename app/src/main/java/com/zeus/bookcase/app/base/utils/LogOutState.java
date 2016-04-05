package com.zeus.bookcase.app.base.utils;

import android.content.Context;
import android.content.Intent;

import com.zeus.bookcase.app.user.ui.activity.LogInActivity;

/**
 * 注销状态，即未登录状态
 * Created by zeus_coder on 2016/3/20.
 */
public class LogOutState implements UserState {

    @Override
    public void shopping(Context context) {
        goLoginActivity(context);
    }

    private void goLoginActivity(Context context) {
        Intent intent = new Intent(context, LogInActivity.class);
        context.startActivity(intent);
    }
}
