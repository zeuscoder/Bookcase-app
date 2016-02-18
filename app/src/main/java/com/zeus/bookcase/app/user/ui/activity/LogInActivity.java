package com.zeus.bookcase.app.user.ui.activity;

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
 * Created by zeus_coder on 2016/1/14.
 */
public class LogInActivity extends BaseActivity {

    private ImageView close;
    private Button login;
    private TextView register;
    private TextView look;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__activity_login);

        close = (ImageView) findViewById(R.id.log_in_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInActivity.this.finish();
            }
        });

        register = (TextView) findViewById(R.id.user_log_in_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
                LogInActivity.this.finish();
            }
        });
    }
}
