package tool;

import android.app.Application;
import android.content.Context;



/**
 * Created by dllo on 16/8/15.
 */
public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
//        Bmob.initialize(this,"60911802d6b8da3f2f135c982b2bbaf3");


        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
//        BmobConfig config = new BmobConfig.Builder(this)
//                //设置appkey
//                .setApplicationId("60911802d6b8da3f2f135c982b2bbaf3")
//                //请求超时时间（单位为秒）：默认15s
//                .setConnectTimeout(30)
//                //文件分片上传时每片的大小（单位字节），默认512*1024
//                .setUploadBlockSize(1024 * 1024)
//                //文件的过期时间(单位为秒)：默认1800s
//                .setFileExpiration(2500)
//                .build();
//        Bmob.initialize(config);
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
