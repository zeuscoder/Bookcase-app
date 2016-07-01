package com.zeus.bookcase.app.base.utils;

import android.content.Context;

/**
 * Created by zeus_coder on 2015/12/20
 * LoginContext，用户接口和状态管理类.
 */
public class LoginCOntext {

    //用户状态，默认为未登录状态
    UserState state = new LogOutState();
    //单例
    static LoginCOntext loginContext = new LoginCOntext();

    private LoginCOntext(){
    }

    public static LoginCOntext getLoginContext() {
        return loginContext;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public void shopping(Context context) {
        state.shopping(context);
    }
}
