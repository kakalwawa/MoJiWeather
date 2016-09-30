package lanou.mojiweather.background.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import lanou.mojiweather.BuildConfig;
import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseActivity;
import lanou.mojiweather.tool.NetTool;
import lanou.mojiweather.tool.NetTool.ResponseListenner;

/**
 * Created by dllo on 16/9/25.
 */
public class SearchDetailActivity extends BaseActivity {
    private TextView textView;
    private ListView listView;
    private SearchDetailsAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.activity_search_details;
    }

    @Override
    protected void initView() {
        textView = (TextView) findViewById(R.id.tv_search_place_name);
        listView = (ListView) findViewById(R.id.list_view_search_details);
        adapter = new SearchDetailsAdapter(this);
    }

    @Override
    protected void initDate() {
        findViewById(R.id.iv_back_search_details).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id",12);
        String url = "http://chanyouji.com/api/destinations/trips/" + id + ".json?page=1";
        textView.setText(name);
        Type type = new TypeToken<List<SearchEntity>>(){}.getType();
        try {
            NetTool.getInstance().getDataArray(url, type, new ResponseListenner<List<SearchEntity>>() {
                @Override
                public void onRespnseComplete(List<SearchEntity> searchEntity) {
                    adapter.setSearchEntity(searchEntity);
                    Log.d("SearchDetailActivity+++", searchEntity.get(0).getName());
                    Log.d("SearchDetailActivity", "lalala");

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapter);

    }

}
