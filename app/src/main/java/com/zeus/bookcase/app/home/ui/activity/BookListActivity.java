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
public class BookListActivity extends BaseActivity {

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
        list.add("http://img4.imgtn.bdimg.com/it/u=1914385367,2773465149&fm=21&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=1459528716,2382015290&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3882347136,1187674664&fm=21&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=3616248447,1939819186&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3143204377,1920131569&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=98744119,1601059181&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1351354913,484173169&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=741981606,3155267448&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1914385367,2773465149&fm=21&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=1459528716,2382015290&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3882347136,1187674664&fm=21&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=3616248447,1939819186&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3143204377,1920131569&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=98744119,1601059181&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1351354913,484173169&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=741981606,3155267448&fm=21&gp=0.jpg");
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
