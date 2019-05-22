package com.mystery.funclothes.View;

import android.graphics.Bitmap;

import com.mystery.funclothes.Bean.ScenesInfo;

public interface CameraView {
    void setData(ScenesInfo scenesInfo,int postion);
    void setBackground(Bitmap background);
    void setVisibility(Boolean flag);
}
