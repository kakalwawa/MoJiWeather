package lanou.mojiweather.background.fragment;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * Created by dllo on 16/9/18.
 */
public class SleepThread implements Runnable {
    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    private Handler handler;

    private ViewPager viewPager;

    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public SleepThread(Handler handler, ViewPager viewPager) {
        this.handler = handler;
        this.viewPager = viewPager;
    }
    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            handler.sendEmptyMessage(viewPager.getCurrentItem());
        }
    }
}
