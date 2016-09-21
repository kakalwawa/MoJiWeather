package lanou.mojiweather.weather.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import lanou.mojiweather.R;

import lanou.mojiweather.tool.BaseFragment;
import lanou.mojiweather.tool.LineChartViewDouble;
import lanou.mojiweather.tool.MyScrollView;
import lanou.mojiweather.tool.NetTool;
import lanou.mojiweather.tool.URLValues;
import lanou.mojiweather.view.SceneSurfaceView;

/**
 * Created by 高翔 on 16/9/13.
 */
public class WeatherFragment extends BaseFragment implements View.OnClickListener {
    private boolean isclick ;
    private SceneSurfaceView surfaceView;
    private MyScrollView scrollView;
    private static final int START_ALPHA = 0;
    private static final int END_ALPHA = 225;
    private int fadingHeight = 600;   //当ScrollView滑动到什么位置时渐变消失（根据需要进行调整）
    private Drawable drawable;        //顶部渐变布局需设置的Drawable
    private LinearLayout linearLayout;
    private ImageView iv , voice;
    private AnimationSet as;
    private AnimationDrawable drawableAd;
    private MediaPlayer mediaPlayer;
    private ContentResolver contentResolver;
    private Handler handler;
    private ArrayList<MusicBean> arrayList;
    private ImageView icon;
    private RelativeLayout rv;
    private CircleImageView iconFrist , iconSecond , iconThird , iconFourth;
    private SharedPreferences.Editor edit;
    private SharedPreferences shared;
    private int i;
    private LineChartViewDouble lineChart;
    private TextView dayOne;
    private TextView dayTwo;
    private TextView dayThree;
    private TextView dayFour;
    private TextView dayFive;
    private TextView daySix;
    private TextView dateOne;
    private TextView dateTwo;
    private TextView dateThree;
    private TextView dateFour;
    private TextView dateFive;
    private TextView dateSix;
    private TextView weatherOne;
    private TextView weatherTwo;
    private TextView weatherThree;
    private TextView weatherFour;
    private TextView weatherFive;
    private TextView weatherSix;

    @Override
    protected int setLayout() {
        return R.layout.fragment_weather;
    }


    @Override
    protected void initView() {
        surfaceView = (SceneSurfaceView) getView().findViewById(R.id.weather_scene);
        scrollView = (MyScrollView) getView().findViewById(R.id.scrollView_weather);
        linearLayout = (LinearLayout) getView().findViewById(R.id.weather_title_ll);
        iconFrist = (CircleImageView) getView().findViewById(R.id.icon_first);
        iconSecond = (CircleImageView) getView().findViewById(R.id.icon_second);
        iconThird = (CircleImageView) getView().findViewById(R.id.icon_three);
        iconFourth = (CircleImageView) getView().findViewById(R.id.icon_four);
        rv = (RelativeLayout) getView().findViewById(R.id.select_rl);
        iv = (ImageView) getView().findViewById(R.id.iv_leida);
        icon = (ImageView) getView().findViewById(R.id.iv_icon);
        voice = (ImageView) getView().findViewById(R.id.voice_animation);
        voice.setOnClickListener(this);
        icon.setOnClickListener(this);
        iconFrist.setOnClickListener(this);
        iconSecond.setOnClickListener(this);
        iconThird.setOnClickListener(this);
        iconFourth.setOnClickListener(this);
        rv.setOnClickListener(this);
        drawableAd = (AnimationDrawable) voice.getBackground();
        contentResolver = this.getActivity().getContentResolver();
        lineChart = (LineChartViewDouble) getView().findViewById(R.id.temp_customview);
        dayOne = (TextView) getView().findViewById(R.id.day_one);
        dayTwo = (TextView) getView().findViewById(R.id.day_two);
        dayThree = (TextView) getView().findViewById(R.id.day_three);
        dayFour = (TextView) getView().findViewById(R.id.day_four);
        dayFive = (TextView) getView().findViewById(R.id.day_five);
        daySix = (TextView) getView().findViewById(R.id.day_six);
        dateOne = (TextView) getView().findViewById(R.id.date_one);
        dateTwo = (TextView) getView().findViewById(R.id.date_two);
        dateThree = (TextView) getView().findViewById(R.id.date_three);
        dateFour = (TextView) getView().findViewById(R.id.date_four);
        dateFive = (TextView) getView().findViewById(R.id.date_five);
        dateSix = (TextView) getView().findViewById(R.id.date_six);
        weatherOne = (TextView) getView().findViewById(R.id.weather_one);
        weatherTwo = (TextView) getView().findViewById(R.id.weather_two);
        weatherThree = (TextView) getView().findViewById(R.id.weather_three);
        weatherFour = (TextView) getView().findViewById(R.id.weather_four);
        weatherFive = (TextView) getView().findViewById(R.id.weather_five);
        weatherSix = (TextView) getView().findViewById(R.id.weather_six);


    }

