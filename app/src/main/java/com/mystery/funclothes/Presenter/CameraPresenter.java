package com.mystery.funclothes.Presenter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.mystery.funclothes.Activity.CameraActivity;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Model.CameraModel;
import com.mystery.funclothes.Util.BitmapFactoryUtil;

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

    /*构造方法*/
    public CameraPresenter(Context mContext) {
        this.mContext = mContext;
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
}
