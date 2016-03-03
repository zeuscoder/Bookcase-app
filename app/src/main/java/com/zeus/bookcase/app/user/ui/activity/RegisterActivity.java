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
import com.zeus.bookcase.app.user.model.User;

import cn.bmob.v3.listener.SaveListener;


/**
 * Created by zeus_coder on 2016/1/16.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private ImageView close;
    private TextView login;
    private TextView look;

    //注册信息
    private EditText nickName;     //用户昵称
    private EditText email;        //用户邮箱
    private EditText password;     //用户密码
    private Button register;       //注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_register);
        initView();
    }

    private void initView() {
        //注册信息填写
        nickName = (EditText) findViewById(R.id.user_register_nickname);
        email = (EditText) findViewById(R.id.user_register_email);
        password = (EditText) findViewById(R.id.user_register_password);
        //注册
        register = (Button) findViewById(R.id.user_register);
        register.setOnClickListener(this);
        //退出注册界面
        close = (ImageView) findViewById(R.id.register_close);
        close.setOnClickListener(this);
        //返回登录界面
        login = (TextView) findViewById(R.id.user_register_log_in);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.register_close:
                RegisterActivity.this.finish();
                break;
            case R.id.user_register_log_in:
                goLoginActivity();
                break;
            case R.id.user_register:
                register();
                break;
        }
    }

    private void register() {
        User user = new User();
        user.setNickName(nickName.getText().toString());
        user.setUsername(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                RegisterActivity.this.finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(RegisterActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goLoginActivity() {
        startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
        RegisterActivity.this.finish();
    }
}
