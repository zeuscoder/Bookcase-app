package com.zeus.bookcase.app.user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.base.utils.LogOutState;
import com.zeus.bookcase.app.base.utils.LoginCOntext;
import com.zeus.bookcase.app.user.model.User;

import cn.bmob.v3.BmobUser;

/**
 * Created by zeus_coder on 2016/3/14.
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private Button exit;
    private TextView nickName;
    private TextView gender;

    private LinearLayout address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_settings);
        initTopButton(R.string.activity_settings, R.mipmap.app__top_bar_arrow_back, 0);
        initView();
    }

    private void initView() {
        User user = BmobUser.getCurrentUser(this, User.class);
        exit = (Button) findViewById(R.id.user_exit);
        exit.setOnClickListener(this);
        nickName = (TextView) findViewById(R.id.user_nick_name);
        gender = (TextView) findViewById(R.id.user_gender);
        if(user != null) {
            nickName.setText(user.getNickName());
            //gender.setText(user.getGender() ? "男" : "女");
        }

        address = (LinearLayout) findViewById(R.id.address);
        address.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.user_exit:
                exitUser();
                break;
            case R.id.address:
                goAddressActivity();
                break;
        }
    }

    private void goAddressActivity() {
        startActivity(new Intent(SettingsActivity.this, AddressActivity.class));
    }

    private void exitUser() {
        BmobUser.logOut(SettingsActivity.this);
        if (BmobUser.getCurrentUser(this, User.class) == null) {
            LoginCOntext.getLoginContext().setState(new LogOutState());
            Toast.makeText(SettingsActivity.this, "退出当前账号成功", Toast.LENGTH_SHORT).show();
            SettingsActivity.this.finish();
        } else {
            Toast.makeText(SettingsActivity.this, "退出当前账号失败", Toast.LENGTH_SHORT).show();
        }
    }

}
