package tool;

import okhttp3.OkHttpClient;

/**
 * Created by 高翔 on 16/9/12.
 */
public class OkHttpSingleTon {
    public static OkHttpSingleTon okHttpSingleTon;
    private OkHttpClient client;
    public static OkHttpSingleTon getInstance() {
        if (okHttpSingleTon == null) {
            synchronized (OkHttpSingleTon.class) {
                if (okHttpSingleTon == null) {
                    okHttpSingleTon = new OkHttpSingleTon();
                }
            }

        }
        return okHttpSingleTon;
    }
    private OkHttpSingleTon() {
        client = new OkHttpClient();

    }

    public OkHttpClient getClient() {
        return client;
    }


}
