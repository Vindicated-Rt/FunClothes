package com.mystery.funclothes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mystery.funclothes.Adapter.ClothesAdapter;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.R;

import link.fls.swipestack.SwipeStack;


/**
 * Created by Vindicated-Rt
 * 2019/5/18 10:52 PM
 * 选择推荐衣服
 */

@Route(path = BaseURL.ACTIVITY_URL_CHOOSE)
public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);
        SwipeStack swipeStack = findViewById(R.id.swipeStack);
        final ClothesAdapter clothesAdapter = new ClothesAdapter();
        swipeStack.setAdapter(clothesAdapter);
        swipeStack.setSwipeProgressListener(new SwipeStack.SwipeProgressListener() {
            @Override
            public void onSwipeStart(int position) {

                }

            @Override
            public void onSwipeProgress(int position, float progress) {
                if (progress>0){
                    clothesAdapter.getViews(position).getChooseItemLikeIv().setAlpha(progress);
                    clothesAdapter.getViews(position).getChooseItemLikeIv().setVisibility(View.VISIBLE);
                    clothesAdapter.getViews(position).getChooseItemPassIv().setVisibility(View.GONE);
                }else {
                    clothesAdapter.getViews(position).getChooseItemPassIv().setAlpha((0-progress));
                    clothesAdapter.getViews(position).getChooseItemPassIv().setVisibility(View.VISIBLE);
                    clothesAdapter.getViews(position).getChooseItemLikeIv().setVisibility(View.GONE);
                }

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });
    }
}