    @Override
    protected void initData() {
        try {
            NetTool.getInstance().getData(URLValues.WEATHER, WeatherBean.class, new NetTool.ResponseListenner<WeatherBean>() {
                @Override
                public void onRespnseComplete(WeatherBean weatherBean) {
                    int[]tempDay = new int[weatherBean.getResults().get(0).getDaily().size()];
                    int[]tempNight = new int[weatherBean.getResults().get(0).getDaily().size()];
                    for (int i = 0; i <weatherBean.getResults().get(0).getDaily().size() ; i++) {
                        tempDay[i] = Integer.valueOf(weatherBean.getResults().get(0).getDaily().get(i).getHigh());
                        tempNight[i] = Integer.valueOf(weatherBean.getResults().get(0).getDaily().get(i).getLow());
                    }

                    lineChart.setTempDay(tempDay);
                    lineChart.setTempNight(tempNight);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawable = getResources().getDrawable(R.color.color_black);
        drawable.setAlpha(START_ALPHA);
        linearLayout.setBackground(drawable);
        scrollView.setOnScrollChangedListener(scrollChangedListener);
        as = new AnimationSet(true);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        //动画持续的时间
        animation.setDuration(2000);
        //动画重复的次数
        animation.setRepeatCount(0);
        //动画重复的模式   REVERSE 倒序执行动画
        // aa.setRepeatMode(Animation.REVERSE);
        //停留在动画行结束的状态
        animation.setFillAfter(true);
        //开始动画f
        iv.startAnimation(animation);
        RotateAnimation ra1 = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra1.setDuration(2000);
        ra1.setRepeatCount(0);
        iv.startAnimation(ra1);
        as.addAnimation(animation);
        as.addAnimation(ra1);
    }
    @Override
    public void onResume() {
        super.onResume();
        surfaceView.resume();
        iv.startAnimation(as);
        String name ="lanou.mojiweather.SETTING";
        shared = getContext().getSharedPreferences(name , Context.MODE_PRIVATE);
        edit = shared.edit();
        i = shared.getInt("icon" , 0);
        switch (i) {
            case 0:
                icon.setImageResource(R.mipmap.dlamwm);
                break;
            case 1:
                icon.setImageResource(R.mipmap.dldtwo);
                break;
            case 2:
                icon.setImageResource(R.mipmap.three);
                break;
            case 3:
                icon.setImageResource(R.mipmap.four);
                break;
        }
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
        switch (v.getId()) {
            case R.id.voice_animation:
                if( isclick ){
                    drawableAd.stop();
                    mediaPlayer.stop();
                    isclick = false ;

                }else {
                    drawableAd.start();
                    int a = shared.getInt("icon" , 0);
                    switch (a) {
                        case 0 :
                            mediaPlayer= MediaPlayer.create(this.getContext() , R.raw.test);
                            break;
                        case 1 :
                            mediaPlayer= MediaPlayer.create(this.getContext() , R.raw.testtwo);
                            break;
                        case 2 :
                            mediaPlayer= MediaPlayer.create(this.getContext() , R.raw.testthree);
                            break;
                        case 3 :
                            mediaPlayer= MediaPlayer.create(this.getContext() , R.raw.testfour);
                            break;
                    }
                    mediaPlayer.start();
                    isclick = true ;
                }

                break;
            case R.id.iv_icon:
                rv.setVisibility(View.VISIBLE);

                break;
            case R.id.icon_first:
                if (drawableAd.isRunning()){
                    drawableAd.stop();
                }
                if (mediaPlayer!= null&&mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                icon.setImageResource(R.mipmap.dlamwm);
                edit.putInt("icon" , 0);
                edit.apply();
                rv.setVisibility(View.GONE);
                break;
            case R.id.icon_second:
                if (drawableAd.isRunning()){
                    drawableAd.stop();
                }
                if (mediaPlayer!= null&&mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                icon.setImageResource(R.mipmap.dldtwo);
                edit.putInt("icon" , 1);
                edit.apply();
                rv.setVisibility(View.GONE);
                break;
            case R.id.icon_three:
                if (drawableAd.isRunning()){
                    drawableAd.stop();
                }

                if (mediaPlayer!= null&&mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                icon.setImageResource(R.mipmap.three);
                edit.putInt("icon" , 2);
                edit.apply();
                rv.setVisibility(View.GONE);
                break;
            case R.id.icon_four:
                if (drawableAd.isRunning()){
                    drawableAd.stop();
                }

                if (mediaPlayer!= null&&mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                icon.setImageResource(R.mipmap.four);
                edit.putInt("icon" , 3);
                edit.apply();
                rv.setVisibility(View.GONE);
                break;
            case R.id.select_rl :
                rv.setVisibility(View.GONE);
                break;
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}


