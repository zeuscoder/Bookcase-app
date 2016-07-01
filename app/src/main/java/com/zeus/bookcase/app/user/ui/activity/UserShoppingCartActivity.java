package com.zeus.bookcase.app.user.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.model.Cart;
import com.zeus.bookcase.app.home.model.Favorite;
import com.zeus.bookcase.app.home.model.dao.CartDao;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;
import com.zeus.bookcase.app.home.ui.activity.BookOrderConfirmActivity;
import com.zeus.bookcase.app.user.adapter.ShoppingCartAdapter;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.view.AnimCheckBox;

import java.util.List;

import cn.bmob.v3.BmobUser;


/**
 * Created by zeus_coder on 2016/1/10.
 */
public class UserShoppingCartActivity extends BaseActivity implements AnimCheckBox.OnCheckedChangeListener {

    private SwipeMenuListView goodList;
    private SwipeMenuCreator creator;
    private AnimCheckBox allCheck;

//    private TextView finish;

    private List<Cart> carts;
    private CartDao cartDao;
    private ShoppingCartAdapter shoppingCartAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_shopping_cart);
        initTopButton(R.string.activity_shopping_cart,R.mipmap.app__top_bar_arrow_back, 0);
        //setContentView(R.layout.user_item_shopping_cart);

        initViews();
        initData();
        initCreator();
        initClick();
    }

    private void initViews() {
//        allCheck = (AnimCheckBox) findViewById(R.id.user_shopping_cart_all_check);
//        allCheck.setChecked(false,false);
//        allCheck.setOnCheckedChangeListener(this);

        goodList = (SwipeMenuListView) findViewById(R.id.list_shopping_carts);
//        finish = (TextView) findViewById(R.id.order_finish);
    }

    private void initData() {
        cartDao = new CartDao(this);
        user = BmobUser.getCurrentUser(this, User.class);
        carts = cartDao.getAllData(user);
        if (carts != null) {
            shoppingCartAdapter = new ShoppingCartAdapter(this, carts);
            goodList.setAdapter(shoppingCartAdapter);
        } else {
            Toast.makeText(UserShoppingCartActivity.this, "你的购物车空空的", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCreator() {
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("结算");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.mipmap.app__ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        goodList.setMenuCreator(creator);
    }

    private void initClick() {
        goodList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Cart cart = carts.get(position);
                switch (index) {
                    case 0:
                        // open
                        buy(cart);
                        break;
                    case 1:
                        // delete
                        delete(cart);
                        break;
                }
                return false;
            }
        });
        goodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cart cart = carts.get(i);
                Intent intent = new Intent(UserShoppingCartActivity.this, BookIntroductionActivity.class);
                intent.putExtra("bookId", cart.getBid().toString());
                startActivity(intent);
            }
        });
//        finish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    private void buy(Cart cart) {
        Intent intent = new Intent(UserShoppingCartActivity.this, BookOrderConfirmActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cart", cart);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void delete(Cart cart) {
        if (cartDao.deleteCart(cart)) {
            Toast.makeText(UserShoppingCartActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            refreshFavoritesList();
        } else {
            Toast.makeText(UserShoppingCartActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshFavoritesList() {
        carts.clear();
        carts.addAll(cartDao.getAllData(BmobUser.getCurrentUser(this, User.class)));
        shoppingCartAdapter.notifyDataSetChanged();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onChange(boolean checked) {

    }
}
