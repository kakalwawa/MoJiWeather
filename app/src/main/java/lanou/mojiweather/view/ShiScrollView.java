package lanou.mojiweather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import lanou.mojiweather.R;

/**
 * 个人界面 自定义的scrollview
 * Created by shiyujia on 16/9/14.
 */
public class ShiScrollView extends ScrollView {


    public static final String TAG = "MyScrollView";

    /**
     * 首先根据三种状态切割UserInfoView控件的三种高度，
     * userinfo_min_height 100dp
     * userinfo_common_height 200dp
     * userinfo_max_height 300dp
     * 分别为最小高度（弹性高度），普通高度和最大高度。
     */
    public static int USERINFO_MIN_HEIGHT;

    public static int USERINFO_COMMON_HEIGHT;

    public static int USERINFO_MAX_HEIGHT;

    //最小scroll
    public static int MIN_SCROLLY;
    //最大scroll
    public static int MAX_SCROLLY;

    private View mOperateView; //可操控的View

    private OnScrollChangeListener mOnScrollChangeListener;

    boolean isFirst = true;

    public static final int MSG_SCROLL = 1;


    public ShiScrollView(Context context) {
        this(context, null);
    }

    public ShiScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShiScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        USERINFO_MIN_HEIGHT = getResources().getDimensionPixelSize(R.dimen.userinfo_min_height);
        USERINFO_COMMON_HEIGHT = getResources().getDimensionPixelOffset(R.dimen.userinfo_common_height);
        USERINFO_MAX_HEIGHT = getResources().getDimensionPixelOffset(R.dimen.userinfo_max_height);

        MIN_SCROLLY = USERINFO_MAX_HEIGHT - USERINFO_COMMON_HEIGHT;
        MAX_SCROLLY = USERINFO_MAX_HEIGHT - USERINFO_MIN_HEIGHT;
    }

    public void bindView(View view) {
        mOperateView = view;
        /**
         * 我们知道在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件布局要在onResume回调后完成。
         * 所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度。这是获得一个view的宽度和高度的方法之一。
         *
         * OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，
         * 这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。
         * ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。
         */
        mOperateView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                //当控件绘制完成

                if (isFirst) {
                    //scrollTo在当前视图内容偏移至(x , y)坐标处。
                    //smoothScrollTo类似于scrollTo，但是滚动的时候是平缓的而不是立即滚动到某处。另外，smoothScrollTo()方法可以打断滑动动画。
                    smoothScrollTo(0, ShiScrollView.MIN_SCROLLY);

                    ((UserInfoView) mOperateView).setMaxRange(USERINFO_COMMON_HEIGHT - USERINFO_MIN_HEIGHT);
                    isFirst = false;
                }
            }
        });
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int scrollY = t < MAX_SCROLLY ? t : MAX_SCROLLY;  //限定在滚动范围内

        Log.i(TAG, "scrollY:" + scrollY + "  t:" + t);

        if (mOnScrollChangeListener != null) {
            if (scrollY - MIN_SCROLLY > 0) {
                mOnScrollChangeListener.onScrollChange(scrollY - MIN_SCROLLY);  //对外部可见的滚动范围在COMMON_HEIGHT 和 MIN_Height之间
            } else {
                mOnScrollChangeListener.onScrollChange(0);
            }

            if (mOperateView != null) {
                ViewGroup.LayoutParams params = mOperateView.getLayoutParams();
                params.height = ShiScrollView.USERINFO_COMMON_HEIGHT + ShiScrollView.MIN_SCROLLY - scrollY;
                mOperateView.setLayoutParams(params);
            }
        }
    }

    /**
     * 弹性效果
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int scrollY = getScrollY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                if (scrollY < MIN_SCROLLY + (MAX_SCROLLY - MIN_SCROLLY) / 4) {
                    smoothScrollTo(0, MIN_SCROLLY);
                } else if (scrollY < MAX_SCROLLY) {
                    smoothScrollTo(0, MAX_SCROLLY);
                }


                return true;
        }
        return super.onTouchEvent(ev);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener mOnScrollChangeListener) {
        this.mOnScrollChangeListener = mOnScrollChangeListener;
    }

    public interface OnScrollChangeListener {
        void onScrollChange(int scrollY);
    }

}
