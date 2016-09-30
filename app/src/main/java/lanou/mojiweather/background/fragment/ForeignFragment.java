package lanou.mojiweather.background.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.IOException;

import lanou.mojiweather.R;
import lanou.mojiweather.background.fragment.ForeignAdapter.OnRecyclerViewItemClickListener;
import lanou.mojiweather.background.fragment.ForeignAdapter.ViewHolder;
import lanou.mojiweather.tool.BaseFragment;
import lanou.mojiweather.tool.NetTool;
import lanou.mojiweather.tool.NetTool.ResponseListenner;

/**
 * Created by dllo on 16/9/23.
 */
public class ForeignFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ForeignAdapter foreignAdapter;
    public static final String countryUrl = "http://chanyouji.com/api/destinations/list.json";
    private GridLayoutManager manager;
    private SearchPlaceEntity searchPlaceEntity;
    @Override
    protected int setLayout() {
        return R.layout.fragment_foreign;
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_foreign);
        foreignAdapter = new ForeignAdapter(this.getContext());
        manager = new GridLayoutManager(this.getContext(), 3);

    }

    @Override
    protected void initData() {
        try {
            NetTool.getInstance().getData(countryUrl, SearchPlaceEntity.class, new ResponseListenner<SearchPlaceEntity>() {
                @Override
                public void onRespnseComplete(SearchPlaceEntity searchPlaceEntity) {
                    foreignAdapter.setSearchPlaceEntity(searchPlaceEntity);
                    foreignAdapter.notifyDataSetChanged();
                    ForeignFragment.this.searchPlaceEntity = searchPlaceEntity;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(foreignAdapter);
        foreignAdapter.setListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, ViewHolder viewHolder, int psition) {
                Intent intent = new Intent(ForeignFragment.this.getContext(),SearchDetailActivity.class);
                String name = searchPlaceEntity.getChina_destinations().get(psition).getName();
                int id = searchPlaceEntity.getChina_destinations().get(psition).getId();
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

    }
}
