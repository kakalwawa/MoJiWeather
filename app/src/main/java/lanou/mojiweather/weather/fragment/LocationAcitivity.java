package lanou.mojiweather.weather.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lanou.mojiweather.MainActivity;
import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseActivity;

/**
 * Created by 高翔 on 16/9/23.
 */
public class LocationAcitivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int setLayout() {
        return R.layout.activity_location;
    }

    @Override
    protected void initView() {
        ImageView iv = (ImageView) findViewById(R.id.plus);
        iv.setOnClickListener(this);

    }

    @Override
    protected void initDate() {

    }

    @Override
    public void finish() {
        super.finish();
        Intent intent = new Intent(LocationAcitivity.this , MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus :
                Intent intent = new Intent(LocationAcitivity.this , SearchCityActivity.class);
                startActivity(intent);
                break;
        }

    }
}
