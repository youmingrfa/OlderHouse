package com.example.olderhouse.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.olderhouse.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mPasswordLayout;
    private TextView mForgetPsdView,mRegisterView;
    private EditText mAccountView,mPasswordView;
    private ImageView mClearAccountView, mClearPasswordView;
    private Button mLoginView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initListener();
    }

    public void initView(){
        mAccountView = findViewById(R.id.et_input_account);
        mPasswordView = findViewById(R.id.et_input_password);
        mClearAccountView = findViewById(R.id.iv_clear_account);
        mClearPasswordView = findViewById(R.id.iv_clear_password);
        mLoginView = findViewById(R.id.btn_login);
        mForgetPsdView = findViewById(R.id.tv_forget_password);
        mRegisterView = findViewById(R.id.tv_register_account);
        mPasswordLayout = findViewById(R.id.rl_password_layout);
    }

    public void initData(){
        mPasswordView.setLetterSpacing(0.2f);

    }

    public void initListener(){
        mClearAccountView.setOnClickListener(this);
        mClearPasswordView.setOnClickListener(this);
        mForgetPsdView.setOnClickListener(this);
        mRegisterView.setOnClickListener(this);
        mLoginView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.iv_clear_account:
                mAccountView.setText("");
                break;
            case R.id.iv_clear_password:
                mPasswordView.setText("");
                break;
            case R.id.tv_forget_password:
                intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_register_account:
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
