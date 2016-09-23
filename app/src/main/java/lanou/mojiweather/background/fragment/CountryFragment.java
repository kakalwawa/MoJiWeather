package lanou.mojiweather.background.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;

import lanou.mojiweather.BuildConfig;
import lanou.mojiweather.R;
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
                    Log.d("CountryFragment++++++", searchPlaceEntity.getChina_destinations().get(0).getName());
                    countryAdapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(countryAdapter);

    }
}
