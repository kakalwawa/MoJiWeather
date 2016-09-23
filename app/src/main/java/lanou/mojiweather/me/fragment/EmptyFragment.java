package lanou.mojiweather.me.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.mojiweather.R;

/**
 *  该Fragment是用于MainDialogFragment中的pager，为了实现滑动隐藏交互Fragment的
 * 这个大家可以不用修改，但是根据各自的需求而定
 * Created by shiyujia on 16/9/23.
 */
public class EmptyFragment extends Fragment {
    private static final String TAG = "TAG_EmptyFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }
}
