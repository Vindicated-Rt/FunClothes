package com.mystery.funclothes.View;

import android.view.View;

import com.mystery.funclothes.Bean.ScenesInfo;

public interface CameraView {
    void setData(ScenesInfo scenesInfo,int postion);
    void openDialog(View view);
    void setBackground();
}
