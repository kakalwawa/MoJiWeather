package lanou.mojiweather.background.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import lanou.mojiweather.R;

/**
 * Created by dllo on 16/9/23.
 */
public class ForeignAdapter extends RecyclerView.Adapter<ForeignAdapter.ViewHolder> {
    private SearchPlaceEntity searchPlaceEntity;
    private LayoutInflater inflater;
    private OnRecyclerViewItemClickListener listener;
    public ForeignAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setSearchPlaceEntity(SearchPlaceEntity searchPlaceEntity) {
        this.searchPlaceEntity = searchPlaceEntity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_country_recyclerview,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(searchPlaceEntity.getChina_destinations().get(position).getName());
        if (listener != null){
            holder.textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,holder,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        try {
            return searchPlaceEntity.getChina_destinations().size();
        }catch (Exception e){
            return 0;

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_country_recyclerview);
        }
    }
    public interface OnRecyclerViewItemClickListener{
        void onClick(View view,ViewHolder viewHolder,int psition);
    }
    public void setListener(OnRecyclerViewItemClickListener listener){
        this.listener = listener;
    }
}
