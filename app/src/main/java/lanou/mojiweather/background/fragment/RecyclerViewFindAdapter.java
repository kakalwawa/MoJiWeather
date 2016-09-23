package lanou.mojiweather.background.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lanou.mojiweather.BuildConfig;
import lanou.mojiweather.R;

/**
 * Created by dllo on 16/9/14.
 */
public class RecyclerViewFindAdapter extends RecyclerView.Adapter<RecyclerViewFindAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private RecyclerViewFindEntity entity;
    private Context context;

    public RecyclerViewFindAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    public void setEntity(RecyclerViewFindEntity entity) {
        this.entity = entity;
    notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_background_recyclerview, parent ,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            Glide.with(context).load(entity.getRecommendplaces().get(position).getCover()).into(holder.imageView);
            holder.textView.setText(entity.getRecommendplaces().get(position).getName());
        }catch (NullPointerException e){
            //   Log.d("RecyclerVIewAdapter", e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return entity.getRecommendplaces().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_rv_item);
            textView = (TextView) itemView.findViewById(R.id.tv_rv_item);
        }
    }
}
