package com.mystery.funclothes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mystery.funclothes.Presenter.ScenesPresenter;
import com.mystery.funclothes.R;

/**
 * Created by Vindicated-Rt
 * 2019/4/26 7:40 PM
 * 选择场景页面
 * 显示各种场景
 */
@Route(path = "/activities/scenes")
public class ScenesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenes_layout);
        initView();
    }

    private void initView(){
        RecyclerView scenes_rl = findViewById(R.id.scenes_rl);
        ScenesPresenter scenesPresenter = new ScenesPresenter(this, scenes_rl);
        scenesPresenter.initDatas();
    }
}
