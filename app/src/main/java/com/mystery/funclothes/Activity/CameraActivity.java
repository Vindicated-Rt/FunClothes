package com.mystery.funclothes.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hanks.htextview.fade.FadeTextView;
import com.hanks.htextview.typer.TyperTextView;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Bean.ScenesInfo;
import com.mystery.funclothes.Presenter.CameraPresenter;
import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.BitmapFactoryUtil;
import com.mystery.funclothes.View.CameraView;

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
    private CameraPresenter cameraPresenter;
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
    public void setData(ScenesInfo scenesInfo, int postion) {
        cameraScenceTv.animateText(scenesInfo.getTitle(postion));
        scenceDesTv.animateText(scenesInfo.getDescription(postion));
        cameraScenesIv.setBackgroundResource(scenesInfo.getImageId(postion));
    }

    @Override
    public void setBackground(Bitmap background) {
        Drawable drawable = new BitmapDrawable(getResources(), background);
        cameraScenesIv.setBackground(drawable);
    }

    @Override
    public void setVisibility(Boolean flag) {
        if (flag) {
            cmaeraChooseIv.setVisibility(View.GONE);
            cameraRecommendIv.setVisibility(View.VISIBLE);
        } else {
            cmaeraChooseIv.setVisibility(View.VISIBLE);
            cameraRecommendIv.setVisibility(View.GONE);
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
                    setVisibility(true);
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    setBackground(cameraPresenter.handleImage(data));
                    setVisibility(true);
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
        cameraPresenter = new CameraPresenter(this, this);
        setData(ScenesInfo.getInstance(), position);
    }

    /*相机按钮点击事件*/
    public void cameraBtnClick(View view) {
        cameraPresenter.openDialog(view);
    }

    /*推荐按钮点击事件*/
    public void ClothesRecommend(View view) {
        ARouter.getInstance().build(BaseURL.ACTIVITY_URL_CHOOSE).navigation();
    }
}
