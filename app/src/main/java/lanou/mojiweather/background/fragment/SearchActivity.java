package lanou.mojiweather.background.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseActivity;

/**
 * Created by dllo on 16/9/23.
 */
public class SearchActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SearchActivityAdapter activityAdapter;
    private List<Fragment> fragments;
    private TextView imageViewCancel;
    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        tabLayout = (TabLayout) findViewById(R.id.search_tab);
        viewPager = (ViewPager) findViewById(R.id.search_viewpager);
        activityAdapter = new SearchActivityAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        imageViewCancel = (TextView) findViewById(R.id.tv_search_cancel);
        imageViewCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initDate() {
        fragments.add(new ForeignFragment());
        fragments.add(new CountryFragment());
        fragments.add(new SeasonsFragment());
        activityAdapter.setFragments(fragments);
        viewPager.setAdapter(activityAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
