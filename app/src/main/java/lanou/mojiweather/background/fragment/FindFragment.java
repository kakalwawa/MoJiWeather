package lanou.mojiweather.background.fragment;

import android.content.Intent;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import lanou.mojiweather.BuildConfig;
import lanou.mojiweather.R;
import lanou.mojiweather.tool.BaseFragment;


import lanou.mojiweather.tool.NetTool;
import lanou.mojiweather.tool.NetTool.ResponseListenner;



/**
 * Created by dllo on 16/9/14.
 */
public class FindFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerViewFindAdapter recyclerViewFindAdapter;
    public static final String RECYCLERVIEWURL = "http://www.koubeilvxing.com/countrys?lang=zh";
    private ImageView imageViewCamara;
    private FrameLayout frameLayoutPickPic;
    private TextView textViewPickPicCancel;
    private TextView textViewPickFromPics;
    private TextView textViewTakePhoto;
    private GoTopScrollView findgoTopScrollView;
    private ImageView goTopBtnFind;
    @Override
    protected int setLayout() {
        return R.layout.fragment_background_find;
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_background_find);
        recyclerViewFindAdapter = new RecyclerViewFindAdapter(this.getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        imageViewCamara = (ImageView) getView().findViewById(R.id.iv_camara_find);
        frameLayoutPickPic = (FrameLayout) getView().findViewById(R.id.pop_frame_layout_pick_pic_find);
        textViewPickPicCancel = (TextView) getView().findViewById(R.id.tv_pick_pic_cancel);
        textViewPickFromPics = (TextView) getView().findViewById(R.id.tv_pick_from_pics);
        textViewTakePhoto = (TextView) getView().findViewById(R.id.tv_camera);
        goTopBtnFind = (ImageView) getView().findViewById(R.id.go_top_btn_find);
        findgoTopScrollView = (GoTopScrollView) getView().findViewById(R.id.scroll_view_find);

    }

    @Override
    protected void initData() {
        try {
            NetTool.getInstance().getData(RECYCLERVIEWURL, RecyclerViewFindEntity.class, new ResponseListenner<RecyclerViewFindEntity>() {
                @Override
                public void onRespnseComplete(RecyclerViewFindEntity recyclerViewFindEntity) {
                    recyclerViewFindAdapter.setEntity(recyclerViewFindEntity);
//                    if (BuildConfig.DEBUG)
//                        Log.d("FindFragment+++++", recyclerViewFindEntity.getContinents().get(0).getName());
                    recyclerView.setAdapter(recyclerViewFindAdapter);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        findgoTopScrollView.setScrollListener(goTopBtnFind);

         imageViewCamara.setOnClickListener(new OnClickListener() {
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

}
