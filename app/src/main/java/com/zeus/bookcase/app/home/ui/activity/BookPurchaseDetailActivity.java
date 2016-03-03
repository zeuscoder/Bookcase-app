package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.ui.BookPurchaseDetailFragment;
import com.zeus.bookcase.app.home.ui.BookPurchaseMessageFragment;
import com.zeus.bookcase.app.home.ui.inter.ISlideCallback;
import com.zeus.bookcase.app.home.view.BannerLayout;
import com.zeus.bookcase.app.home.widget.ObservableScrollView;
import com.zeus.bookcase.app.home.widget.ObservableScrollViewCallbacks;
import com.zeus.bookcase.app.home.widget.ScrollState;
import com.zeus.bookcase.app.home.widget.ScrollUtils;
import com.zeus.bookcase.app.home.widget.SlideDetailsLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class BookPurchaseDetailActivity extends AppCompatActivity implements ISlideCallback {

    private TextView purchase;
    private TextView addCart;
    private TextView favorite;

    //上拉图文详情
    //private SlideDetailsLayout slideDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.home__activity_purchase_detail);
        setContentView(R.layout.home__activity_purchase);
        //initTopButton(R.string.activity_book_detail, R.mipmap.app__top_bar_arrow_back, 0);

        //上拉图文详情
//        slideDetailsLayout = (SlideDetailsLayout) findViewById(R.id.slidedetails);
        FragmentManager fm1 = getSupportFragmentManager();
        fm1.beginTransaction().replace(R.id.book_purchase_message, new BookPurchaseMessageFragment()).commit();
//        FragmentManager fm2 = getSupportFragmentManager();
//        fm2.beginTransaction().replace(R.id.book_purchase_detail, new BookPurchaseDetailFragment()).commit();

        purchase = (TextView) findViewById(R.id.book_purchase);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookPurchaseDetailActivity.this, BookOrderConfirmActivity.class));
            }
        });

        addCart = (TextView) findViewById(R.id.book_add_shopping_cart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BookPurchaseDetailActivity.this, "已经加入购物车了噢~亲", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(BookPurchaseDetailActivity.this, TestActivity.class));
            }
        });

        favorite = (TextView) findViewById(R.id.book_add_favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookPurchaseDetailActivity.this, "已经加入收藏夹了噢~亲", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void openDetails(boolean smooth) {
        //slideDetailsLayout.smoothOpen(smooth);
    }

    @Override
    public void closeDetails(boolean smooth) {
        //slideDetailsLayout.smoothClose(smooth);
    }
}
