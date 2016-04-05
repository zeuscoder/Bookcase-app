package com.zeus.bookcase.app.home.ui.activity;

import android.os.Bundle;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;

/**
 * Created by zeus_coder on 2016/3/14.
 */
public class OrderPayResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_order_pay_result);
        initTopButton(R.string.activity_result, R.mipmap.app__top_bar_arrow_back, 0);
    }
}
