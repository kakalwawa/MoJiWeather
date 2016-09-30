package lanou.mojiweather.tool;

import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 高翔 on 16/9/28.
 */
public class DBTool {
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase writalbeDateBase;
    private DaoMaster daoMaster;
    private LactionDao lactionDao;
    private  DaoSession daoSession ;
    private static DBTool singleton ;

    public static  DBTool getInstance (){
        if (singleton == null){
            synchronized (DBTool.class){
                if (singleton == null){
                    singleton = new DBTool();
                }
            }
        }
        return singleton;
    }

    private DBTool() {
        helper = new DaoMaster.DevOpenHelper(MyApp.getContext() , "Location.db");
        writalbeDateBase = helper.getWritableDatabase();
        daoMaster = new DaoMaster( writalbeDateBase);
        daoSession = daoMaster.newSession() ;
        lactionDao = daoSession.getLactionDao();

    }
    public  void  insert(final Laction laction){
        new Thread(new Runnable() {
            @Override
            public void run() {
                lactionDao.insert(laction);
            }
        }).start();
    }
    public void queryall(Action1<List<Laction>> action1){
        Observable.just("ssss").flatMap(new Func1<String, Observable<List<Laction>>>() {
            @Override
            public Observable<List<Laction>> call(String s) {
                QueryBuilder<Laction> queryBuilder = lactionDao.queryBuilder();
                return Observable.just(queryBuilder.list());
            }
        }).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(action1);

    }
    public void conditionQuery(final String name , Action1<List<Laction>> action1){
        Observable.just(name).flatMap(new Func1<String, Observable<List<Laction>>>() {
            @Override
            public Observable<List<Laction>> call(String s) {
                QueryBuilder<Laction> queryBuilder = lactionDao.queryBuilder();
                queryBuilder.where(LactionDao.Properties.Name.eq(name)).orderAsc(LactionDao.Properties.Name);
                return Observable.just(queryBuilder.list());
            }
        }).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(action1);
    }
    public void delete (final Laction laction){
        new Thread(new Runnable() {
            @Override
            public void run() {
                lactionDao.delete(laction);
            }
        }).start();
    }
}
