package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.adapter.BookIntroductionCommentAdapter;
import com.zeus.bookcase.app.home.widget.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/21.
 */
public class BookIntroductionActivity extends BaseActivity {

    private ListView comments;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_book_introduction);
        initTopButton(R.string.activity_geek, R.mipmap.app__top_bar_arrow_back, 0);
        initListView();
        initHeaderView();
        finishListView();
    }

    private void initListView() {
        for (int i = 0; i < 10; i++) {
            data.add("data: " + i);
        }
        comments = (ListView) findViewById(R.id.book_introduction_comment_list);
    }

    private void initHeaderView() {
        final View headerView = this.getLayoutInflater().inflate(R.layout.home__activity_book_introduction_header_view, null);
        ExpandableTextView exp = (ExpandableTextView) headerView.findViewById(R.id.expand_text_view);
        exp.setText("        "+getResources().getString(R.string.suibian));
        comments.addHeaderView(headerView, null, false);
    }

    private void finishListView() {
        comments.setAdapter(new BookIntroductionCommentAdapter(data, this));
        comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(BookIntroductionActivity.this, BookArticleActivity.class));
            }
        });
    }

}
