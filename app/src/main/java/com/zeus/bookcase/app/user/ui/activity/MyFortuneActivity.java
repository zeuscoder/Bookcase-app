package com.zeus.bookcase.app.user.ui.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.user.adapter.MyFortuneAdapter;
import com.zeus.bookcase.app.user.view.ClipViewPager;
import com.zeus.bookcase.app.user.view.ScalePageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/26.
 */
public class MyFortuneActivity extends BaseActivity {

    private ClipViewPager mViewPager;
    private MyFortuneAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_my_fortune);
        initTopButton(R.string.activty_my_fortune, R.mipmap.app__top_bar_arrow_back, 0);

        mViewPager = (ClipViewPager) findViewById(R.id.viewpager_fortune);
        mViewPager.setPageTransformer(true, new ScalePageTransformer());

        findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        mPagerAdapter = new MyFortuneAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
    }


}
