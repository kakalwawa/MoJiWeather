package lanou.mojiweather.weather.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import lanou.mojiweather.R;

import lanou.mojiweather.tool.BaseFragment;
import lanou.mojiweather.tool.MyScrollView;
import lanou.mojiweather.view.SceneSurfaceView;

/**
 * Created by 高翔 on 16/9/13.
 */
public class WeatherFragment extends BaseFragment implements View.OnClickListener {

    private SceneSurfaceView surfaceView;
    private MyScrollView scrollView;
    private static final int START_ALPHA = 0;
    private static final int END_ALPHA = 255;
    private int fadingHeight = 600;   //当ScrollView滑动到什么位置时渐变消失（根据需要进行调整）
    private Drawable drawable;        //顶部渐变布局需设置的Drawable
    private LinearLayout linearLayout;
    private ImageView iv , voice;
    private AnimationSet as;
    private AnimationDrawable drawable1;

    @Override
    protected int setLayout() {
        return R.layout.fragment_weather;
    }


    @Override
    protected void initView() {
        surfaceView = (SceneSurfaceView) getView().findViewById(R.id.weather_scene);
        scrollView = (MyScrollView) getView().findViewById(R.id.scrollView_weather);
        linearLayout = (LinearLayout) getView().findViewById(R.id.weather_title_ll);
        iv = (ImageView) getView().findViewById(R.id.iv_leida);
        voice = (ImageView) getView().findViewById(R.id.voice_animation);
        voice.setOnClickListener(this);
        drawable1 = (AnimationDrawable) voice.getBackground();
    }

    @Override
    protected void initData() {
        drawable = getResources().getDrawable(R.color.color_black);
        drawable.setAlpha(START_ALPHA);
        linearLayout.setBackground(drawable);
        scrollView.setOnScrollChangedListener(scrollChangedListener);
        as = new AnimationSet(true);
        AlphaAnimation aa1 = new AlphaAnimation(0.3f, 1.0f);
        //动画持续的时间
        aa1.setDuration(2000);
        //动画重复的次数
        aa1.setRepeatCount(0);
        //动画重复的模式   REVERSE 倒序执行动画
        // aa.setRepeatMode(Animation.REVERSE);
        //停留在动画行结束的状态
        aa1.setFillAfter(true);
        //开始动画f
        iv.startAnimation(aa1);
        RotateAnimation ra1 = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra1.setDuration(2000);
        ra1.setRepeatCount(0);
        iv.startAnimation(ra1);
        as.addAnimation(aa1);
        as.addAnimation(ra1);
    }

    @Override
    public void onResume() {
        super.onResume();
        surfaceView.resume();
        iv.startAnimation(as);
    }

    private MyScrollView.OnScrollChangedListener scrollChangedListener = new MyScrollView.OnScrollChangedListener() {
        @Override
        public void onScrollChanged(ScrollView who, int x, int y, int oldx, int oldy) {
            if (y > fadingHeight) {
                y = fadingHeight;   //当滑动到指定位置之后设置颜色为纯色，之前的话要渐变---实现下面的公式即可
            }
            drawable.setAlpha(y * (END_ALPHA - START_ALPHA) / fadingHeight + START_ALPHA);
        }
    };

    /**
     * Called when a lanou.mojiweather.view has been clicked.
     *
     * @param v The lanou.mojiweather.view that was clicked.
     */
    @Override
    public void onClick(View v) {
      drawable1.start();
        Log.d("WeatherFragment", "点你了");

    }
}


