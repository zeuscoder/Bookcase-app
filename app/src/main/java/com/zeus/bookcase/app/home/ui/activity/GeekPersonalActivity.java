package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.adapter.GeekMessageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/19.
 */
public class GeekPersonalActivity extends BaseActivity {

    private ListView messageList;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_geek_personal);
        initTopButton(R.string.activity_geek, R.mipmap.app__top_bar_arrow_back, 0);
        initListView();
        initHeaderView();
        finishListView();
    }

    private void initListView() {
        for (int i = 0; i < 10; i++) {
            data.add("data: " + i);
        }
        messageList = (ListView) findViewById(R.id.geek_list_message);
    }

    private void initHeaderView() {
        final View headerView = this.getLayoutInflater().inflate(R.layout.home__activity_geek_personal_header_view, null);
        messageList.addHeaderView(headerView, null, false);
    }

    private void finishListView() {
        messageList.setAdapter(new GeekMessageAdapter(data, this));
        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(GeekPersonalActivity.this, BookArticleActivity.class));
            }
        });
    }
}
