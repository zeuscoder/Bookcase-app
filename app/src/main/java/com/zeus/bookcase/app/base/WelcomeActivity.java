package com.zeus.bookcase.app.base;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.ui.HomeActivity;

import cn.bmob.v3.Bmob;

/**
 * 欢迎启动页
 *
 * Created by zeus_coder on 2016/2/25.
 */
public class WelcomeActivity extends Activity {

    //Bmob SDK ID
    private static final String BombApplicationID = "b2796854589dba7e80d243844e733ffb";

    private boolean isFirstIn = false;

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    //延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 1000;

    private static final String SHAREDPREFERENCES_NAME = "first_pref";

    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app__activity_welcome);
        initBmobSDK();
        initView();
    }

    private void initBmobSDK() {
        // 初始化 Bmob SDK
        Bmob.initialize(this, BombApplicationID);
    }

    private void initView() {
        // 读取SharedPreferences中需要的数据
        // 使用SharedPreferences来记录程序的使用次数
        //getSharedPreferences获取SharedPreferences实例
        SharedPreferences preferences = WelcomeActivity.this.getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = preferences.getBoolean("isFirstIn", true);

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (!isFirstIn) {
            // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            //将登录标志位设置为false，下次登录时不在显示首次登录界面
            editor.putBoolean("isFirstIn", false);
            editor.commit();
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }
    }

    private void goHome() {
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        WelcomeActivity.this.startActivity(intent);
        WelcomeActivity.this.finish();
    }

    private void goGuide() {
        Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
        WelcomeActivity.this.startActivity(intent);
        WelcomeActivity.this.finish();
    }
}
