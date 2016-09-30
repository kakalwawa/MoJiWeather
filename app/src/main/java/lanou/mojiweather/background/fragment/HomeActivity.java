package lanou.mojiweather.background.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import lanou.mojiweather.MainActivity;
import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseActivity;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends BaseActivity implements OnClickListener {
    private EditText cityET;
    private TextView queryTV;
    private TextView weatherTV;
    private static final String WEATHER_API_URL = "http://php.weather.sina.com.cn/xml.php?city=%s&password=DJOYnieT8234jlsK&day=0";
    private Subscription subscription;
    @Override
    protected int setLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        cityET = (EditText) findViewById(R.id.city);
        queryTV = (TextView) findViewById(R.id.query);
        weatherTV = (TextView) findViewById(R.id.weather);
    }

    @Override
    protected void initDate() {
        queryTV.setOnClickListener(this);
        weatherTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.query){
            weatherTV.setText("");
            String city = cityET.getText().toString();
            if (TextUtils.isEmpty(city)){
                Toast.makeText(this, "城市不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            observableAsNormal(city);

        }

    }
    //获取指定城市的天气情况
    private String getWeather(String city) throws Exception {
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            String urlString = String.format(WEATHER_API_URL, URLEncoder.encode(city,"GBK"));
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.connect();
            //处理返回结果
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while (!TextUtils.isEmpty(line = reader.readLine()))
                buffer.append(line);
            return buffer.toString();

        }finally {
            if (connection != null){
                connection.disconnect();
            }
            if (reader != null){
                reader.close();
            }
        }

    }
    private WeatherEntity parseWeather(String weatherXml){
        //采用pull方式解析xml
        StringReader reader = new StringReader(weatherXml);
        XmlPullParser xmlPullParser = Xml.newPullParser();
        WeatherEntity weatherEntity = null;
        try {
            xmlPullParser.setInput(reader);
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        weatherEntity = new WeatherEntity();
                        break;
                    case XmlPullParser.START_TAG:
                        String nodeName = xmlPullParser.getName();
                        if ("city".equals(nodeName)){
                            weatherEntity.city = xmlPullParser.nextText();
                        }else if ("savedate_weather".equals(nodeName)){
                            weatherEntity.date = xmlPullParser.nextText();
                        }else if("temperature1".equals(nodeName)) {
                            weatherEntity.temperature = xmlPullParser.nextText();
                        } else if("temperature2".equals(nodeName)){
                            weatherEntity.temperature += "-" + xmlPullParser.nextText();
                        } else if("direction1".equals(nodeName)){
                            weatherEntity.direction = xmlPullParser.nextText();
                        } else if("power1".equals(nodeName)){
                            weatherEntity.power = xmlPullParser.nextText();
                        } else if("status1".equals(nodeName)){
                            weatherEntity.status = xmlPullParser.nextText();
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
            return weatherEntity;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            reader.close();
        }
    }
    //采用普通的写法创建Observable
    private void observableAsNormal(final String city){
        subscription = (Subscription) Observable.create(new Observable.OnSubscribe<WeatherEntity>() {
            @Override
            public void call(Subscriber<? super WeatherEntity> subscriber) {
                //如果已经取消订阅 则直接退出
                if (subscriber.isUnsubscribed()) return;
                //开网络连接请求获取天气预报,返回结果是xml格式
                try {
                    String weatherXml = getWeather(city);
                    //解析xml格式 ,返回weather实例
                    WeatherEntity weather = parseWeather(weatherXml);
                    //发布事件通知订阅者
                    subscriber.onNext(weather);
                    //事件通知完成
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).subscribeOn(Schedulers.newThread())//让Observable运行在新线程中
        .observeOn(AndroidSchedulers.mainThread())//让subscriber运行在主线程中
        .subscribe(new Subscriber<WeatherEntity>() {
            @Override
            public void onCompleted() {
                //对应 subscriber.onCompleted();
                //这里些时间发布后的处理逻辑
            }

            @Override
            public void onError(Throwable e) {
                //对应上面 subscriber.onError(e);
                //这里写出异常后的处理逻辑
                Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNext(WeatherEntity weatherEntity) {
                //对应subscriber.OnNext(weather);
                //这里写获取到某一个事件通知后的处理逻辑
                if (weatherEntity != null)
                    weatherTV.setText(weatherEntity.toString());

            }
        });
    }


















}
