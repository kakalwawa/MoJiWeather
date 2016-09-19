package lanou.mojiweather.background.fragment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lanou.mojiweather.R;
import tool.BaseFragment;

/**
 * Created by 高翔 on 16/9/13.
 */
public class BackgroundFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private BackgroundFragmentAdapter adapter;
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

    }

    @Override
    protected void initData() {
        fragments.add(new BackgroundNowFragment());
        fragments.add(new FindFragment());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
