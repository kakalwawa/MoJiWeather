package lanou.mojiweather.background.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lanou.mojiweather.R;

/**
 * Created by dllo on 16/9/14.
 */
public class RecyclerViewWorldAdapter extends RecyclerView.Adapter<RecyclerViewWorldAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private RecyclerViewEntity entity;
    private Context context;

    public RecyclerViewWorldAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    public void setEntity(RecyclerViewEntity entity) {
        this.entity = entity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_background_recyclerview,null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            Glide.with(context).load(entity.getList().get(position + 6).getCover()).into(holder.imageView);
            holder.textView.setText(entity.getList().get(position + 6).getName());
        }catch (NullPointerException e){
            //   Log.d("RecyclerVIewAdapter", e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return entity.getList().size() - 6;
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