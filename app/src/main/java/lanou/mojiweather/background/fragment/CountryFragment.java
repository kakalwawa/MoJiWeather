package lanou.mojiweather.background.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import lanou.mojiweather.BuildConfig;
import lanou.mojiweather.R;
import lanou.mojiweather.background.fragment.CountryAdapter.OnRecyclerItemClickListener;
import lanou.mojiweather.background.fragment.CountryAdapter.ViewHolder;
import lanou.mojiweather.tool.BaseFragment;
import lanou.mojiweather.tool.NetTool;
import lanou.mojiweather.tool.NetTool.ResponseListenner;

/**
 * Created by dllo on 16/9/23.
 */
public class CountryFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    public static final String countryUrl = "http://chanyouji.com/api/destinations/list.json";
    private GridLayoutManager manager;
    SearchPlaceEntity searchPlaceEntity;

    @Override
    protected int setLayout() {
        return R.layout.fragment_country;
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_country);
        countryAdapter = new CountryAdapter(this.getContext());
        manager = new GridLayoutManager(this.getContext(), 3);
    }

    @Override
    protected void initData() {
        try {
            NetTool.getInstance().getData(countryUrl, SearchPlaceEntity.class, new ResponseListenner<SearchPlaceEntity>() {
                @Override
                public void onRespnseComplete(SearchPlaceEntity searchPlaceEntity) {
                    countryAdapter.setSearchPlaceEntity(searchPlaceEntity);
                    countryAdapter.notifyDataSetChanged();
                    CountryFragment.this.searchPlaceEntity = searchPlaceEntity;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(countryAdapter);
        countryAdapter.setOnrecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                Intent intent = new Intent(CountryFragment.this.getContext(),SearchDetailActivity.class);
                String name = searchPlaceEntity.getOther_destinations().get(position).getName();
                int id = searchPlaceEntity.getOther_destinations().get(position).getId();
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });

    }
}
