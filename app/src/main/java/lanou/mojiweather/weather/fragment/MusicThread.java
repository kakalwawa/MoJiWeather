package lanou.mojiweather.weather.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 高翔 on 16/9/19.
 */
public class MusicThread implements Runnable {
    private Handler handler ;
    private ContentResolver contentResolver ;
    public MusicThread(Handler handler, ContentResolver contentResolver) {
        this.handler = handler;
        this.contentResolver = contentResolver ;
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */

    @Override
    public void run() {
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI , null , null , null , null);
        ArrayList<MusicBean> arrayList = new ArrayList<>();
        if(cursor != null && cursor.moveToFirst()){
            int indexPath = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do{
              String  path = cursor.getString(indexPath);
              MusicBean  bean = new MusicBean();
                bean.setPath(path);
                arrayList.add(bean);
            }while (cursor.moveToNext());
            cursor.close();
        }
         Message message = new Message();
         message.obj = arrayList ;
         message.what = 1;
        handler.sendMessage(message);
    }
}
