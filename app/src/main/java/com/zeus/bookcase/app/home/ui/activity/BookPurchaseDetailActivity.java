package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.home.model.dao.CartDao;
import com.zeus.bookcase.app.home.ui.BookPurchaseMessageFragment;
import com.zeus.bookcase.app.home.ui.inter.ISlideCallback;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.ui.activity.LogInActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class BookPurchaseDetailActivity extends AppCompatActivity implements ISlideCallback, View.OnClickListener {

    private TextView purchase;
    private TextView addCart;

    private User user;
    private CartDao cartDao;
    private Book book;

    private String number;

    //上拉图文详情
    //private SlideDetailsLayout slideDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_purchase);
        //initTopButton(R.string.activity_book_detail, R.mipmap.app__top_bar_arrow_back, 0);
        cartDao = new CartDao(this);
        user = BmobUser.getCurrentUser(this, User.class);
        initFragment();
        initData();

        purchase = (TextView) findViewById(R.id.book_purchase);
        purchase.setOnClickListener(this);

        addCart = (TextView) findViewById(R.id.book_add_shopping_cart);
        addCart.setOnClickListener(this);
    }

    private void initFragment() {
        //上拉图文详情
//        slideDetailsLayout = (SlideDetailsLayout) findViewById(R.id.slidedetails);
        BookPurchaseMessageFragment messageFragment = new BookPurchaseMessageFragment();
        Bundle bundle = getIntent().getExtras();
        messageFragment.setArguments(bundle);
        FragmentManager fm1 = getSupportFragmentManager();
        fm1.beginTransaction().replace(R.id.book_purchase_message, messageFragment).commit();
//        FragmentManager fm2 = getSupportFragmentManager();
//        fm2.beginTransaction().replace(R.id.book_purchase_detail, new BookPurchaseDetailFragment()).commit();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            book = (Book) bundle.getSerializable("book");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (user == null) {
            goLoginActivity();
        } else {
            switch (id) {
                case R.id.book_purchase:
                    goOrderConfirmActivity();
                    break;
                case R.id.book_add_shopping_cart:
                    addCart();
                    break;
            }
        }
    }

    private void addCart() {
        number = String.valueOf(1);
        if (cartDao.insertCart(book, user, number)) {
            Toast.makeText(BookPurchaseDetailActivity.this, "已经加入购物车了噢~亲", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BookPurchaseDetailActivity.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
        }

    }

    private void goOrderConfirmActivity() {
        Intent intent = new Intent(BookPurchaseDetailActivity.this, BookOrderConfirmActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goLoginActivity() {
        startActivity(new Intent(BookPurchaseDetailActivity.this, LogInActivity.class));
    }

    @Override
    public void openDetails(boolean smooth) {
        //slideDetailsLayout.smoothOpen(smooth);
    }

    @Override
    public void closeDetails(boolean smooth) {
        //slideDetailsLayout.smoothClose(smooth);
    }
}
