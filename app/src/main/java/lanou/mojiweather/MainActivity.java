package lanou.mojiweather;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import lanou.mojiweather.background.fragment.BackgroundFragment;
import lanou.mojiweather.me.fragment.MeFragment;
import lanou.mojiweather.weather.fragment.WeatherFragment;
import lanou.mojiweather.tool.BaseActivity;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioButton rbWeather;
    private RadioButton rbBackground;
    private  RadioButton rbMe ;
    private RadioGroup rg;



    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rg = (RadioGroup) findViewById(R.id.rg_main);
        rbWeather = (RadioButton) findViewById(R.id.rb_weather);
        rbBackground = (RadioButton) findViewById(R.id.rb_background);
        rbMe = (RadioButton) findViewById(R.id.rb_me);
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.rb_weather);

    }

    @Override
    protected void initDate() {
        Drawable dbWeather = getResources().getDrawable(R.drawable.selector_weather);
        dbWeather.setBounds(0 , 0 , 110 ,110);
        rbWeather.setCompoundDrawables(null , dbWeather ,null , null);
        Drawable dbBackground = getResources().getDrawable(R.drawable.selector_background);
        dbBackground.setBounds(0, 0, 110, 110);
        rbBackground.setCompoundDrawables(null, dbBackground, null, null);
        Drawable dbMe = getResources().getDrawable(R.drawable.selector_me);
        dbMe.setBounds(0, 0, 110, 110);
        rbMe.setCompoundDrawables(null, dbMe, null, null);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (checkedId) {
            case  R.id.rb_weather:
                fragmentTransaction.replace(R.id.main_fragment , new WeatherFragment() );
                break;
            case  R.id.rb_background:
                fragmentTransaction.replace(R.id.main_fragment , new BackgroundFragment());
                break;
            case  R.id.rb_me :
                fragmentTransaction.replace(R.id.main_fragment , new MeFragment());
                break;
        }
      fragmentTransaction.commit();

    }

}
