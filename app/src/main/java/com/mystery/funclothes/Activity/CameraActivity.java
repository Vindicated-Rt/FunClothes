package com.mystery.funclothes.Activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.hanks.htextview.fade.FadeTextView;
import com.hanks.htextview.typer.TyperTextView;
import com.mystery.funclothes.Bean.ScenesInfo;
import com.mystery.funclothes.Presenter.CameraPresenter;
import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.BitmapFactoryUtil;
import com.mystery.funclothes.View.CameraView;

import java.io.File;
import java.io.IOException;

/**
 * Created by Vindicated-Rt
 * 2019/5/13 8:00 PM
 * 相机页面
 * 显示当前场景的信息
 * 并链接至相机拍照（待完成）
 */

public class CameraActivity extends AppCompatActivity implements CameraView {

    /*跳转页面标识符*/
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    private FadeTextView cameraScenceTv;
    private TyperTextView scenceDesTv;
    private ImageView cameraScenesIv;
    private CameraPresenter cameraPresenter;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        initView();
    }

    /*初始化视图，设置视图数据*/
    private void initView() {
        ScenesInfo scenesInfo = ScenesInfo.getInstance();
        cameraScenceTv = findViewById(R.id.camera_scenes_tv);
        scenceDesTv = findViewById(R.id.camera_scence_Des_tv);
        cameraScenesIv = findViewById(R.id.camera_scenes_iv);
        cameraPresenter = new CameraPresenter(this);
        int postion = getIntent().getIntExtra("postion", 0);
        setData(scenesInfo, postion);
    }

    /*相机按钮点击时间*/
    public void cameraBtnClick(View view) {
        openDialog(view);
    }

    /*设置场景数据*/
    @Override
    public void setData(ScenesInfo scenesInfo, int postion) {
        cameraScenceTv.animateText(scenesInfo.getTitle(postion));
        scenceDesTv.animateText(scenesInfo.getDescription(postion));
        cameraScenesIv.setBackgroundResource(scenesInfo.getImageId(postion));
    }

    /**
     * 代码冗长，dialog也不能完全体现出效果，待重写DIY的Dialog
     */
    /*选择图片来源Dialog*/
    @Override
    public void openDialog(View v) {
        ActivityCompat.requestPermissions(CameraActivity.this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("CHOOSE")
                .setContentText("开始选择衣服")
                .setCustomImage(R.mipmap.camera)
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                        new SweetAlertDialog(CameraActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("FROM WHERE!")
                                .setConfirmText("相机")
                                .setCancelText("相册")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Toast.makeText(CameraActivity.this, "相机", Toast.LENGTH_SHORT).show();
                                        sweetAlertDialog.cancel();
                                        openCamera();
                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Toast.makeText(CameraActivity.this, "相册", Toast.LENGTH_SHORT).show();
                                        sweetAlertDialog.cancel();
                                        openAlbum();
                                    }
                                }).show();
                    }
                }).show();
    }

    @Override
    public void setBackground() {

    }

    /*相册或相机返回图片*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = BitmapFactoryUtil.getBitmapByUri(this,imageUri,1920,1080);
                    Drawable drawable = new BitmapDrawable(getResources(),bitmap);
                    cameraScenesIv.setBackground(drawable);
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = cameraPresenter.handleImage(data);
                    Drawable drawable = new BitmapDrawable(getResources(),bitmap);
                    cameraScenesIv.setBackground(drawable);
                }
                break;
            default:
                break;
        }
    }

    /*打开相册*/
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    /*打开相机*/
    private void openCamera() {
        File storeImage = new File(getExternalCacheDir(), cameraPresenter.getTime() + ".jpg");
        try {
            storeImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(CameraActivity.this, "com.mystery.funclothes.fileprovider", storeImage);
        } else {
            imageUri = Uri.fromFile(storeImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }
}
