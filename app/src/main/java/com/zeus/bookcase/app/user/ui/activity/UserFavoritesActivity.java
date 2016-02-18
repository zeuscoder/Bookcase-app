package com.zeus.bookcase.app.user.ui.activity;

import android.os.Bundle;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;


/**
 * Created by zeus_coder on 2016/1/10.
 */
public class UserFavoritesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_favorites);
        initTopButton(R.string.activity_favorites,R.mipmap.app__top_bar_arrow_back, 0);
    }
}
