package com.zeus.bookcase.app.cabinet.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.cabinet.model.Transaction;

import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by zeus_coder on 2016/4/7.
 */
public class TransactionConditionActivity extends BaseActivity implements View.OnClickListener {

    private ImageView image;
    private TextView title;
    private TextView author;
    private TextView summary;
    private TextView owner;
    private TextView condition;
    //private TextView date;
    private CardView card;
    private RelativeLayout choose;

    private TextView channel;
    private TextView insert;

    private ImageView myImage;
    private TextView myTitle;
    private TextView myAuthor;
    private TextView mySummary;

    private Transaction transaction;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_transaction_condition);
        initTopButton(R.string.activity_apply, R.mipmap.app__top_bar_arrow_back, 0);

        initViews();
        initData();
    }

    private void initViews() {
        image = (ImageView) findViewById(R.id.case_book_image);
        title = (TextView) findViewById(R.id.case_book_title);
        author = (TextView) findViewById(R.id.case_book_author_pubdate);
        summary = (TextView) findViewById(R.id.case_book_summary);
        owner = (TextView) findViewById(R.id.case_book_owner);
        condition = (TextView) findViewById(R.id.case_book_condition);
        //date = (TextView) findViewById(R.id.case_book_date);

        myImage = (ImageView) findViewById(R.id.case_my_book_image);
        myTitle = (TextView) findViewById(R.id.case_my_book_title);
        myAuthor = (TextView) findViewById(R.id.case_my_book_author_pubdate);
        mySummary = (TextView) findViewById(R.id.case_my_book_summary);

        card = (CardView) findViewById(R.id.case_transaction_book);
        choose = (RelativeLayout) findViewById(R.id.case_choose_transaction_book);

        channel = (TextView) findViewById(R.id.case__delete_transaction);
        insert = (TextView) findViewById(R.id.case__insert_transaction);

        choose.setOnClickListener(this);
        channel.setOnClickListener(this);
        insert.setOnClickListener(this);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            transaction = (Transaction) bundle.getSerializable("transaction");
            updateView(transaction);
        } else {
            finish();
        }

    }

    private void updateView(Transaction transaction) {
        ImageLoader.getInstance().displayImage(transaction.getImage(), image);
        title.setText(transaction.getTitle());
        author.setText(transaction.getAuthor());
        summary.setText(transaction.getSummary());
        owner.setText(transaction.getOwner());
        condition.setText(transaction.getCondition());
        flag = false;
        //date.setText("无");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.case_choose_transaction_book:
                goChooseMyBookActivity();
                break;
            case R.id.case__delete_transaction:
                TransactionConditionActivity.this.finish();
                break;
            case R.id.case__insert_transaction:
                insertTransaction();
                break;
        }
    }

    private void goChooseMyBookActivity() {
        Intent intent = new Intent(TransactionConditionActivity.this, ChooseMyBookActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("transaction", transaction);
        intent.putExtras(bundle);
        startActivityForResult(intent, 100);
    }

    private void insertTransaction() {
        if (flag) {
            transaction.setState("交易中");
            transaction.update(this, transaction.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(TransactionConditionActivity.this, "申请交易成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(TransactionConditionActivity.this, "申请交易失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(TransactionConditionActivity.this, "请选择要交换的书籍", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    updateChooseBook(intent);
                    break;
            }
        }
    }

    private void updateChooseBook(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            MyBook myBook = (MyBook) bundle.getSerializable("book");
            choose.setVisibility(View.GONE);
            card.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(myBook.getImage(), myImage);
            myTitle.setText(myBook.getTitle());
            myAuthor.setText(myBook.getPublisher()+ " " + myBook.getPubdate());
            mySummary.setText(myBook.getSummary());
            flag = true;
        }
    }
}
