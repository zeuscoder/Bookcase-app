package com.zeus.bookcase.app.user.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.model.Cart;
import com.zeus.bookcase.app.home.model.dao.CartDao;
import com.zeus.bookcase.app.user.adapter.ShoppingCartAdapter;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.view.AnimCheckBox;

import java.util.List;

import cn.bmob.v3.BmobUser;


/**
 * Created by zeus_coder on 2016/1/10.
 */
public class UserShoppingCartActivity extends BaseActivity implements AnimCheckBox.OnCheckedChangeListener {

    private ListView goodList;
    private AnimCheckBox allCheck;

    private List<Cart> carts;
    private CartDao cartDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_shopping_cart);
        initTopButton(R.string.activity_shopping_cart,R.mipmap.app__top_bar_arrow_back, 0);
        //setContentView(R.layout.user_item_shopping_cart);
        initData();
        initViews();
    }

    private void initData() {
        cartDao = new CartDao(this);
        user = BmobUser.getCurrentUser(this, User.class);
        carts = cartDao.getAllData(user);
        goodList = (ListView) findViewById(R.id.list_shopping_cart);
        if (carts != null) {
            goodList.setAdapter(new ShoppingCartAdapter(this, carts));
        } else {
            Toast.makeText(UserShoppingCartActivity.this, "你的购物车空空的", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        allCheck = (AnimCheckBox) findViewById(R.id.user_shopping_cart_all_check);
        allCheck.setChecked(false,false);
        allCheck.setOnCheckedChangeListener(this);
    }

    @Override
    public void onChange(boolean checked) {

    }
}
