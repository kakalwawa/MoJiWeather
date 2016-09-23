package lanou.mojiweather.background.fragment;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseFragment;


/**
 * Created by 高翔 on 16/9/13.
 */
public class BackgroundFragment extends BaseFragment implements OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private BackgroundFragmentAdapter adapter;
    private ImageView imageViewSearch;
    @Override
    protected int setLayout() {
        return R.layout.fragment_background;
    }

    @Override
    protected void initView() {
        tabLayout = (TabLayout) getView().findViewById(R.id.tablayout_background);
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager_background);
        fragments = new ArrayList<>();
        adapter = new BackgroundFragmentAdapter(getChildFragmentManager());
        imageViewSearch = (ImageView) getView().findViewById(R.id.iv_search);

    }

    @Override
    protected void initData() {
        fragments.add(new BackgroundNowFragment());
        fragments.add(new FindFragment());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        imageViewSearch.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search:
                Intent intent = new Intent(this.getActivity(),SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
