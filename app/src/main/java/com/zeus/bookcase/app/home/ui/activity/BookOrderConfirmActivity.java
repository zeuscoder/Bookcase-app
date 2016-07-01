package com.zeus.bookcase.app.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.model.Cart;
import com.zeus.bookcase.app.home.model.Order;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.home.model.dao.OrderDao;
import com.zeus.bookcase.app.user.model.Address;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.model.dao.AddressDao;
import com.zeus.bookcase.app.user.ui.activity.AddAddressActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zeus_coder on 2016/2/17.
 */
public class BookOrderConfirmActivity extends BaseActivity implements View.OnClickListener {

    private TextView addressName;
    private TextView addressPhone;
    private TextView addressDetail;

    private ImageView bookImage;
    private TextView bookName;
    private TextView bookNewPrice;
    private TextView bookOldPrice;
    private TextView bookNumber;
    private TextView bookMessage;
    private TextView purchase;
    private TextView allPrices;

    private ImageView minus;
    private ImageView plus;

    private SpannableStringBuilder ssb;
    private int count;
    private double price;
    private double prices;

    private Cart cart;
    private Book book;
    private User user;

    private Address address;
    private AddressDao addressDao;
    private OrderDao orderDao;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_order_confirm);
        initTopButton(R.string.activity_order_confirm, R.mipmap.app__top_bar_arrow_back, 0);

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initViews() {
        addressName = (TextView) findViewById(R.id.order_address_name);
        addressPhone = (TextView) findViewById(R.id.order_address_phone);
        addressDetail = (TextView) findViewById(R.id.order_address_address);
        bookImage = (ImageView) findViewById(R.id.order_book_image);
        bookName = (TextView) findViewById(R.id.order_book_name);
        bookMessage = (TextView) findViewById(R.id.order_message);
        bookNumber = (TextView) findViewById(R.id.order_book_count);
        bookNewPrice = (TextView) findViewById(R.id.order_book_new_price);
        bookOldPrice = (TextView) findViewById(R.id.order_book_old_price);
        minus = (ImageView) findViewById(R.id.order_book_count_minus);
        plus = (ImageView) findViewById(R.id.order_book_count_plus);
        purchase = (TextView) findViewById (R.id.order_purchase);
        allPrices = (TextView) findViewById(R.id.order_book_all_prices);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        purchase.setOnClickListener(this);
    }

    private void initData() {
        user = BmobUser.getCurrentUser(this, User.class);
        addressDao = new AddressDao(this);
        address = addressDao.getFirstData(user);
        orderDao = new OrderDao(this);
        order = new Order();
        if (address != null) {
            addressName.setText(address.getName());
            addressPhone.setText(address.getPhone());
            addressDetail.setText("收货地址： " + address.getAddress());
        } else {
            startActivity(new Intent(BookOrderConfirmActivity.this, AddAddressActivity.class));
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getSerializable("book") != null) {
                book = (Book) bundle.getSerializable("book");
                ImageLoader.getInstance().displayImage(book.getImage(), bookImage);
                bookName.setText(book.getTitle());
                ssb = new SpannableStringBuilder(book.getPrice());
                ssb.setSpan(new StrikethroughSpan(), 0, book.getPrice().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            } else if (bundle.getSerializable("cart") != null) {
                cart = (Cart) bundle.getSerializable("cart");
                ImageLoader.getInstance().displayImage(cart.getImage(), bookImage);
                bookName.setText(cart.getTitle());
                ssb = new SpannableStringBuilder(cart.getPrice());
                ssb.setSpan(new StrikethroughSpan(), 0, cart.getPrice().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            bookNumber.setText(String.valueOf(1));
            bookNewPrice.setText(String.valueOf("10.00元"));
            count = Integer.parseInt(bookNumber.getText().toString());
            price = 10.00;
            prices = count * price;
            allPrices.setText("￥" + String.valueOf(prices));
            bookOldPrice.setText(ssb);
        } else {
            BookOrderConfirmActivity.this.finish();
            Toast.makeText(BookOrderConfirmActivity.this, "暂时缺货", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            Toast.makeText(BookOrderConfirmActivity.this, "订单已支付成功，请勿重复下单！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.order_purchase:
                goOrderPayActivity();
                break;
            case R.id.order_book_count_plus:
                plusNumber();
                break;
            case R.id.order_book_count_minus:
                minusNumber();
                break;
        }
    }

    private void minusNumber() {
        if (count > 1) {
            count -= 1;
            prices = price * count;
            bookNumber.setText(String.valueOf(count));
            allPrices.setText("￥" + String.valueOf(prices));
        }
    }

    private void plusNumber() {
        if (count < 10) {
            count += 1;
            prices = price * count;
            bookNumber.setText(String.valueOf(count));
            allPrices.setText("￥" + String.valueOf(prices));
        }
    }

    private void goOrderPayActivity() {
        order.setUid(user.getObjectId());
        order.setBid(book.getId());
        order.setPrice(String.valueOf("10"));
        order.setCount(String.valueOf(count));
        order.setPay(String.valueOf(prices));
        order.setMessage(bookMessage.getText().toString());
        order.setState("待发货");
        //有问题，跳转出错！
/*        if (!orderDao.insertOrder(order)) {
            startActivityForResult(new Intent(BookOrderConfirmActivity.this, OrderPayResultActivity.class), 0);
        } else {

        }*/
        order.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                startActivityForResult(new Intent(BookOrderConfirmActivity.this, OrderPayResultActivity.class), 0);
                Toast.makeText(BookOrderConfirmActivity.this, "已提交订单", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(BookOrderConfirmActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
