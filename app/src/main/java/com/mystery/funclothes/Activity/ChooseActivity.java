package com.mystery.funclothes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mystery.funclothes.Adapter.ClothesAdapter;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.R;


/**
 * Created by Vindicated-Rt
 * 2019/5/18 10:52 PM
 * 选择推荐衣服
 */

@Route(path = BaseURL.ACTIVITY_URL_CHOOSE)
public class ChooseActivity extends AppCompatActivity {
    private SwipeFlingAdapterView chooseSp;
    private ClothesAdapter clothesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);
        chooseSp = findViewById(R.id.choose_sp);
        clothesAdapter = new ClothesAdapter();
        chooseSp.setAdapter(clothesAdapter);
        chooseSp.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                clothesAdapter.getImageIds().remove(0);
                clothesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                clothesAdapter.setImageId();
                clothesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float v) {
                View view = chooseSp.getSelectedView();
                view.findViewById(R.id.choose_item_pass_iv).setAlpha(v < 0 ? -v : 0);
                view.findViewById(R.id.choose_item_like_iv).setAlpha(v > 0 ? v : 0);
            }
        });
    }

    public void left(View view) {
        chooseSp.getTopCardListener().selectLeft();
    }

    public void right(View view) {
        chooseSp.getTopCardListener().selectRight();
    }
}
