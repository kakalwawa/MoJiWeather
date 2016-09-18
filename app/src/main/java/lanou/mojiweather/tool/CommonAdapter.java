package lanou.mojiweather.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 高翔 on 16/9/13.
 * 通用的Adapter
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private List<T> beanList ;
    private LayoutInflater inflater ;
    private  int convertViewId ;
    public CommonAdapter(List<T> beanList , Context context , int id){
        this.beanList = beanList;
        this.inflater = LayoutInflater.from(context);
        this.convertViewId = id ;
    }
    @Override
    public int getCount() {
        return beanList == null ? 0 : beanList.size();
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
        CommonViewHolder commonViewHolder = CommonViewHolder.getHolder(convertView , inflater , convertViewId ,parent);
        setData(beanList.get(position) ,commonViewHolder);
        return commonViewHolder.getConvertView();
    }
    public abstract void setData(T t ,CommonViewHolder viewHolder);

}
