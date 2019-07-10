package com.mystery.funclothes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Presenter.ScenesPresenter;
import com.mystery.funclothes.R;

/**
 * Created by Vindicated-Rt
 * 2019/4/26 7:40 PM
 * 选择场景页面
 * 显示各种场景
 */
@Route(path = BaseURL.ACTIVITY_URL_STYLE)
public class StyleActivity extends AppCompatActivity {


    private TextView VindicatedTv;
    private TextView BobHongYuChenTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenes_layout);
        initView();
        setHtml();
    }

    private void initView() {
        VindicatedTv = findViewById(R.id.Vindicated_tv);
        BobHongYuChenTv = findViewById(R.id.BobHongYuChen_tv);
        RecyclerView scenes_rv = findViewById(R.id.scenes_rl);
        ScenesPresenter scenesPresenter = new ScenesPresenter(this, scenes_rv);
        scenesPresenter.initDatas();
    }

    private void setHtml(){
        VindicatedTv.setMovementMethod(LinkMovementMethod.getInstance());
        VindicatedTv.setText(Html.fromHtml(BaseURL.VINDICATEDRT_URL));
        BobHongYuChenTv.setMovementMethod(LinkMovementMethod.getInstance());
        BobHongYuChenTv.setText(Html.fromHtml(BaseURL.BOBHONGYUCHEN_URL));
    }
}
