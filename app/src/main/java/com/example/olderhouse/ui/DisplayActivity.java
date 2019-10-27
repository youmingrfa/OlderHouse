package com.example.olderhouse.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.olderhouse.R;
import com.example.olderhouse.ui.fragment.FirstFragment;
import com.example.olderhouse.ui.fragment.SecondFragment;
import com.example.olderhouse.ui.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private ViewPager vp;
    private List<Fragment> list = new ArrayList<>();
    private ImageView[] imgs = new ImageView[3];
    private ImageView mImg1,mImg2,mImg3;
    private Button enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        initView();

        intData();

    }

    private void initView(){
        vp = findViewById(R.id.vp);
        mImg1 = findViewById(R.id.img1);
        mImg2 = findViewById(R.id.img2);
        mImg3 = findViewById(R.id.img3);
        enter = findViewById(R.id.enter);
    }

    private void intData(){
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        list.add(new ThirdFragment());
        imgs[0] = mImg1;
        imgs[1] = mImg2;
        imgs[2] = mImg3;

        //viewpager添加适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        //viewpager添加切换的监听器
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                getPoint(i);
                if (i==2){
                    enter.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * @param j
     * 判断点是否被选中
     */
    private void getPoint(int j){
        for(int i=0;i<imgs.length;i++){
            if(i == j){
                Glide.with(DisplayActivity.this)
                        .load(R.mipmap.point_selected)
                        .placeholder(R.mipmap.point_normal)
                        .into(imgs[i]);
            } else{
                Glide.with(DisplayActivity.this)
                        .load(R.mipmap.point_normal)
                        .placeholder(R.mipmap.point_normal)
                        .into(imgs[i]);
            }
        }
    }
}
