package com.example.olderhouse.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.olderhouse.R;

public class InstitutionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_institution_detail);

        initView();
        initListener();
        initData();
    }

    private void initView(){
        btnBack = findViewById(R.id.back);
    }

    private void initListener(){
        btnBack.setOnClickListener(this);
    }

    private void initData(){

    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
