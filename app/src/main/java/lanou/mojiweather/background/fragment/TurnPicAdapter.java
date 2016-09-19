package lanou.mojiweather.background.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import lanou.mojiweather.R;
import lanou.mojiweather.background.fragment.TurnPicEntity.DataBean.PicsBean;

/**
 * Created by dllo on 16/9/13.
 */
public class TurnPicAdapter extends PagerAdapter implements OnPageChangeListener {
    private TurnPicEntity turnPicEntity ;
    private Context context;
    //private ViewPager viewPager;

    private LayoutInflater inflater;

    public void setTurnPicEntity(TurnPicEntity turnPicEntity) {
        this.turnPicEntity = turnPicEntity;
        notifyDataSetChanged();
    }

    public void setViewPager(ViewPager viewPager) {
        //this.viewPager = viewPager;
    }



    public TurnPicAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return (turnPicEntity != null ? Integer.MAX_VALUE : 0);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_turn_pic,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_item_turn_pic);
        Glide.with(context).load(turnPicEntity.getData().getPics()
                .get(position%turnPicEntity.getData().getPics().size()).getImgUrl()).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
