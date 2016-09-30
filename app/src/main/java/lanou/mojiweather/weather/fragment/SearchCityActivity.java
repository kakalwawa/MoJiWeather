package lanou.mojiweather.weather.fragment;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseActivity;
import lanou.mojiweather.tool.LineChartViewDouble;
import lanou.mojiweather.tool.PullToZoomListView;

/**
 * Created by 高翔 on 16/9/27.
 */
public class SearchCityActivity extends BaseActivity {

    private PullToZoomListView lv;
    private ScLvAdapter scLvAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activty_sreachcity;
    }

    @Override
    protected void initView() {
        lv = (PullToZoomListView) findViewById(R.id.pulltozoomlv);
        lv.getHeaderView().setImageResource(R.mipmap.view);
        lv.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);
        scLvAdapter = new ScLvAdapter(this);

    }

    @Override
    protected void initDate() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("北京市");
        arrayList.add("大连市");
        arrayList.add("天津市");
        arrayList.add("上海市");
        arrayList.add("重庆市");
        arrayList.add("沈阳市");
        arrayList.add("长春市");
        arrayList.add("哈尔滨市");
        arrayList.add("郑州市");
        arrayList.add("武汉市");
        arrayList.add("长沙市");
        arrayList.add("广州市");
        arrayList.add("深圳市");
        arrayList.add("南京市");
        scLvAdapter.setArrayList(arrayList);
        lv.setAdapter(scLvAdapter);
    }
}