package tool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by dllo on 16/8/15.
 */
public abstract class BaseActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayout());
        initView();
        initDate();


    }

    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initDate();

    //简化findViewById
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }
}
