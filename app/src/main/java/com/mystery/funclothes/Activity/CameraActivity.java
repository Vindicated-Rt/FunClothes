package com.mystery.funclothes.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cazaea.sweetalert.SweetAlertDialog;
import com.hanks.htextview.fade.FadeTextView;
import com.hanks.htextview.typer.TyperTextView;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Bean.StyleInfo;
import com.mystery.funclothes.Presenter.CameraPresenter;
import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.BitmapFactoryUtil;
import com.mystery.funclothes.Util.HttpUtil;
import com.mystery.funclothes.View.CameraView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by Vindicated-Rt
 * 2019/5/13 8:00 PM
 * 相机页面
 * 显示当前场景的信息
 * 并链接至相机拍照（待完成）
 */

@Route(path = BaseURL.ACTIVITY_URL_CAMERA)
public class CameraActivity extends AppCompatActivity implements CameraView {

    /*跳转页面标识符*/
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    private FadeTextView cameraScenceTv;
    private TyperTextView scenceDesTv;
    private ImageView cameraScenesIv;
    private ImageView cameraRecommendIv;
    private ImageView cmaeraChooseIv;
    private ImageView cameraPictureIv;
    private CardView cameraPictureCv;
    private CardView cameraScenesCv;
    private CameraPresenter cameraPresenter;

    private String result;
    private String string;

    @Autowired
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        initView();
    }

    /*设置场景数据*/
    @Override
    public void setData(int postion) {
        cameraScenceTv.animateText(StyleInfo.getInstance().getTitle(postion));
        scenceDesTv.animateText(StyleInfo.getInstance().getDescription(postion));
        cameraScenesIv.setBackgroundResource(StyleInfo.getInstance().getImageId(postion));
    }

    @Override
    public void setBackground(Bitmap background) {
        Drawable drawable = new BitmapDrawable(getResources(), background);
        cameraPictureIv.setBackground(drawable);
    }

    @Override
    public void setVisibility(boolean flag) {
        if (flag){
            cameraPictureCv.setVisibility(View.GONE);
            cameraRecommendIv.setVisibility(View.GONE);
            cameraScenceTv.setVisibility(View.VISIBLE);
            scenceDesTv.setVisibility(View.VISIBLE);
            cameraScenesIv.setVisibility(View.VISIBLE);
            cmaeraChooseIv.setVisibility(View.VISIBLE);
            cameraScenesCv.setVisibility(View.VISIBLE);
        }else {
            cameraPictureCv.setVisibility(View.VISIBLE);
            cameraRecommendIv.setVisibility(View.VISIBLE);
            cameraScenceTv.setVisibility(View.GONE);
            scenceDesTv.setVisibility(View.GONE);
            cameraScenesIv.setVisibility(View.GONE);
            cmaeraChooseIv.setVisibility(View.GONE);
            cameraScenesCv.setVisibility(View.GONE);
        }
    }

    /*相册或相机返回图片*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    setBackground(BitmapFactoryUtil.getBitmapByUri(this, cameraPresenter.getImageUri(), 1920, 1080));
                    setVisibility(false);
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    setBackground(cameraPresenter.handleImage(data));
                    setVisibility(false);
                }
                break;
            default:
                break;
        }
    }

    /*初始化视图，设置视图数据*/
    private void initView() {
        ARouter.getInstance().inject(this);
        cameraScenceTv = findViewById(R.id.camera_scenes_tv);
        scenceDesTv = findViewById(R.id.camera_scence_Des_tv);
        cameraScenesIv = findViewById(R.id.camera_scenes_iv);
        cameraRecommendIv = findViewById(R.id.camera_recommend_iv);
        cmaeraChooseIv = findViewById(R.id.camera_choose_iv);
        cameraScenesCv = findViewById(R.id.camera_scenes_cv);
        cameraPictureCv = findViewById(R.id.camera_picture_cv);
        cameraPictureIv = findViewById(R.id.camera_picture_iv);
        cameraPresenter = new CameraPresenter(this, this);
        setData(position);
        }

    /*相机按钮点击事件*/
    public void cameraBtnClick(View view) {
        cameraPresenter.openDialog(view);
    }

    /*推荐按钮点击事件*/
    public void ClothesRecommend(View view) {
        final SweetAlertDialog loading = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        loading.setTitleText("Loading").setCancelable(false);
        loading.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap postPic = BitmapFactoryUtil.getBitmapByView(cameraPictureIv,224,224);
                JSONObject obj = new JSONObject();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                postPic.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] bytes = bos.toByteArray();
                string = Base64.encodeToString(bytes, Base64.DEFAULT);
                try {
                    obj.put("pic",string);
                    Log.i(BaseURL.TAG,"发送："+bytes);
                    result = HttpUtil.post(BaseURL.WHF_URL,obj);
                    Log.i(BaseURL.TAG,"链接成功："+result);
                    bos.close();
                } catch (Exception e) {
                    Log.i(BaseURL.TAG,"链接失败");
                    e.printStackTrace();
                }
            }
        }).start();
        new CountDownTimer(1000*12,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(result != null && !result.equals("")){
                    loading.setTitleText("Success!")
                            .setConfirmText("OK")
                            .showConfirmButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    /*ARouter.getInstance().build(BaseURL.ACTIVITY_URL_CHOOSE).navigation();
                                    loading.cancel();
                                    setVisibility(true);*/
                                    loading.cancel();
                                    setVisibility(true);
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(result);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    String pic = jsonObject.optString("pic");
                                    Log.i(BaseURL.TAG, "onClick: "+pic.length());
                                    scenceDesTv.animateText(pic);
                                    byte[] bytes = Base64.decode(pic, Base64.DEFAULT);

                                    Bitmap bitmap =BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    cameraScenesIv.setImageBitmap(bitmap);
                                }
                            })
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                }else {
                    loading.setTitleText("无法连接网络或推荐系统出错")
                            .setConfirmText("好吧")
                            .changeAlertType(SweetAlertDialog.WARNING_TYPE);
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
