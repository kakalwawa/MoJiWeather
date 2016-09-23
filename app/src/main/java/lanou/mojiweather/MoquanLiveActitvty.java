package lanou.mojiweather;

import lanou.mojiweather.me.fragment.LiveViewFragment;
import lanou.mojiweather.me.fragment.MainDialogFragment;
import lanou.mojiweather.tool.BaseActivity;

/**
 * Created by shiyujia on 16/9/23.
 */
public class MoquanLiveActitvty extends BaseActivity {
    private static final String TAG = "TAG_MoquanLiveActitvty";

    @Override
    protected int setLayout() {
        return R.layout.actitvty_moquanlive;
    }

    @Override
    protected void initView() {
/*这里可以看到的就是我们将初始化直播的Fragment添加到了这个页面作为填充
        * 并且将MainDialogFragment显示在该页面的顶部已达到各种不同交互的需求*/
        LiveViewFragment liveViewFragment = new LiveViewFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flmain, liveViewFragment).commit();
        new MainDialogFragment().show(getSupportFragmentManager(),"MainDialogFragment");
    }

    @Override
    protected void initDate() {

    }
}
