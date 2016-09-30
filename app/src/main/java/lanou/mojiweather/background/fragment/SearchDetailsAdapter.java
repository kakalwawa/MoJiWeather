package lanou.mojiweather.background.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lanou.mojiweather.BuildConfig;
import lanou.mojiweather.R;

/**
 * Created by dllo on 16/9/25.
 */
public class SearchDetailsAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<SearchEntity> searchEntity;
    private Context context;
    public SearchDetailsAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setSearchEntity(List<SearchEntity> searchEntity) {
        this.searchEntity = searchEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        try {
            return searchEntity.size() ;
        }catch (Exception e){

        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return searchEntity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_search_details_lv,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(searchEntity.get(position).getFront_cover_photo_url()).into(viewHolder.imageViewFrontCoverPhoto);
        viewHolder.textViewName.setText(searchEntity.get(position).getName());
        Log.d("SearchDetailsAdapter+++", searchEntity.get(position).getName());
        viewHolder.textViewStartDate.setText(searchEntity.get(position).getStart_date());
        Glide.with(context).load(searchEntity.get(position).getUser().getImage()).into(viewHolder.imageView);
        return convertView;
    }
    private class ViewHolder{
        private ImageView imageViewFrontCoverPhoto;
        private TextView textViewName;
        private TextView textViewStartDate;
        private ImageView imageView;
        public ViewHolder(View view) {
            imageViewFrontCoverPhoto = (ImageView) view.findViewById(R.id.iv_front_cover_photo);
            textViewName = (TextView) view.findViewById(R.id.tv_name);
            textViewStartDate = (TextView) view.findViewById(R.id.start_data);
            imageView = (ImageView) view.findViewById(R.id.iv_image);

        }
    }
}
