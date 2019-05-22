package com.mystery.funclothes.Presenter;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.mystery.funclothes.Activity.CameraActivity;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Bean.CameraDialog;
import com.mystery.funclothes.Model.CameraModel;
import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.BitmapFactoryUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vindicated-Rt
 * 2019/5/13 9:10 PM
 * 相机页面功能表现层
 */

public class CameraPresenter implements CameraModel{

    private Context mContext;
    private CameraActivity cameraActivity;
    private Uri imageUri;

    /*跳转页面标识符*/
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    /*构造方法*/
    public CameraPresenter(Context mContext,CameraActivity cameraActivity) {
        this.mContext = mContext;
        this.cameraActivity = cameraActivity;
    }

    /*从图库获取图片路径转换为位图*/
    @Override
    public Bitmap handleImage(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(mContext, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if (BaseURL.DOCUMENTS_URL.equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if (BaseURL.DOWNLOADS_URL.equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse(BaseURL.CONTENT_DOWNLOADS_URL),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        }
        return BitmapFactoryUtil.getBitmapByFileDescriptor(imagePath,1920,1080);
    }

    /*获取图片路径*/
    @Override
    public String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = mContext.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /*获取当前时间*/
    public String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss", Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 代码冗长，dialog也不能完全体现出效果，待重写DIY的Dialog
     */
    /*选择图片来源Dialog*/
    public void openDialog(View v) {
        CameraDialog cameraDialog = new CameraDialog(mContext,0);
        cameraDialog.getWindow().setWindowAnimations(R.style.CameraDialogStytle);
        cameraDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        cameraDialog.show();
        /*ActivityCompat.requestPermissions((Activity) mContext,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        new SweetAlertDialog(mContext, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("CHOOSE")
                .setContentText("开始选择衣服")
                .setCustomImage(R.mipmap.camera)
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                        new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("FROM WHERE!")
                                .setConfirmText("相机")
                                .setCancelText("相册")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Toast.makeText(mContext, "相机", Toast.LENGTH_SHORT).show();
                                        sweetAlertDialog.cancel();
                                        openCamera();
                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Toast.makeText(mContext, "相册", Toast.LENGTH_SHORT).show();
                                        sweetAlertDialog.cancel();
                                        openAlbum();
                                    }
                                }).show();
                    }
                }).show();*/
    }

    /*打开相机*/
    private void openCamera() {
        File storeImage = new File(mContext.getExternalCacheDir(), getTime() + ".jpg");
        try {
            storeImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(mContext, BaseURL.PACKAGE_FILEPROVIDER, storeImage);
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

    public Uri getImageUri() {
        return imageUri;
    }
}
