package com.zeus.bookcase.app.user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.base.utils.LogOutState;
import com.zeus.bookcase.app.base.utils.LoginContext;
import com.zeus.bookcase.app.base.utils.LoginState;
import com.zeus.bookcase.app.user.model.User;

import cn.bmob.v3.listener.SaveListener;


/**
 * Created by zeus_coder on 2016/1/14.
 */
public class LogInActivity extends BaseActivity implements View.OnClickListener{


    private ImageView close;
    //
    private TextView register;
    private TextView look;
    //登录信息
    private EditText email;     //用户邮箱
    private EditText password;  //用户密码
    private Button login;       //登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_login);
        initView();
    }

    private void initView() {
        email = (EditText) findViewById(R.id.user_log_in_email);
        password = (EditText) findViewById(R.id.user_log_in__password);
        //登录按钮
        login = (Button) findViewById(R.id.user_log_in);
        login.setOnClickListener(this);
        //退出登录界面
        close = (ImageView) findViewById(R.id.log_in_close);
        close.setOnClickListener(this);
        //跳转注册界面
        register = (TextView) findViewById(R.id.user_log_in_register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.log_in_close:
                LogInActivity.this.finish();
                break;
            case R.id.user_log_in_register:
                goRegisterActivity();
                break;
            case R.id.user_log_in:
                login();
                break;
        }
    }

    private void goRegisterActivity() {
        startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
        LogInActivity.this.finish();
    }

    private void login() {
        User user = new User();
        user.setUsername(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.login(this, new SaveListener() {
            @Override
            public void onSuccess() {
                LoginContext.getLoginContext().setState(new LoginState());
                Toast.makeText(LogInActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                LogInActivity.this.finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LogInActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
