package lanou.mojiweather.me.fragment;


import lanou.mojiweather.R;
import tool.BaseFragment;
import view.MyScrollView;
import view.UserInfoView;

/**
 * Created by 高翔 on 16/9/13.
 */
public class MeFragment extends BaseFragment {
    private MyScrollView myScrollView;
    private UserInfoView userInfoView;
    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        myScrollView = (MyScrollView) getView().findViewById(R.id.my_scrollview);
        userInfoView = (UserInfoView) getView().findViewById(R.id.userInfoView);
        myScrollView.bindView(userInfoView);
    }

    @Override
    protected void initData() {
        myScrollView.setOnScrollChangeListener(new MyScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(int scrollY) {
                userInfoView.onChange(scrollY);
            }
        });
    }
}
