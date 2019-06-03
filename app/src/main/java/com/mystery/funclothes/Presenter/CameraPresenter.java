package com.mystery.funclothes.Presenter;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.mystery.funclothes.Activity.CameraActivity;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.DesignView.CameraDialog;
import com.mystery.funclothes.Model.CameraModel;
import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.BitmapFactoryUtil;


/**
 * Created by Vindicated-Rt
 * 2019/5/13 9:10 PM
 * 相机页面功能表现层
 */

public class CameraPresenter implements CameraModel {

    private Context mContext;
    private CameraActivity cameraActivity;
    private Uri imageUri;
    private CameraDialog cameraDialog;


    /*构造方法*/
    public CameraPresenter(Context mContext, CameraActivity cameraActivity) {
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
        return BitmapFactoryUtil.getBitmapByFileDescriptor(imagePath, 1920, 1080);
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

    /*打开选择图片来源Dialog*/
    public void openDialog(View v) {
        ActivityCompat.requestPermissions(cameraActivity,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        cameraDialog = new CameraDialog(mContext, cameraActivity, 0);
        cameraDialog.getWindow().setWindowAnimations(R.style.CameraDialogStytle);
        cameraDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        cameraDialog.show();
    }

    /*获取图片地址*/
    public Uri getImageUri() {
        return cameraDialog.getImageUri();
    }
}
