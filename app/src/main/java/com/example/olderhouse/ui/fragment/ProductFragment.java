package com.example.olderhouse.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olderhouse.R;
import com.example.olderhouse.ui.widgets.BannerLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static com.example.olderhouse.ui.utils.ImageUtil.imageUrls;

public class ProductFragment extends Fragment {

    private Banner banner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product, container, false);

        initView(v);

        initData();

        return v;
    }

    private void initView(View v){
        banner = v.findViewById(R.id.banner);
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

}
