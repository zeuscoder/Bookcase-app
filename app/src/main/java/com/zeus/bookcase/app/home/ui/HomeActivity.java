package com.zeus.bookcase.app.home.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.quinny898.library.persistentsearch.SearchBox;
import com.yalantis.guillotine.animation.GuillotineAnimation;
import com.zeus.bookcase.app.AppMain;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.utils.CommonUtil;
import com.zeus.bookcase.app.cabinet.CaseFragment;
import com.zeus.bookcase.app.home.HomeFragment;
import com.zeus.bookcase.app.home.ui.activity.BookSearchActivity;
import com.zeus.bookcase.app.user.MySelfFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 启动首页
 * Created by zeus_coder on 2016/2/3.
 */
public class HomeActivity extends AppCompatActivity {

    private static final long RIPPLE_DURATION = 250;
    private FragmentTransaction transaction;
    private RadioGroup tabGroup;
    private HomeFragment homeFragment;
//    private MarketFragment marketFragment;
    private CaseFragment caseFragment;
    private MySelfFragment mySelfFragment;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.root) FrameLayout root;
    @Bind(R.id.content_hamburger) View contentHamburger;
    //初始化搜索图标
    @Bind(R.id.searchbox) SearchBox search;
    @Bind(R.id.content_search) ImageView content_search;

    public View mainView;
    // 退出系统字段
    protected long exitTime = 0;

    /*搜索栏*/
    @OnClick(R.id.content_search) void search() {
        //openSearch();
        startActivity(new Intent(HomeActivity.this, BookSearchActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_main);
        ButterKnife.bind(this);

        //初始化View
        initViews();
        //初始化Fragment
        setDefaultFragment();
        //初始化TabGroup
        tabGroup = (RadioGroup) findViewById(R.id.fragment_group);
        tabGroup.setOnCheckedChangeListener(checkedChangeListener);
    }

    private void initViews() {
        mainView = new View(this);
        search.enableVoiceRecognition(this);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }
        //主菜单
        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.home__activity_menu, null);
        root.addView(guillotineMenu);
        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
    }

    private void setDefaultFragment() {
        transaction = getFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.main_fragment, homeFragment);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, fragment);
        transaction.commit();
    }

    /**
     *Fragment切换动画
     */
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.tab_home:
                    if(null == homeFragment) {
                        homeFragment = new HomeFragment();
                    }
                    replaceFragment(homeFragment);
                    content_search.setVisibility(View.VISIBLE);
                    break;
//                case R.id.tab_market:
//                    if(null == marketFragment) {
//                        marketFragment = new MarketFragment();
//                    }
//                    replaceFragment(marketFragment);
//                    content_search.setVisibility(View.GONE);
//                    break;
                case R.id.tab_case:
                    if(null == caseFragment) {
                        caseFragment = new CaseFragment();
                    }
                    replaceFragment(caseFragment);
                    content_search.setVisibility(View.GONE);
                    break;
                case R.id.tab_user:
                    if(null == mySelfFragment) {
                        mySelfFragment = new MySelfFragment();
                    }
                    replaceFragment(mySelfFragment);
                    content_search.setVisibility(View.GONE);
                    break;
            }
        }
    };

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
}
