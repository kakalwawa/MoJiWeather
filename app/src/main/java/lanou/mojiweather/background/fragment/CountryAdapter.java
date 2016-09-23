package lanou.mojiweather.background.fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lanou.mojiweather.BuildConfig;
import lanou.mojiweather.R;

/**
 * Created by dllo on 16/9/23.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private SearchPlaceEntity searchPlaceEntity;
    private LayoutInflater inflater;

    public CountryAdapter(Context context) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(searchPlaceEntity.getOther_destinations().get(position).getName());

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
}
