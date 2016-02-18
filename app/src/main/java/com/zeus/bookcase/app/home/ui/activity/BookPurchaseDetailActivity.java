package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.view.BannerLayout;
import com.zeus.bookcase.app.home.widget.ObservableScrollView;
import com.zeus.bookcase.app.home.widget.ObservableScrollViewCallbacks;
import com.zeus.bookcase.app.home.widget.ScrollState;
import com.zeus.bookcase.app.home.widget.ScrollUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class BookPurchaseDetailActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{

    //广告栏
    private BannerLayout banner;

    private TextView purchase;
    private TextView addCart;

    private TextView favorite;

    //向上显示topbar
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.home__activity_purchase_detail);
        setContentView(R.layout.home__activity_purchase);
        //initTopButton(R.string.activity_book_detail, R.mipmap.app__top_bar_arrow_back, 0);
        setSupportActionBar((Toolbar) findViewById(R.id.top_bar));
        mToolbarView = findViewById(R.id.top_bar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.selected_item_color)));

        mScrollView = (ObservableScrollView) findViewById(R.id.purchase_scroll);
        mScrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);

        banner = (BannerLayout) findViewById(R.id.banner);
        List<String> urls = new ArrayList<>();
        urls.add("http://img3.imgtn.bdimg.com/it/u=2674591031,2960331950&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");
        banner.setViewUrls(urls);
        //添加监听事件
        banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BookPurchaseDetailActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.selected_item_color);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(banner, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
