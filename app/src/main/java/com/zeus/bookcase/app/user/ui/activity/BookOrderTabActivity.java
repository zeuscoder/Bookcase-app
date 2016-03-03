package com.zeus.bookcase.app.user.ui.activity;


//import android.app.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.user.ui.FirstFragment;
import com.zeus.common.tabs.MaterialTab;
import com.zeus.common.tabs.MaterialTabHost;
import com.zeus.common.tabs.MaterialTabListener;


/**
 * Created by zeus_coder on 2015/12/2.
 */
public class BookOrderTabActivity extends AppCompatActivity implements MaterialTabListener {

    private ViewPager pager;
    private ViewPagerAdapter pagerAdapter;
    private MaterialTabHost tabHost;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__book_order_tab);
        res = getResources();
        // init toolbar (old action bar)

        tabHost = (MaterialTabHost) findViewById(R.id.tabHost);
        pager = (ViewPager) findViewById(R.id.pager);
        // init view pager
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });
        // insert all tabs from pagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(pagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }

        int position = getIntent().getExtras().getInt("position");

    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        // when the tab is clicked the pager swipe content to the tab position
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            return new FirstFragment();
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "全部";
                case 1:
                    return "待付款";
                case 2:
                    return "待发货";
                case 3:
                    return "待收货";
                case 4:
                    return "待评价";
                default:
                    return null;
            }
        }
    }

    /*
   * It doesn't matter the color of the icons, but they must have solid colors
   */
/*    private Drawable getIcon(int position) {
        switch(position) {
            case 0:
                return res.getDrawable(R.drawable.ic_person_black_24dp);
            case 1:
                return res.getDrawable(R.drawable.ic_group_black_24dp);
            case 2:
                return res.getDrawable(R.drawable.ic_notifications_off_white_24dp);
        }
        return null;
    }*/
}
