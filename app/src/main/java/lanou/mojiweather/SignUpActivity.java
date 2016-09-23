package lanou.mojiweather;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import lanou.mojiweather.tool.BaseActivity;

/**
 * 注册界面
 * Created by shiyujia on 16/9/22.
 */
public class SignUpActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG_SignUpActivity";
    private Button signUp;
    private EditText tel ,pass;

    @Override
    protected int setLayout() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initView() {
        signUp = (Button) findViewById(R.id.register_btn);
        tel = (EditText) findViewById(R.id.login_tel);
        pass = (EditText) findViewById(R.id.login_password);
    }

    @Override
    protected void initDate() {
        signUp.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:
                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(tel.getText().toString());
                bmobUser.setPassword(pass.getText().toString());
                bmobUser.signUp(new SaveListener<Object>() {
                    @Override
                    public void done(Object o, BmobException e) {
                        if(e == null){
                            Toast.makeText(SignUpActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(SignUpActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
                break;
        }
    }
}
