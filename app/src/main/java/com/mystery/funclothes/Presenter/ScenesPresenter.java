package com.mystery.funclothes.Presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.mystery.funclothes.Adapter.ScenesAdapter;
import com.mystery.funclothes.Bean.ScenesInfo;
import com.mystery.funclothes.Model.ScenesModel;
import com.mystery.funclothes.Util.ItemDecorationUtil;

/**
 * Created by Vindicated-Rt
 * 2019/4/26 7:30 PM
 * 场景页面功能表现层
 */

public class ScenesPresenter implements ScenesModel {
    private Context mContext;
    private RecyclerView mRecyclerView;

    /*构造方法*/
    public ScenesPresenter(Context context, RecyclerView recyclerView) {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
    }

    /*初始化 recyclerView 数据*/
    @Override
    public void initDatas() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new ScenesAdapter(ScenesInfo.getInstance()));
        mRecyclerView.addItemDecoration(new ItemDecorationUtil(20));
    }
}
