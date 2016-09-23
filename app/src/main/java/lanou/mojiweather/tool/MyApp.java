package lanou.mojiweather.tool;

import android.app.Application;
import android.content.Context;

import lanou.mojiweather.tool.DaoMaster.DevOpenHelper;


/**
 * Created by 高翔 on 16/8/15.
 */
public class MyApp extends Application {
    private static Context mContext;
    public static DaoMaster daoMaster;
    public static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
    public static DaoMaster getDaoMaster(){
        DaoMaster.DevOpenHelper helper = new DevOpenHelper(getContext(),"search.db");
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        return daoMaster;
    }
    public static DaoSession getDaoSession(){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
