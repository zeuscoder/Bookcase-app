package com.zeus.bookcase.app.user.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.user.adapter.ShoppingCartAdapter;
import com.zeus.bookcase.app.user.view.AnimCheckBox;


/**
 * Created by zeus_coder on 2016/1/10.
 */
public class UserShoppingCartActivity extends BaseActivity implements AnimCheckBox.OnCheckedChangeListener {

    private ListView goodList;
    private AnimCheckBox allCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_shopping_cart);
        initTopButton(R.string.activity_shopping_cart,R.mipmap.app__top_bar_arrow_back, 0);
        //setContentView(R.layout.user_item_shopping_cart);

        goodList = (ListView) findViewById(R.id.list_shopping_cart);
        goodList.setAdapter(new ShoppingCartAdapter(this));
        allCheck = (AnimCheckBox) findViewById(R.id.user_shopping_cart_all_check);
        allCheck.setChecked(false,false);
        allCheck.setOnCheckedChangeListener(this);
    }

    @Override
    public void onChange(boolean checked) {

    }
}
