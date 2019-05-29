package com.mystery.funclothes.Bean;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;

import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vindicated-Rt
 * 2019/5/22 10:35 PM
 * 选择相机或相册的Dialog
 */
public class CameraDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private Uri imageUri;
    private Activity cameraActivity;


    /*跳转页面标识符*/
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    /*构造方法*/
    public CameraDialog(Context context, Activity cameraActivity, int themeResId) {
        super(context, themeResId);
        this.context = context;
        this.cameraActivity = cameraActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.camera_dailog);
        findViewById(R.id.camera_dialog_cancel_iv).setOnClickListener(this);
        findViewById(R.id.camera_dialog_camera_iv).setOnClickListener(this);
        findViewById(R.id.camera_dialog_album_iv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera_dialog_cancel_iv:
                CameraDialog.this.cancel();
                break;
            case R.id.camera_dialog_camera_iv:
                openCamera();
                CameraDialog.this.cancel();
                break;
            case R.id.camera_dialog_album_iv:
                openAlbum();
                CameraDialog.this.cancel();
                break;
        }
    }

    /*打开相机*/
    private void openCamera() {
        File storeImage = new File(context.getExternalCacheDir(), getTime() + ".jpg");
        try {
            storeImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(context, BaseURL.PACKAGE_FILEPROVIDER, storeImage);
        } else {
            imageUri = Uri.fromFile(storeImage);
        }
        Intent intent = new Intent(BaseURL.INTENT_URL_IMAGECAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraActivity.startActivityForResult(intent, TAKE_PHOTO);
    }

    /*打开相册*/
    private void openAlbum() {
        Intent intent = new Intent(BaseURL.INTENT_URL_GETCONTENT);
        intent.setType("image/*");
        cameraActivity.startActivityForResult(intent, CHOOSE_PHOTO);
    }

    /*返回图片地址*/
    public Uri getImageUri() {
        return imageUri;
    }

    /*获取当前时间*/
    public String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss", Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
