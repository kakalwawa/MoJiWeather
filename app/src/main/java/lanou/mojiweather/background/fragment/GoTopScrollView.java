package lanou.mojiweather.background.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;

import lanou.mojiweather.R;


/**
 * Created by dllo on 16/9/19.
 */
public class GoTopScrollView extends ScrollView implements OnClickListener {
    private ImageView goTopBtn;
    private int screenHeight;
    public GoTopScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setScrollListener(ImageView goTopBtn){
        this.goTopBtn = goTopBtn;
        this.goTopBtn.setOnClickListener(this);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //滑动距离超过300px,出现向上按钮
        if (t >= 300){
            goTopBtn.setVisibility(VISIBLE);
        }else {
            goTopBtn.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.go_top_btn){
            this.smoothScrollTo(0,0);
        }

    }
}
