package com.zeus.bookcase.app.user.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuAdapter;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.model.Favorite;
import com.zeus.bookcase.app.home.ui.activity.BookIntroductionActivity;
import com.zeus.bookcase.app.user.adapter.AddressAdapter;
import com.zeus.bookcase.app.user.model.Address;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.model.dao.AddressDao;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by zeus_coder on 2016/3/31.
 */
public class AddressActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "AddressActivity";

    private SwipeMenuListView addressList;
    private SwipeMenuCreator creator;

    private Button addAddress;

    private List<Address> addresses;

    private AddressDao addressDao;

    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_address);
        initTopButton(R.string.activity_address, R.mipmap.app__top_bar_arrow_back, 0);
        addressDao = new AddressDao(this);

        initView();
        initData();
        initCreator();
        initClick();
    }

    private void initView() {
        addressList = (SwipeMenuListView) findViewById(R.id.user_address);
//        View footer = this.getLayoutInflater().inflate(R.layout.user__layout_address_footer_view,null);
        addAddress = (Button)findViewById(R.id.user_add_address);
        addAddress.setOnClickListener(this);
//        addressList.addHeaderView(LayoutInflater.from(this).inflate(R.layout.user__layout_address_footer_view, null), null, false);
    }

    private void initData() {
        addresses = addressDao.getAllData(BmobUser.getCurrentUser(this, User.class));
        if (addresses != null) {
            addressAdapter = new AddressAdapter(this, addresses);
            addressList.setAdapter(addressAdapter);
        }
    }

    private void initCreator() {
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());

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
        addressList.setMenuCreator(creator);
    }

    private void initClick() {
        addressList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Address address = addresses.get(position);
                switch (index) {
                    case 0:
                        // delete
                        delete(address);
                        break;
                }
                return false;
            }
        });
        addressList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Address address = addresses.get(i);
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("address", address);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void delete(Address address) {
        if (addressDao.deleteAddress(address)) {
            Toast.makeText(AddressActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            refreshAddressList();
        } else {
            Toast.makeText(AddressActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshAddressList();
    }

    private void refreshAddressList() {
        // 注意：千万不要直接赋值，如：orderList = ordersDao.getAllDate() 此时相当于重新分配了一个内存 原先的内存没改变 所以界面不会有变化
        // Java中的类是地址传递 基本数据才是值传递
        addresses.clear();
        addresses.addAll(addressDao.getAllData(BmobUser.getCurrentUser(this, User.class)));
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int id =view.getId();
        switch (id) {
            case R.id.user_add_address:
                goAddAddressActivity();
                break;
        }
    }

    private void goAddAddressActivity() {
        startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
