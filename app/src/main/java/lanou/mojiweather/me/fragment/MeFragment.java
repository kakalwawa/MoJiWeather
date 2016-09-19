package lanou.mojiweather.me.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import lanou.mojiweather.LoginActivity;
import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseFragment;
import lanou.mojiweather.tool.MyApp;
import lanou.mojiweather.view.ShiScrollView;
import lanou.mojiweather.view.UserInfoView;



/**
 * Created by 高翔 on 16/9/13.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private ShiScrollView myScrollView;
    private UserInfoView userInfoView;
    private ImageView head;

    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        myScrollView = (ShiScrollView) getView().findViewById(R.id.my_scrollview);
        userInfoView = (UserInfoView) getView().findViewById(R.id.userInfoView);
        head = (ImageView) getView().findViewById(R.id.BM_Iv_mine_head);
        myScrollView.bindView(userInfoView);
        head.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        myScrollView.setOnScrollChangeListener(new ShiScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(int scrollY) {
                userInfoView.onChange(scrollY);
            }
        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BM_Iv_mine_head:
                Intent intent = new Intent(MyApp.getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
