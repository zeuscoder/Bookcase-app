package com.zeus.bookcase.app.cabinet.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.cabinet.model.dao.TransactionDao;
import com.zeus.bookcase.app.user.model.User;

import cn.bmob.v3.BmobUser;

/**
 * Created by zeus_coder on 2016/4/3.
 */
public class TransactionActivity extends BaseActivity implements View.OnClickListener {

    private ImageView image;
    private TextView title;
    private TextView author;
    private TextView summary;
    private TextView conditionTV;
    private TextView dateTV;
    private ImageButton choose;

    private TextView delete;
    private TextView insert;

    private MyBook book;
    private TransactionDao transactionDao;
    private User user;

    private String condition;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_transaction);
        initTopButton(R.string.activity_apply, R.mipmap.app__top_bar_arrow_back, 0);
        transactionDao = new TransactionDao(this);
        user = BmobUser.getCurrentUser(this, User.class);
        initViews();
        initData();
    }

    private void initViews() {
        image = (ImageView) findViewById(R.id.case_book_image);
        title = (TextView) findViewById(R.id.case_book_title);
        author = (TextView) findViewById(R.id.case_book_author_pubdate);
        summary = (TextView) findViewById(R.id.case_book_summary2);
        conditionTV = (TextView) findViewById(R.id.case_book_condition);
        dateTV = (TextView) findViewById(R.id.case_book_date);
        choose = (ImageButton) findViewById(R.id.case_book_choose);

        delete = (TextView) findViewById(R.id.case__delete_transaction);
        insert = (TextView) findViewById(R.id.case__insert_transaction);
        delete.setOnClickListener(this);
        insert.setOnClickListener(this);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            book = (MyBook) bundle.getSerializable("book");
            ImageLoader.getInstance().displayImage(book.getImage(), image);
            title.setText(book.getTitle());
            author.setText(book.getPublisher() + " " + book.getPubdate());
            summary.setText(book.getSummary());
        } else {
            TransactionActivity.this.finish();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.case__delete_transaction:
                TransactionActivity.this.finish();
                break;
            case R.id.case__insert_transaction:
                condition = conditionTV.getText().toString();
                insertTransaction(book, user, condition);
                break;
        }
    }

    private void insertTransaction(MyBook book, User user, String condition) {
        transactionDao.insertTransaction(book, user, condition);
    }
}
