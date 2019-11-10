package com.example.olderhouse.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.olderhouse.R;
import com.example.olderhouse.ui.fragment.FindFragment;
import com.example.olderhouse.ui.fragment.HomeFragment;
import com.example.olderhouse.ui.fragment.MineFragment;
import com.example.olderhouse.ui.fragment.ProductFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private BottomNavigationView navigation;
    private ViewPager viewPager;
    private HomeFragment homeFragment = new HomeFragment();
    private ProductFragment productFragment = new ProductFragment();
    private FindFragment findFragment = new FindFragment();
    private MineFragment mineFragment = new MineFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView(){
        viewPager = findViewById(R.id.viewPager);
        //添加viewPager事件监听（很容易忘）
        viewPager.addOnPageChangeListener(this);
        navigation = findViewById(R.id.navigation);
    }

    private void initData(){
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return homeFragment;
                    case 1:
                        return productFragment;
                    case 2:
                        return findFragment;
                    case 3:
                        return mineFragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            //点击BottomNavigationView的Item项，切换ViewPager页面
            //menu/navigation.xml里加的android:orderInCategory属性就是下面item.getOrder()取的值
            viewPager.setCurrentItem(item.getOrder());
            return true;
        }

    };

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        //页面滑动的时候，改变BottomNavigationView的Item高亮
        navigation.getMenu().getItem(i).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
