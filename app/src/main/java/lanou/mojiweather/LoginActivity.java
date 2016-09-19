package lanou.mojiweather;

import android.view.View;
import android.widget.ImageView;

import lanou.mojiweather.tool.BaseActivity;

/**
 * Created by shiyujia on 16/9/19.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG_LoginActivity";
    private ImageView back;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.login_iv_back);

    }

    @Override
    protected void initDate() {
        back.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_iv_back:
                finish();
                break;
        }
    }
}
