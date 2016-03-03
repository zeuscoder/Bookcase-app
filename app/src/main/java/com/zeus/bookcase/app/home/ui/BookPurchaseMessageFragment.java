package com.zeus.bookcase.app.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.view.BannerLayout;
import com.zeus.bookcase.app.home.widget.ObservableScrollView;
import com.zeus.bookcase.app.home.widget.ObservableScrollViewCallbacks;
import com.zeus.bookcase.app.home.widget.ScrollState;
import com.zeus.bookcase.app.home.widget.ScrollUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajb on 16-2-19.
 */
public class BookPurchaseMessageFragment extends BaseFragment implements ObservableScrollViewCallbacks {

    //广告栏
    private BannerLayout banner;

    //向上显示topbar
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home__fragment_purchase_message, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbarView = view.findViewById(R.id.top_bar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.selected_item_color)));

        mScrollView = (ObservableScrollView) view.findViewById(R.id.purchase_scroll);
        mScrollView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);

        banner = (BannerLayout) view.findViewById(R.id.banner);
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
                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        mToolbarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
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
