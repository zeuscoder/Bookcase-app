package com.zeus.bookcase.app.home.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.yalantis.phoenix.PullToRefreshView;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.adapter.BookListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class NewHeatBookActivity extends BaseActivity {

    private PullToRefreshView bookPullToRefreshView;
    private ListView bookList;
    private List<String> list;
    private BookListViewAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_new_book_list);
        initTopButton(R.string.activity_new_book_come, R.mipmap.app__top_bar_arrow_back, 0);
        initView();
        setData();
        setAction();
    }

    private void initView() {
        bookPullToRefreshView = (PullToRefreshView) findViewById(R.id.book_pull_to_refresh);
        bookList = (ListView) findViewById(R.id.book_list_view);
    }

    private void setData() {
        list = new ArrayList<String>();
        list.add("https://img3.doubanio.com/mpic/s28351121.jpg");
        list.add("https://img1.doubanio.com/mpic/s27997453.jpg");
        list.add("https://img1.doubanio.com/mpic/s6641009.jpg");
        list.add("https://img1.doubanio.com/mpic/s28320163.jpg");
        list.add("https://img3.doubanio.com/mpic/s28293575.jpg");
        list.add("https://img3.doubanio.com/mpic/s2686916.jpg");
        list.add("https://img3.doubanio.com/mpic/s28283341.jpg");
        list.add("https://img3.doubanio.com/mpic/s28283341.jpg");
        list.add("https://img3.doubanio.com/mpic/s28283341.jpg");
        bookAdapter = new BookListViewAdapter(this,list);
        bookList.setAdapter(bookAdapter);
    }

    private void setAction() {
        bookPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bookPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bookPullToRefreshView.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}
