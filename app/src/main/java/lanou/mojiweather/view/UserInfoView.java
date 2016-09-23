package lanou.mojiweather.view;

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

    private RelativeLayout mRlCenter;
    private ImageView mIvHead;
    private ImageView mIvShare;
    private TextView mTvUsername;
    private TextView mTvUsernameTop;

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
        setBackgroundResource(R.mipmap.bluewater_bg);

        initView();
    }

    void initView() {
        mRlCenter = (RelativeLayout) findViewById(R.id.bm_li_mine_center);
        mTvUsername = (TextView) findViewById(R.id.tv_mine_username);
        mIvHead = (ImageView) findViewById(R.id.mine_iv_head);//头像
        mTvUsernameTop = (TextView) findViewById(R.id.tv_mine_username_top);
        mIvShare = (ImageView) findViewById(R.id.Iv_mine_share);//消息


        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isFirst) {
                    MOVE_INIT_X = (int) mIvShare.getX();
                    MOVE_FINAL_X = getMeasuredWidth() - mIvShare.getMeasuredWidth() - getResources().getDimensionPixelSize(R.dimen.userinfo_move_marginRight);

                    isFirst = false;
                }
            }
        });
    }

    public void setMaxRange(int maxRange) {
        MAX_RANGE = maxRange;
    }

    /**
     * 中间过程动画处理
     *
     * @param range
     */
    public void onChange(int range) {

        mRange = range * STANDARD_RANGE / MAX_RANGE;

        if (mRange <= HEAD_GONE_VALUE) {
            mRlCenter.setVisibility(VISIBLE);

            int alpha = 255 - 255 * (mRange) / HEAD_GONE_VALUE;
            mIvHead.setAlpha(alpha);
            mTvUsername.setTextColor(Color.argb(alpha, 255, 255, 255));
            float scale = (float) mRange / STANDARD_RANGE;
            mRlCenter.setScaleX((1 - scale) * 0.3f + 0.7f);
            mRlCenter.setScaleY((1 - scale) * 0.3f + 0.7f);


        } else {
            mRlCenter.setVisibility(INVISIBLE);
        }

        if (mRange >= STANDARD_RANGE - HEAD_GONE_VALUE) {
            mTvUsernameTop.setVisibility(VISIBLE);

            int alpha = 255 * (STANDARD_RANGE - mRange) / HEAD_GONE_VALUE;
            mTvUsernameTop.setTextColor(Color.argb(255 - alpha, 255, 255, 255));
        } else {
            mTvUsernameTop.setVisibility(INVISIBLE);
        }

        int x = mRange * (MOVE_FINAL_X - MOVE_INIT_X) / STANDARD_RANGE + MOVE_INIT_X;
        mIvShare.setX(x);
    }
}
