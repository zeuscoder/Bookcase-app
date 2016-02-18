package com.zeus.bookcase.app.user.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;


/**
 * Created by zeus_coder on 2016/1/16.
 */
public class RegisterActivity extends BaseActivity {

    private ImageView close;
    private Button register;
    private TextView login;
    private TextView look;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_register);

        close = (ImageView) findViewById(R.id.register_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        login = (TextView) findViewById(R.id.user_register_log_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
                RegisterActivity.this.finish();
            }
        });


    }
}
