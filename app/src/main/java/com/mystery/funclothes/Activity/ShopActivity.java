package com.mystery.funclothes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mystery.funclothes.Adapter.ShopAdapter;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.ItemDecorationUtil;

/**
 * Created by Vindicated-Rt
 * 2019/7/5 8:30 PM
 * 购物车界面
 */

@Route(path = BaseURL.ACTIVITY_URL_SHOP)
public class ShopActivity extends AppCompatActivity {

    private RecyclerView shopRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        initView();
    }

    /*初始化视图*/
    private void initView(){
        shopRv = findViewById(R.id.shop_rv);
        shopRv.setLayoutManager(new LinearLayoutManager(this));
        shopRv.setAdapter(new ShopAdapter(this));
        shopRv.addItemDecoration(new ItemDecorationUtil(20));
    }
}
