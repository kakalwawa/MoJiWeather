package lanou.mojiweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

/**
 * 不墨迹地图
 * Created by shiyujia on 16/9/26.
 */
public class MopActivity extends AppCompatActivity {
    public LocationClient mLocClient = null;

    BaiduMap mBaiduMap;
    private MapView mapView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mop);
        //地图初始化
        mapView = (MapView) findViewById(R.id.main_map);
        mBaiduMap = mapView.getMap();
        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //定位初始化

        //设置地图是否什么类型
          mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);


        mLocClient = new LocationClient(this);

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {


                String lat = latLng.toString();
                String lon = lat.substring(39, 54);
                Toast.makeText(MopActivity.this, "点击我", Toast.LENGTH_SHORT).show();
                String l = lat.substring(lat.indexOf(":"), lat.indexOf(","));

            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });


        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");//设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

    }

    @Override
    protected void onDestroy() {

        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mLocClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mapView = null;
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();

    }
}

