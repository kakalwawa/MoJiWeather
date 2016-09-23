package lanou.mojiweather.me.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;
import lanou.mojiweather.LoginActivity;
import lanou.mojiweather.MoquanLiveActitvty;
import lanou.mojiweather.MyInFormationActivity;
import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseFragment;
import lanou.mojiweather.tool.MyApp;
import lanou.mojiweather.tool.MyUser;
import lanou.mojiweather.view.ShiScrollView;
import lanou.mojiweather.view.UserInfoView;


/**
 * Created by 高翔 on 16/9/13.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private ShiScrollView myScrollView;
    private UserInfoView userInfoView;
    private ImageView head;
    private MyUser users;
    private TextView username,usernameTop;
    private LinearLayout moquanLive;

    @Override
    public void onResume() {
        super.onResume();
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        if (myUser == null) {
            head.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
            usernameTop.setVisibility(View.VISIBLE);

        } else {

            head.setVisibility(View.VISIBLE);
            users = BmobUser.getCurrentUser(MyUser.class);
            usernameTop.setText(users.getUsername());
            username.setText(users.getUsername());
            Bitmap bitmap = myUser.getIcon();
            head.setImageBitmap(bitmap);

        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        myScrollView = (ShiScrollView) getView().findViewById(R.id.my_scrollview);
        userInfoView = (UserInfoView) getView().findViewById(R.id.userInfoView);
        //用户头像
        head = (ImageView) getView().findViewById(R.id.mine_iv_head);
        //用户名字
        username = (TextView) getView().findViewById(R.id.tv_mine_username);
        usernameTop = (TextView) getView().findViewById(R.id.tv_mine_username_top);
        //墨圈直播
        moquanLive = (LinearLayout) getView().findViewById(R.id.live_moquan);
        moquanLive.setOnClickListener(this);

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
        switch (v.getId()) {
            case R.id.mine_iv_head:
                BmobUser bmobUser = BmobUser.getCurrentUser();
                if (bmobUser == null) {
                    Intent intent = new Intent(MyApp.getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MyApp.getContext(), MyInFormationActivity.class);
                    startActivity(intent);
                }


                break;

            case R.id.live_moquan:
                Intent intent = new Intent(MyApp.getContext(), MoquanLiveActitvty.class);
                startActivity(intent);
                break;
        }
    }
}
