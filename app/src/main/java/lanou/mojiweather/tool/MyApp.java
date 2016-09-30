package lanou.mojiweather.tool;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

import cn.bmob.v3.Bmob;



/**
 * Created by 高翔 on 16/8/15.
 */
public class MyApp extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //默认初始化 通常会在Application的onCreate方法里写
        //第一参数 : context
        //第二参数 : 你的应用id
        Bmob.initialize(this,"94f676ae4a1d8063cf6701436a06f2bb");

        SDKInitializer.initialize(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
