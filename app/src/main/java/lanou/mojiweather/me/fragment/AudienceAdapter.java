package lanou.mojiweather.me.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lanou.mojiweather.R;

/**
 * 横向listview的数据适配器，也就是观众列表，后居者可以直接根据自己的需求在这里改功能以及布局文件就ok
 * Created by shiyujia on 16/9/23.
 */
public class AudienceAdapter  extends BaseAdapter {
    private static final String TAG = "TAG_AudienceAdapter";

    private Context mContext;

    public AudienceAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 1000;
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
        return View.inflate(mContext, R.layout.item_audienceadapter, null);
    }
}
