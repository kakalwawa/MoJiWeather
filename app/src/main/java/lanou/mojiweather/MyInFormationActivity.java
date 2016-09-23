package lanou.mojiweather;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import lanou.mojiweather.tool.BaseActivity;
import lanou.mojiweather.tool.MyUser;

/**
 * 个人资料界面
 * Created by shiyujia on 16/9/22.
 */
public class MyInFormationActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TAG_MyInFormationActivity";
    private Button logout ;
    private BmobUser bmobUser;
    private ImageView headPhoto ,back;
    private ImageView photo;

    @Override
    protected int setLayout() {
        return R.layout.activity_myinformation;
    }

    @Override
    protected void initView() {
        logout = bindView(R.id.out_button);
        back = bindView(R.id.return_butn);
        headPhoto = bindView(R.id.head_portraits);
        photo = bindView(R.id.photo);



    }

    @Override
    protected void initDate() {
        logout.setOnClickListener(this);
        back.setOnClickListener(this);
        headPhoto.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.out_button:
                bmobUser = BmobUser.getCurrentUser();
                bmobUser.logOut();
                finish();
                break;
            case R.id.return_butn:
                finish();
                break;

            case R.id.head_portraits:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.
                        Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);

                break;


        }
    }


    public void onActivityResult(int requestCode ,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1 && resultCode == RESULT_OK && data!= null){
            Uri selectedImage = data.getData();

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            photo.setImageBitmap(bitmap);
            cropPhoto(selectedImage);
            cursor.close();
        }else if (requestCode == 3 && resultCode == RESULT_OK && data!=null) {
            Bitmap bitmap = data.getParcelableExtra("data");
            photo.setImageBitmap(bitmap);
            MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
            if (myUser != null){
                myUser.setIcon(bitmap);
                myUser.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){
                            Toast.makeText(MyInFormationActivity.this, "更新成功", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(MyInFormationActivity.this, "更新失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }


        }
    }

    /**
     * 图片剪裁的方法
     * @param uri
     */

    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);

    }

}
