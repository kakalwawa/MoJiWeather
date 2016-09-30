package lanou.mojiweather.weather.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseActivity;

/**
 * Created by 高翔 on 16/9/27.
 */
public class ScLvAdapter extends BaseAdapter {
    ArrayList<String> arrayList ;
    Context context ;

    public ScLvAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return 1 ;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city ,parent, false);
        }
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(R.id.rv_city);
        GridLayoutManager manager = new GridLayoutManager(context ,3);
        recyclerView.setLayoutManager(manager);
        CityRv cityRv = new CityRv(context);
        cityRv.setArrayList(arrayList);
        return convertView;
    }
}
