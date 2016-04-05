package com.zeus.bookcase.app.cabinet.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;

import static com.zeus.bookcase.app.R.id.add_book_search;

/**
 * Created by zeus_coder on 2016/3/23.
 */
public class AddBookActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout scan;
    private LinearLayout search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_add_book);
        initTopButton(R.string.activity_add_book, R.mipmap.app__top_bar_arrow_back, 0);
        initView();
    }


    private void initView() {
        scan = (LinearLayout) findViewById(R.id.add_book_scan);
        search = (LinearLayout) findViewById(add_book_search);
        scan.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.add_book_scan:
                goScanActivity();
                break;
            case R.id.add_book_search:
                goSearchActivity();
                break;
        }
    }

    private void goScanActivity() {
        startActivityForResult(new Intent(AddBookActivity.this, CaptureActivity.class), 100);
    }

    private void goSearchActivity() {
        startActivityForResult(new Intent(AddBookActivity.this, SearchISBNActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (((requestCode==100)||(resultCode== Activity.RESULT_OK)) && data!=null) {
            String isbn = data.getStringExtra("result");
            Intent intent = new Intent(AddBookActivity.this, BookViewActivity.class);
            intent.putExtra("isbn", isbn);
            startActivity(intent);
        }
    }
}
