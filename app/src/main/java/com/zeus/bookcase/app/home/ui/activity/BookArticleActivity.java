package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;

/**
 * Created by zeus_coder on 2016/2/20.
 */
public class BookArticleActivity extends BaseActivity {

    private Button purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_book_article);
        initTopButton(R.string.activity_article, R.mipmap.app__top_bar_arrow_back, 0);

        purchase = (Button) findViewById(R.id.book_article_purchase);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookArticleActivity.this, BookPurchaseDetailActivity.class));
            }
        });
    }
}
