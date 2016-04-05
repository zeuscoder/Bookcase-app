package com.zeus.bookcase.app.base.utils;

import android.content.Context;
import android.content.Intent;

import com.zeus.bookcase.app.user.ui.activity.UserShoppingCartActivity;

/**
 * 已登录状态
 * Created by zeus_coder on 2016/3/20.
 */
public class LoginState implements UserState {

    @Override
    public void shopping(Context context) {
        Intent intent = new Intent(context, UserShoppingCartActivity.class);
        context.startActivity(intent);
    }
}
