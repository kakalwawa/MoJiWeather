package lanou.mojiweather.background.fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseFragment;
import lanou.mojiweather.tool.NetTool;
import lanou.mojiweather.tool.NetTool.ResponseListenner;

/**
 * Created by dllo on 16/9/14.
 */
public class BackgroundNowFragment extends BaseFragment {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TurnPicAdapter turnPicAdapter;
    private  SleepThread sleepThread ;
    private  Thread thread ;
    private RecyclerView recyclerView;
    private RecyclerView worldRecyclerView;
    private RecyclerVIewAdapter recyclerViewAdapter;
    public static final String TURNPICURL = "https://rong.36kr.com/api/mobi/roundpics/v4";
    public static final String RECYCLERVIEWURL = "http://www.koubeilvxing.com/search?lang=zh&lat=0.000000&lng=0.000000&module=attraction&page=1&placeId=0&rows=20";
    private Handler handler;
    private RecyclerViewWorldAdapter recyclerViewWorldAdapter;
    private ImageView goTopBtn;
    private GoTopScrollView goTopScrollView;
    private ImageView CamaraimageView;
    private FrameLayout frameLayoutPickPic;
    private TextView textViewPickPicCancel;
    private TextView textViewPickFromPics;
    private TextView textViewTakePhoto;
    @Override
    protected int setLayout() {
        return R.layout.fragment_background_now;
    }

    @Override
    protected void initView() {
        CamaraimageView = (ImageView) getView().findViewById(R.id.iv_camara);
        frameLayoutPickPic = (FrameLayout) getView().findViewById(R.id.pop_frame_layout_pick_pic);
        textViewPickPicCancel = (TextView) getView().findViewById(R.id.tv_pick_pic_cancel);
        textViewPickFromPics = (TextView) getView().findViewById(R.id.tv_pick_from_pics);
        textViewTakePhoto = (TextView) getView().findViewById(R.id.tv_camera);
        recyclerViewAdapter = new RecyclerVIewAdapter(this.getActivity());
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_background_now);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        RecyclerVIewAdapter recyclerVIewAdapter = new RecyclerVIewAdapter(getActivity());
        recyclerView.setAdapter(recyclerVIewAdapter);
        worldRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_world_now);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        worldRecyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewWorldAdapter = new RecyclerViewWorldAdapter(getActivity());
        worldRecyclerView.setAdapter(recyclerVIewAdapter);
        goTopBtn = (ImageView) getView().findViewById(R.id.go_top_btn);
        goTopScrollView = (GoTopScrollView) getView().findViewById(R.id.scroll_view);
        //轮播图
        viewPager = (ViewPager) getView().findViewById(R.id.turn_pic_view_pager);
        linearLayout = (LinearLayout) getView().findViewById(R.id.turn_pic_point);
        turnPicAdapter = new TurnPicAdapter(this.getActivity());
    }

    @Override
    protected void initData() {
        try {
            NetTool.getInstance().getData(TURNPICURL, TurnPicEntity.class, new ResponseListenner<TurnPicEntity>() {
                @Override
                public void onRespnseComplete(final TurnPicEntity turnPicEntity) {
                    turnPicAdapter.setTurnPicEntity(turnPicEntity);
                    final ImageView[] views = new ImageView[turnPicEntity.getData().getPics().size()];

                    for (int i = 0; i < turnPicEntity.getData().getPics().size(); i++) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.iv_point, null);
                        ImageView iv = (ImageView) view.findViewById(R.id.dot_select);
                        views[i] = iv;
                        linearLayout.addView(view);
                    }
                    viewPager.setAdapter(turnPicAdapter);
                    turnPicAdapter.setViewPager(viewPager);
                    viewPager.addOnPageChangeListener(new OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            for (int i = 0; i < turnPicEntity.getData().getPics().size(); i++) {
                                if (viewPager.getCurrentItem() % turnPicEntity.getData().getPics().size() == i) {
                                    views[i].setImageResource(R.mipmap.point_black);
                                } else {
                                    views[i].setImageResource(R.mipmap.point_white);
                                }
                            }
                        }
                    });
                    viewPager.setCurrentItem(3000 , false);
                    views[0].setImageResource(R.mipmap.point_black);
                    handler = new Handler(new Callback() {
                        @Override
                        public boolean handleMessage(Message msg) {
                            int currentItem = msg.what ;
                            viewPager.setCurrentItem(currentItem + 1);
                            for (int i = 0; i < turnPicEntity.getData().getPics().size(); i++) {
                                if (viewPager.getCurrentItem() % turnPicEntity.getData().getPics().size() == i) {
                                    views[i].setImageResource(R.mipmap.point_black);
                                } else {
                                    views[i].setImageResource(R.mipmap.point_white);
                                }
                            }
                            return false;
                        }
                    });
                    sleepThread = new SleepThread(handler, viewPager);
                    thread = new Thread(sleepThread);
                    thread.start();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //RecyclerView 获取网络数据
        try {
            NetTool.getInstance().getData(RECYCLERVIEWURL, RecyclerViewEntity.class, new ResponseListenner<RecyclerViewEntity>() {
                @Override
                public void onRespnseComplete(RecyclerViewEntity recyclerViewEntity) {
                    recyclerViewAdapter.setEntity(recyclerViewEntity);
                    //Log.d("++++++",recyclerViewEntity.getList().get(0).getCityName());
                    recyclerViewAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewWorldAdapter.setEntity(recyclerViewEntity);
                    recyclerViewWorldAdapter.notifyDataSetChanged();
                    worldRecyclerView.setAdapter(recyclerViewWorldAdapter);

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }





        goTopScrollView.setScrollListener(goTopBtn);
        //点击相机获取图库照片
        CamaraimageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayoutPickPic.setVisibility(View.VISIBLE);
            }
        });
        textViewPickPicCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayoutPickPic.setVisibility(View.GONE);
            }
        });
        textViewPickFromPics.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //动作  选择
                intent.setAction(intent.ACTION_PICK);
                //通过URI 指定跳转到系统图库
                intent.setData(Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,101);
                frameLayoutPickPic.setVisibility(View.GONE);
            }
        });
        textViewTakePhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == getActivity().RESULT_OK){
            Uri uri = data.getData();
            ContentResolver cr = this.getActivity().getContentResolver();
            Cursor cursor = cr.query(uri,null,null,null,null);
            if (cursor != null && cursor.moveToFirst()){
                String path = cursor.getString(cursor.getColumnIndex(Media.DATA));

            }
        }
    }
}
