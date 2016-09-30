package lanou.mojiweather.weather.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.mojiweather.R;

/**
 * Created by 高翔 on 16/9/27.
 */
public class CityRv extends RecyclerView.Adapter<CityRv.ViewHolder> {
    ArrayList<String> arrayList ;
    Context context ;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public CityRv(Context context) {
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_city , parent ,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     holder.textView.setText(arrayList.get(position));
    }


    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_cityname);}
    }
}
