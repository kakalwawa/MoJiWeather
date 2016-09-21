package lanou.mojiweather.view;

import android.view.animation.LinearInterpolator;

/**
 * Created by shiyujia on 16/9/20.
 */
public class JellyInterpolator extends LinearInterpolator {
    private static final String TAG = "TAG_JellyInterpolator";
    private float factor;

    public JellyInterpolator() {
        this.factor = 0.15f;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input)
                * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
