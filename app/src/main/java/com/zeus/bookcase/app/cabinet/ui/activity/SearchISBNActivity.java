package com.zeus.bookcase.app.cabinet.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;

/**
 * Created by zeus_coder on 2016/3/23.
 */
public class SearchISBNActivity extends BaseActivity {

    private TextView isbn;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_search_isbn);
        initTopButton(R.string.activity_search, R.mipmap.app__top_bar_arrow_back, 0);
        initView();

    }

    private void initView() {
        isbn = (TextView) findViewById(R.id.isbn_text);
        search = (Button) findViewById(R.id.search_isbn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("result", isbn.getText().toString());
                SearchISBNActivity.this.setResult(Activity.RESULT_OK, intent);
                SearchISBNActivity.this.finish();
            }
        });
    }
}
