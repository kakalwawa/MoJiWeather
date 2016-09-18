package view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lanou.mojiweather.R;

/**
 * 个人界面 弹性View
 * Created by shiyujia on 16/9/14.
 */
public class UserInfoView extends RelativeLayout {
    private static final String TAG = "TAG_UserInfoView";

    private RelativeLayout mRl_center;
    private ImageView mIv_head;
    private ImageView mIv_share;
    private TextView mTv_username, mTv_username_top;

    public static int MOVE_INIT_X;
    public static int MOVE_FINAL_X;
    public static int MAX_RANGE; //最大的scroll范围  转化为userInfoView控件对应的
    public static final int STANDARD_RANGE = 100;
    private int mRange;

    public static final int HEAD_GONE_VALUE = 50;

    boolean isFirst = true;

    public UserInfoView(Context context) {
        this(context, null);
    }

    public UserInfoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_userinfo, this);
        setBackgroundResource(R.mipmap.personal_back);

        initView();
    }

    void initView() {
        mRl_center = (RelativeLayout) findViewById(R.id.BM_Ll_mine_center);
        mTv_username = (TextView) findViewById(R.id.BM_Tv_mine_username);
        mIv_head = (ImageView) findViewById(R.id.BM_Iv_mine_head);
        mTv_username_top = (TextView) findViewById(R.id.BM_Tv_mine_username_top);
        mIv_share = (ImageView) findViewById(R.id.BM_Iv_mine_share);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isFirst) {
                    MOVE_INIT_X = (int) mIv_share.getX();
                    MOVE_FINAL_X = getMeasuredWidth() - mIv_share.getMeasuredWidth() - getResources().getDimensionPixelSize(R.dimen.userinfo_move_marginRight);

                    isFirst = false;
                }
            }
        });
    }

    public void setMaxRange(int maxRange) {
        MAX_RANGE = maxRange;
    }

    public void onChange(int range) {
        mRange = range * STANDARD_RANGE / MAX_RANGE;

        if (mRange <= HEAD_GONE_VALUE) {
            mRl_center.setVisibility(VISIBLE);

            int alpha = 255 - 255 * (mRange) / HEAD_GONE_VALUE;
            mIv_head.setAlpha(alpha);
            mTv_username.setTextColor(Color.argb(alpha, 255, 255, 255));
            float scale = (float) mRange / STANDARD_RANGE;
            mRl_center.setScaleX((1 - scale)*0.3f+0.7f);
            mRl_center.setScaleY((1 - scale)*0.3f+0.7f);

            mTv_username.setScaleX(1-scale);
            mTv_username.setScaleY(1-scale);
        } else {
            mRl_center.setVisibility(INVISIBLE);
        }

        if (mRange >= STANDARD_RANGE - HEAD_GONE_VALUE) {
            mTv_username_top.setVisibility(VISIBLE);

            int alpha = 255 * (STANDARD_RANGE - mRange) / HEAD_GONE_VALUE;
            mTv_username_top.setTextColor(Color.argb(255 - alpha, 255, 255, 255));
        } else {
            mTv_username_top.setVisibility(INVISIBLE);
        }

        int x = mRange * (MOVE_FINAL_X - MOVE_INIT_X) / STANDARD_RANGE + MOVE_INIT_X;
        mIv_share.setX(x);
    }
}
