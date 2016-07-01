package com.zeus.bookcase.app.cabinet.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.cabinet.model.dao.BookDao;
import com.zeus.bookcase.app.home.api.BaseAsyncHttp;
import com.zeus.bookcase.app.home.api.HttpResponseHandler;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;
import com.zeus.bookcase.app.home.widget.ExpandableTextView;
import com.zeus.bookcase.app.user.model.User;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * Created by zeus_coder on 2016/3/23.
 */
public class BookViewActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.book_title)
    TextView title;
    @Bind(R.id.book_author)
    TextView author;
    @Bind(R.id.book_printer)
    TextView printer;
    @Bind(R.id.book_date)
    TextView date;
    @Bind(R.id.book_price)
    TextView price;
    @Bind(R.id.book_pages)
    TextView pages;
    @Bind(R.id.book_picture)
    ImageView picture;
    @Bind(R.id.book_add_book)
    TextView addBook;
    @Bind(R.id.book_introduction)
    TextView introduction;
    @Bind(R.id.expand_text_view)
    ExpandableTextView content;

    private MyBook book;
    private String isbn;
    private Thread updateThread;

    private BookDao bookDao;
    private User user;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_book_view);
        ButterKnife.bind(this);
        initTopButton(R.string.activity_add_book_result, R.mipmap.app__top_bar_arrow_back, 0);
        bookDao = new BookDao(this);
        user = BmobUser.getCurrentUser(this, User.class);
        initData();
    }

    private void initData() {
        if (getIntent().hasExtra("book")) {
            Bundle bundle = getIntent().getExtras();
            book = (MyBook) bundle.getSerializable("book");
            flag = true;
            updateToView();
        } else if (getIntent().hasExtra("isbn")) {
            isbn = getIntent().getStringExtra("isbn");
            Log.i("lvzimou------isbn",isbn);
            getRequestData(isbn);
        }

    }

    private void getRequestData(String isbn) {
        RequestParams params = new RequestParams();
        BaseAsyncHttp.getReq("/v2/book/isbn/" + isbn, params, new HttpResponseHandler() {
            @Override
            public void jsonSuccess(JSONObject resp) {
                Gson gson = new Gson();
                book = gson.fromJson(String.valueOf(resp), MyBook.class);
                flag = false;
                updateToView();
            }

            @Override
            public void jsonFail(JSONObject resp) {
                if (resp.optInt("code")==6000){
                    Toast.makeText(BookViewActivity.this, "没有找到该图书或者不是图书的二维码", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private void updateToView() {
        title.setText(book.getTitle().trim());
        if (!flag) {
            author.setText(book.getAuthors().trim());
        } else {
            author.setVisibility(View.INVISIBLE);
        }
        printer.setText(book.getPublisher().trim());
        date.setText(book.getPubdate().trim());
        price.setText("￥" + book.getPrice().trim());
        pages.setText(book.getPrice().trim() + "页");
        if(book.getSummary().trim().equals(""))
            content.setVisibility(View.GONE);
        else
            content.setText(book.getSummary());
        ImageLoader.getInstance().displayImage(book.getImage(), picture);
        Log.i("lvzimou----book-id", book.getId().toString());
        Log.i("lvzimou----book-bitmap", book.getImage().toString());
    }

    @Override
    public void onClick(View view) {

    }

    @OnClick(R.id.book_add_book)
    void addBook() {
        book.setUid(user.getObjectId().toString());
        book.setState("未交易");
        if (bookDao.insertBook(book)) {
            BookViewActivity.this.finish();
            Toast.makeText(BookViewActivity.this, "新增书籍成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BookViewActivity.this, "新增书籍失败", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.book_introduction)
    void goIntroductionActivity() {
        Intent intent = new Intent(BookViewActivity.this, BookIntroductionActivity.class);
        intent.putExtra("bookId",book.getId().toString());
        startActivity(intent);
    }
}
