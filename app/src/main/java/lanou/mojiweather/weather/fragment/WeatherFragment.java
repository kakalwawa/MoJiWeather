package lanou.mojiweather.weather.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
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

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import lanou.mojiweather.MainActivity;
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
    private TextView[] days;
    private TextView[] dates;
    private TextView[] weathers;
    private ImageView weatherIconOne;
    private ImageView weatherIconTwo;
    private ImageView weatherIconThree;
    private ImageView weatherIconFour;
    private ImageView weatherIconFive;
    private ImageView weatherIconSix;
    private ImageView[] weatherIcons;
    private TextView weatherNightOne;
    private TextView weatherNightTwo;
    private TextView weatherNightThree;
    private TextView weatherNightFour;
    private TextView weatherNightFive;
    private TextView weatherNightSix;
    private TextView[] weatherNights;
    private TextView windOne;
    private TextView windTwo;
    private TextView windThree;
    private TextView windFour;
    private TextView windFive;
    private TextView windSix;
    private TextView[] winds;
    private TextView windSpeedOne;
    private TextView windSpeedTwo;
    private TextView windSpeedThree;
    private TextView windSpeedFour;
    private TextView windSpeedFive;
    private TextView windSpeedSix;
    private TextView[] speeds;
    private ImageView weatherNightIconOne;
    private ImageView weatherNightIconTwo;
    private ImageView weatherNightIconThree;
    private ImageView weatherNightIconFour;
    private ImageView weatherNightIconFive;
    private ImageView weatherNightIconSix;
    private ImageView[] weatherNightIcons;
    private SpeechSynthesizer mTts;
    private WeatherBean bean;
    @Override
    protected int setLayout() {
        return R.layout.fragment_weather;
    }


    @Override
    protected void initView() {
        SpeechUtility.createUtility(mContext, SpeechConstant.APPID + "= 57e27478");
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(WeatherFragment.this.getContext(), null);
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
        weatherNightOne = (TextView) getView().findViewById(R.id.weather_night_one);
        weatherNightTwo = (TextView) getView().findViewById(R.id.weather_night_two);
        weatherNightThree = (TextView) getView().findViewById(R.id.weather_night_three);
        weatherNightFour = (TextView) getView().findViewById(R.id.weather_night_four);
        weatherNightFive = (TextView) getView().findViewById(R.id.weather_night_five);
        weatherNightSix = (TextView) getView().findViewById(R.id.weather_night_six);
        weatherNights = new TextView[]{weatherNightOne ,weatherNightTwo , weatherNightThree , weatherNightFour , weatherNightFive ,
                weatherNightSix};
        windOne = (TextView) getView().findViewById(R.id.wind_one);
        windTwo = (TextView) getView().findViewById(R.id.wind_two);
        windThree = (TextView) getView().findViewById(R.id.wind_three);
        windFour = (TextView) getView().findViewById(R.id.wind_four);
        windFive = (TextView) getView().findViewById(R.id.wind_five);
        windSix = (TextView) getView().findViewById(R.id.wind_six);
        windSpeedOne = (TextView) getView().findViewById(R.id.speed_one);
        windSpeedTwo = (TextView) getView().findViewById(R.id.speed_two);
        windSpeedThree = (TextView) getView().findViewById(R.id.speed_three);
        windSpeedFour = (TextView) getView().findViewById(R.id.speed_four);
        windSpeedFive = (TextView) getView().findViewById(R.id.speed_five);
        windSpeedSix = (TextView) getView().findViewById(R.id.speed_six);
        speeds = new TextView[]{windSpeedOne ,windSpeedTwo ,windSpeedThree , windSpeedFour ,windSpeedFive ,windSpeedSix};
        winds = new TextView[]{windOne ,windTwo , windThree ,windFour ,windFive ,windSix};
        days = new TextView[]{dayOne , dayTwo , dayThree , dayFour , dayFive , daySix};
        dates = new TextView[]{dateOne , dateTwo , dateThree , dateFour , dateFive , dateSix};
        weathers = new TextView[]{weatherOne , weatherTwo , weatherThree ,weatherFour ,weatherFive ,weatherSix};
        weatherIconOne = (ImageView) getView().findViewById(R.id.weathericon_one);
        weatherIconTwo = (ImageView) getView().findViewById(R.id.weathericon_two);
        weatherIconThree = (ImageView) getView().findViewById(R.id.weathericon_three);
        weatherIconFour = (ImageView) getView().findViewById(R.id.weathericon_four);
        weatherIconFive = (ImageView) getView().findViewById(R.id.weathericon_five);
        weatherIconSix = (ImageView) getView().findViewById(R.id.weathericon_six);
        weatherIcons = new ImageView[]{weatherIconOne ,weatherIconTwo , weatherIconThree , weatherIconFour ,weatherIconFive , weatherIconSix};
        weatherNightIconOne = (ImageView) getView().findViewById(R.id.weathernight_icon_one);
        weatherNightIconTwo = (ImageView) getView().findViewById(R.id.weathernight_icon_two);
        weatherNightIconThree = (ImageView) getView().findViewById(R.id.weathernight_icon_three);
        weatherNightIconFour = (ImageView) getView().findViewById(R.id.weathernight_icon_four);
        weatherNightIconFive = (ImageView) getView().findViewById(R.id.weathernight_icon_five);
        weatherNightIconSix = (ImageView) getView().findViewById(R.id.weathernight_icon_six);
        weatherNightIcons = new ImageView[]{weatherNightIconOne ,weatherNightIconTwo ,weatherNightIconThree,weatherNightIconFour,weatherNightIconFive,weatherNightIconSix};
    }

    @Override
    protected void initData() {
        try {
            NetTool.getInstance().getData(URLValues.WEATHER, WeatherBean.class, new NetTool.ResponseListenner<WeatherBean>() {
                @Override
                public void onRespnseComplete(WeatherBean weatherBean) {
                    bean = weatherBean;
                    int[]tempDay = new int[weatherBean.getResults().get(0).getDaily().size()];
                    int[]tempNight = new int[weatherBean.getResults().get(0).getDaily().size()];
                    for (int i = 0; i <weatherBean.getResults().get(0).getDaily().size() ; i++) {
                        tempDay[i] = Integer.valueOf(weatherBean.getResults().get(0).getDaily().get(i).getHigh());
                        tempNight[i] = Integer.valueOf(weatherBean.getResults().get(0).getDaily().get(i).getLow());
                        dates[i].setText(weatherBean.getResults().get(0).getDaily().get(i).getDate());
                        weathers[i].setText(weatherBean.getResults().get(0).getDaily().get(i).getText_day());
                        weatherNights[i].setText(weatherBean.getResults().get(0).getDaily().get(i).getText_night());
                        winds[i].setText(weatherBean.getResults().get(0).getDaily().get(i).getWind_direction()+"风");
                        speeds[i].setText(weatherBean.getResults().get(0).getDaily().get(i).getWind_speed()+"km/h");
                        switch (Integer.valueOf(weatherBean.getResults().get(0).getDaily().get(i).getCode_day())) {
                            case 0:
                                weatherIcons[i].setImageResource(R.mipmap.sunny);
                                break;
                            case 2:
                                weatherIcons[i].setImageResource(R.mipmap.fair);
                                break;
                            case 4:
                                weatherIcons[i].setImageResource(R.mipmap.cloudy);
                                break;
                            case 5:
                                weatherIcons[i].setImageResource(R.mipmap.partlycloudy);
                                break;
                            case 7:
                                weatherIcons[i].setImageResource(R.mipmap.mostlycloudy);
                                break;
                            case 9:
                                weatherIcons[i].setImageResource(R.mipmap.overcast);
                                break;
                            case 10:
                                weatherIcons[i].setImageResource(R.mipmap.shower);
                                break;
                            case 22:
                                weatherIcons[i].setImageResource(R.mipmap.snow);
                                break;
                            case 32:
                                weatherIcons[i].setImageResource(R.mipmap.wind);
                                break;
                            case 30:
                                weatherIcons[i].setImageResource(R.mipmap.fooggy);
                                break;
                            default:
                                weatherIcons[i].setImageResource(R.mipmap.unkonw);
                        }
                        switch (Integer.valueOf(weatherBean.getResults().get(0).getDaily().get(i).getCode_night())) {
                            case 0:
                                weatherNightIcons[i].setImageResource(R.mipmap.clear);
                                break;
                            case 1:
                                weatherNightIcons[i].setImageResource(R.mipmap.clear);
                                break;
                            case 3:
                                weatherNightIcons[i].setImageResource(R.mipmap.fairnight);
                                break;
                            case 4:
                                weatherNightIcons[i].setImageResource(R.mipmap.cloudy);
                                break;
                            case 6:
                                weatherNightIcons[i].setImageResource(R.mipmap.partlycloudynight);
                                break;
                            case 8:
                                weatherNightIcons[i].setImageResource(R.mipmap.mostlycloudynight);
                                break;
                            case 9:
                                weatherNightIcons[i].setImageResource(R.mipmap.overcast);
                                break;
                            case 10:
                                weatherNightIcons[i].setImageResource(R.mipmap.shower);
                                break;
                            case 22:
                                weatherNightIcons[i].setImageResource(R.mipmap.snow);
                                break;
                            case 32:
                                weatherNightIcons[i].setImageResource(R.mipmap.wind);
                                break;
                            case 30:
                                weatherNightIcons[i].setImageResource(R.mipmap.fooggy);
                                break;
                            default:
                            weatherNightIcons[i].setImageResource(R.mipmap.unkonw);
                        }

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
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(0);
        iv.startAnimation(rotateAnimation);
        as.addAnimation(animation);
        as.addAnimation(rotateAnimation);
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
                   // mediaPlayer.stop();
                    mTts.stopSpeaking();
                    isclick = false ;

                }else {
                    drawableAd.start();
                    int a = shared.getInt("icon" , 0);
                    switch (a) {
                        case 0 :
                            //mediaPlayer= MediaPlayer.create(this.getContext() , R.raw.test);
                            mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
                            mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
                            mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
                            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
                            Log.d("WeatherFragment", "aaa");

                            break;
                        case 1 :
                           // mediaPlayer= MediaPlayer.create(this.getContext() , R.raw.testtwo);
                            mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyun");//设置发音人
                            mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
                            mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
                            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端

                            break;
                        case 2 :
                           // mediaPlayer= MediaPlayer.create(this.getContext() , R.raw.testthree);
                            mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoqi");//设置发音人
                            mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
                            mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
                            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端

                            break;
                        case 3 :
                          //  mediaPlayer= MediaPlayer.create(this.getContext() , R.raw.testfour);
                            mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaomei");//设置发音人
                            mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
                            mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
                            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端

                            break;
                    }
//                    mediaPlayer.start();

                    mTts.startSpeaking("今天是"+bean.getResults().get(0).getDaily().get(0).getDate()+"今天天气"+bean.getResults().get(0).getDaily().get(0).getText_day()+
                            "最高温度是"+bean.getResults().get(0).getDaily().get(0).getHigh()+"度"+"最低温度是"+bean.getResults().get(0).getDaily().get(0).getLow()+"度"+
                            "今天的风向是"+bean.getResults().get(0).getDaily().get(0).getWind_direction()+"风风速为"+bean.getResults().get(0).getDaily().get(0).getWind_speed(),mSynListener);
                    Log.d("WeatherFragment", "speak");
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
                if (mTts != null && mTts.isSpeaking()){
                    mTts.stopSpeaking();
                }
//                if (mediaPlayer!= null&&mediaPlayer.isPlaying()){
//                    mediaPlayer.stop();
//                }
                icon.setImageResource(R.mipmap.dlamwm);
                edit.putInt("icon" , 0);
                edit.apply();
                rv.setVisibility(View.GONE);
                break;
            case R.id.icon_second:
                if (drawableAd.isRunning()){
                    drawableAd.stop();
                }
                if (mTts != null && mTts.isSpeaking()){
                    mTts.stopSpeaking();
                }
//                if (mediaPlayer!= null&&mediaPlayer.isPlaying()){
//                    mediaPlayer.stop();
//                }
                icon.setImageResource(R.mipmap.dldtwo);
                edit.putInt("icon" , 1);
                edit.apply();
                rv.setVisibility(View.GONE);
                break;
            case R.id.icon_three:
                if (drawableAd.isRunning()){
                    drawableAd.stop();
                }
                if (mTts != null && mTts.isSpeaking()){
                    mTts.stopSpeaking();
                }
//                if (mediaPlayer!= null&&mediaPlayer.isPlaying()){
//                    mediaPlayer.stop();
//                }
                icon.setImageResource(R.mipmap.three);
                edit.putInt("icon" , 2);
                edit.apply();
                rv.setVisibility(View.GONE);
                break;
            case R.id.icon_four:
                if (drawableAd.isRunning()){
                    drawableAd.stop();
                }
                if (mTts != null && mTts.isSpeaking()){
                    mTts.stopSpeaking();
                }
//                if (mediaPlayer!= null&&mediaPlayer.isPlaying()){
//                    mediaPlayer.stop();
//                }
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
//        if (mediaPlayer != null&&mediaPlayer.isPlaying()){
//            mediaPlayer.stop();
//        }
        if (mTts != null && mTts.isSpeaking()){
            mTts.stopSpeaking();
        }
    }
    private SynthesizerListener mSynListener = new SynthesizerListener() {


        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }


        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };
}


