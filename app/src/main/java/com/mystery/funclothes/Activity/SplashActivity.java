package com.mystery.funclothes.Activity;

import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mystery.funclothes.Base.BaseURL;

/**
 * Created by Vindicated-Rt
 * 2019/4/26 7:30 PM
 * 启动页面
 * 显示图标
 */
@Route(path = BaseURL.ACTIVITY_URL_SPLASH)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(BaseURL.ACTIVITY_URL_CHOOSE)
                        .withOptionsCompat(ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this))
                        .navigation(SplashActivity.this);
                finish();
            }
        }, 10);
    }
}
