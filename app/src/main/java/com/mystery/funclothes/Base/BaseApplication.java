package com.mystery.funclothes.Base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import static me.jessyan.autosize.utils.LogUtils.isDebug;

/**
 * Created by Vindicated-Rt
 * 2019/5/17 9:05 PM
 * 初始化 ARouter
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(BaseApplication.this);
    }
}
