package com.mystery.funclothes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fashare.stack_layout.StackLayout;
import com.fashare.stack_layout.transformer.AlphaTransformer;
import com.fashare.stack_layout.transformer.AngleTransformer;
import com.fashare.stack_layout.transformer.StackPageTransformer;
import com.mystery.funclothes.Adapter.ClothesAdapter;
import com.mystery.funclothes.Adapter.ScenesAdapter;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Bean.ScenesInfo;
import com.mystery.funclothes.R;

import static android.widget.Toast.LENGTH_SHORT;

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

        StackLayout mStackLayout = findViewById(R.id.stack_layout);
        ClothesAdapter scenesAdapter = new ClothesAdapter(ScenesInfo.getInstance());
        mStackLayout.setAdapter(scenesAdapter);
        mStackLayout.addPageTransformer(
                new StackPageTransformer(),     // 堆叠
                new AlphaTransformer(),         // 渐变
                new AngleTransformer()          // 角度
        );
        mStackLayout.setOnSwipeListener(new StackLayout.OnSwipeListener() {
            @Override
            public void onSwiped(View swipedView, int swipedItemPos, boolean isSwipeLeft, int itemLeft) {
                String string = isSwipeLeft? "往左": "往右" + "移除" + ScenesInfo.getInstance().getTitle(swipedItemPos) + "." + "剩余" + itemLeft + "项";
                Toast.makeText(ChooseActivity.this,string, LENGTH_SHORT).show();
            }
        });
    }
}
