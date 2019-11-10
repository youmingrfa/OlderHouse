package com.example.olderhouse.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.olderhouse.R;
import com.example.olderhouse.ui.adapter.InstitutionSelectAdapter;
import com.example.olderhouse.ui.bean.DropBean;
import com.example.olderhouse.ui.bean.SelectInstitution;
import com.example.olderhouse.ui.provided.OnItemClickListener;
import com.example.olderhouse.ui.widgets.RecyclerItemDecoration;
import com.heiko.dropwidget.DropBeanFlag;
import com.heiko.dropwidget.DropDownButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author rfa
 */
public class InstitutionSelectActivity extends AppCompatActivity implements View.OnClickListener,LocationSource, AMapLocationListener {

    private RecyclerView institutionRecycler;

    private DropDownButton institutionEntire,institutionType,institutionAround;

    private FrameLayout layoutMask;

    private TextView selectButton;

    private LinearLayout searchBar;

    private MapView mapView;//地图控件
    private AMap aMap;//地图对象

    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    private LocationSource.OnLocationChangedListener mListener = null;//定位监听器

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_institution_select);

        //初始化地图
        initMap(savedInstanceState);
        //定位
        initLoc();

        initView();

        initData();

        initListener();
    }

    private void initMap(Bundle savedInstanceState){
        MapView mapView = (MapView) findViewById(R.id.map);//找到地图控件
        mapView.onCreate(savedInstanceState);//创建地图
        aMap = mapView.getMap();//获取地图对象

        //设置显示定位按钮 并且可以点击
        UiSettings settings = aMap.getUiSettings();
        //设置定位监听
        aMap.setLocationSource(this);
        // 是否显示定位按钮
        settings.setMyLocationButtonEnabled(true);
        // 是否可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);

        //定位的小图标 默认是蓝点 这里自定义一团火，其实就是一张图片
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_shoot));
        myLocationStyle.radiusFillColor(android.R.color.transparent);
        myLocationStyle.strokeColor(android.R.color.transparent);
        aMap.setMyLocationStyle(myLocationStyle);

    }

    //定位
    private void initLoc() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private List<DropBeanFlag> initDatas() {
        List<DropBeanFlag> dropBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dropBeans.add(new DropBean(i, "item" + i));
        }
        return dropBeans;
    }

    private void initView(){
        institutionRecycler = findViewById(R.id.all_institution);

        institutionEntire = findViewById(R.id.institution_entire);
        institutionType = findViewById(R.id.institution_type);
        institutionAround = findViewById(R.id.institution_around);
        layoutMask = findViewById(R.id.layout_mask);

        selectButton = findViewById(R.id.select);
        searchBar = findViewById(R.id.search_bar);
    }

    private void initListener(){
        selectButton.setOnClickListener(this);
    }

    private void initData(){

        final List<DropBeanFlag> dropBeans = initDatas();
//        institutionEntire.attach(this,dropBeans,0,0.5f,layoutMask);
//        institutionType.attach(this,dropBeans,0,0.5f,layoutMask);
//        institutionAround.attach(this,dropBeans,0,0.5f,layoutMask);

        //RecyclerView的设置
        final StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        institutionRecycler.setLayoutManager(layoutManager);
        institutionRecycler.addItemDecoration(new RecyclerItemDecoration());
        InstitutionSelectAdapter adapter = new InstitutionSelectAdapter(this);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                Intent intent = new Intent(InstitutionSelectActivity.this,InstitutionDetailActivity.class);
                startActivity(intent);
            }
        });
        institutionRecycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.select:
                final PopupWindow popupWindow=new PopupWindow(InstitutionSelectActivity.this);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
                popupWindow.setFocusable(false);
                View view = View.inflate(InstitutionSelectActivity.this,R.layout.elect_institution,null);
                popupWindow.setContentView(view);
                Button restart = view.findViewById(R.id.elect_restart);
                Button sure = view.findViewById(R.id.elect_sure);
                restart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setBackgroundAlpha(1f);
                        popupWindow.dismiss();
                    }
                });
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setBackgroundAlpha(1f);
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(searchBar, Gravity.RIGHT, 0,0);
                setBackgroundAlpha(0.8f);
                break;
            default:
                break;
        }
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
                    aMap.addMarker(getMarkerOptions(aMapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + "" + aMapLocation.getProvince() + "" + aMapLocation.getCity() + "" +
                            aMapLocation.getProvince() + "" + aMapLocation.getDistrict() + "" +
                            aMapLocation.getStreet() + "" + aMapLocation.getStreetNum());
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());

                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    //自定义一个图钉，并且设置图标，当我们点击图钉时，显示设置的信息
    private MarkerOptions getMarkerOptions(AMapLocation amapLocation) {
        //设置图钉选项
        MarkerOptions options = new MarkerOptions();
        //图标
//        options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_arrow));
        //位置
        options.position(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude()));
        StringBuffer buffer = new StringBuffer();
        buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" +
                amapLocation.getCity() +  "" + amapLocation.getDistrict() + "" +
                amapLocation.getStreet() + "" + amapLocation.getStreetNum());
        //标题
        options.title(buffer.toString());
        //子标题
        options.snippet("这里好火");
        //设置多少帧刷新一次图片资源
        options.period(60);

        return options;
    }

    //激活定位
    @Override
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    //停止定位
    @Override
    public void deactivate() {
        mListener = null;
    }
}
