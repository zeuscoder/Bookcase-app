package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.adapter.BookIntroductionCommentAdapter;
import com.zeus.bookcase.app.home.adapter.GeekSummaryAdapter;
import com.zeus.bookcase.app.home.widget.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/25.
 */
public class GeekSummaryListActivity extends BaseActivity {

    private ListView geekSummarys;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_geeek_list);
        initTopButton(R.string.activity_geeks, R.mipmap.app__top_bar_arrow_back, 0);
        initListView();
        finishListView();
    }

    private void initListView() {
        for (int i = 0; i < 10; i++) {
            data.add("data: " + i);
        }
        geekSummarys = (ListView) findViewById(R.id.geek_summary_list);
    }

    private void finishListView() {
        geekSummarys.setAdapter(new GeekSummaryAdapter(this, data));
        geekSummarys.setHeaderDividersEnabled(true);
        geekSummarys.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(GeekSummaryListActivity.this, GeekPersonalActivity.class));
            }
        });
    }

}
