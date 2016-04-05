package com.zeus.bookcase.app.user.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.user.model.Address;
import com.zeus.bookcase.app.user.model.User;
import com.zeus.bookcase.app.user.model.dao.AddressDao;

import cn.bmob.v3.BmobUser;


/**
 * Created by zeus_coder on 2016/3/31.
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    private EditText address;
    private EditText name;
    private EditText phone;

    private Button addAddress;

    private AddressDao addressDao;

    private Address addressModel;
    private User user;

    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_add_address);
        initTopButton(R.string.activity_add_address, R.mipmap.app__top_bar_arrow_back, 0);
        addressDao = new AddressDao(this);
        user = BmobUser.getCurrentUser(this, User.class);
        initView();
        initData();
    }

    private void initView() {
        address = (EditText) findViewById(R.id.user_address_detail);
        name = (EditText) findViewById(R.id.user_address_name);
        phone = (EditText) findViewById(R.id.user_address_phone);

        addAddress = (Button) findViewById(R.id.user_add_address);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressModel = new Address();
                addressModel.setId(addressDao.getCount(user) + 1 + "");
                addressModel.setUid(user.getObjectId().toString());
                addressModel.setAddress(address.getText().toString());
                addressModel.setName(name.getText().toString());
                addressModel.setPhone(phone.getText().toString());
                if (addressDao.insertAddress(addressModel)) {
                    AddAddressActivity.this.finish();
                } else {
                    Toast.makeText(AddAddressActivity.this, "新增失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            addressModel = (Address) bundle.get("address");
            address.setText(addressModel.getAddress());
            name.setText(addressModel.getName());
            phone.setText(addressModel.getPhone());
            addAddress.setText("修改收货地址");
            flag = true;
        }
    }

    @Override
    public void onClick(View view) {
        addressModel = new Address();
        addressModel.setId(addressDao.getCount(user) + 1 + "");
        addressModel.setUid(user.getObjectId().toString());
        addressModel.setAddress(address.getText().toString());
        addressModel.setName(name.getText().toString());
        addressModel.setPhone(phone.getText().toString());
        if (flag) {
            if (addressDao.updateAddress(addressModel)) {
                AddAddressActivity.this.finish();
            } else {
                Toast.makeText(AddAddressActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (addressDao.insertAddress(addressModel)) {
                AddAddressActivity.this.finish();
            } else {
                Toast.makeText(AddAddressActivity.this, "新增失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
