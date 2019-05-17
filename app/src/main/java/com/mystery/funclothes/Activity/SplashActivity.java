package com.mystery.funclothes.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by Vindicated-Rt
 * 2019/4/26 7:30 PM
 * 启动页面
 * 显示图标
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle= ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this).toBundle();
                startActivity(new Intent(SplashActivity.this,ScenesActivity.class),bundle);
                finish();
            }
        },1618);
    }
}
