package com.zeus.bookcase.app.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeus.bookcase.app.AppMain;
import com.zeus.bookcase.app.R;

/**
 * Created by zeus_coder on 2016/2/3.
 */
public  class BaseActivity extends Activity {

    //初始化标题栏各组件
    protected ImageView btn_left;
    protected TextView tv_title;
    protected TextView btn_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppMain.getInstance().addActivity(this);
        Log.d("ActivityName:--------", getClass().getSimpleName());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        AppMain.getInstance().removeActivity(this);
        super.onDestroy();
    }

    public void initTopButton(int titleId, int leftImgId, int rightStrId) {
        tv_title = (TextView) findViewById(R.id.topbar_title);
        tv_title.setText(this.getString(titleId));
        btn_left = (ImageView) findViewById(R.id.leftBtn);
        btn_right = (TextView) findViewById(R.id.rightBtn);
        if(leftImgId == 0) {
            btn_left.setVisibility(View.GONE);
        } else {
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setImageResource(leftImgId);
            btn_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

}
