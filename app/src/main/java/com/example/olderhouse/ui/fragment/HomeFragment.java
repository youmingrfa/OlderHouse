package com.example.olderhouse.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.olderhouse.R;
import com.example.olderhouse.ui.activity.InstitutionSelectActivity;
import com.example.olderhouse.ui.widgets.BannerLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static com.example.olderhouse.ui.utils.ImageUtil.imageUrls;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Banner banner;
    private ImageButton institution_more;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        initView(v);

        initData();

        initListener();

        return v;
    }

    private void initView(View root){
        banner = root.findViewById(R.id.banner);
        institution_more = root.findViewById(R.id.institution_more);
    }

    private void initData(){
        //轮播图的设置
        banner.setImageLoader(new BannerLoader());
        List<String> imageList = new ArrayList<>();
        for(int i=0;i<imageUrls.length;i++)
            imageList.add(imageUrls[i]);
        banner.setImages(imageList);
        banner.start();
    }

    private void initListener(){
        institution_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.institution_more:
                intent = new Intent(getContext(),InstitutionSelectActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
