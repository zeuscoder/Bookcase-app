package com.zeus.bookcase.app.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.zeus.bookcase.app.AppMain;
import com.zeus.bookcase.app.base.utils.CommonUtil;

/**
 * Created by zeus_coder on 2016/2/3.
 */
public class BaseExitActivity extends Activity {

    // 退出系统字段
    protected long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 在两秒钟内连续按两次返回，则退出
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                CommonUtil.shortToast(getApplicationContext(), "再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
                // 退出应用
                AppMain.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppMain.getInstance().addActivity(this);
        Log.d("ActivityName----", getClass().getSimpleName());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        AppMain.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
