package tool;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 高翔 on 16/9/13.
 */
public class NetTool {
    private Handler mHandler ;
    public static NetTool netTool ;
    public  static  NetTool getInstance(){
        if (netTool == null) {
            synchronized (NetTool.class){
                if (netTool == null){
                    netTool = new NetTool();
                }
            }
        }
        return  netTool ;
    }

     private NetTool() {
         mHandler = new Handler(Looper.getMainLooper());
    }

    public <T> void getData(final String url, final Class<T> tClass, final ResponseListenner<T> responseListenner) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpSingleTon.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //请求回来的数据,都在response.body里
                //虽然网络请求是回调的,但是!!!
                //它还是在子线程回调,想要用还得写handler 回调回主线程
                Gson gson = new Gson();
                final T t = gson.fromJson(response.body().string(), tClass);
                mHandler.post(new HandlerRunnable<T>(t,responseListenner ));
            }
        });
    }
    public interface ResponseListenner<T> {
        void onRespnseComplete(T t);
    }
    class HandlerRunnable <T> implements Runnable {
        ResponseListenner responseListenner;
        T t;
        public HandlerRunnable(T t , ResponseListenner responseListenner) {
            this.responseListenner = responseListenner;
            this.t = t;
        }
        @Override
        public void run() {
            responseListenner.onRespnseComplete(t);
        }
    }
}


