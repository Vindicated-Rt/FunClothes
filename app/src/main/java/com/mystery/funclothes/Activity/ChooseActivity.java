package com.mystery.funclothes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mystery.funclothes.Adapter.ClothesAdapter;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Bean.ClothesInfo;
import com.mystery.funclothes.Presenter.ChoosePresenter;
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

    @Autowired
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);
        init();
    }

    /*初始化视图，设置视图数据*/
    private void init() {
        ARouter.getInstance().inject(this);
        chooseSp = findViewById(R.id.choose_sp);
        clothesAdapter = new ClothesAdapter();
        ChoosePresenter choosePresenter = new ChoosePresenter(this, chooseSp, clothesAdapter, content);
        chooseSp.setAdapter(clothesAdapter);
        chooseSp.setFlingListener(choosePresenter.getFlingListener());
    }

    /*喜欢按钮监听事件*/
    public void likeBtn(View view) {
        Log.i(BaseURL.TAG, ClothesInfo.getInstance().getSize() + "");
        if (ClothesInfo.getInstance().getSize() != 0) {
            chooseSp.getTopCardListener().selectLeft();
        }
    }

    /*不喜欢按钮监听事件*/
    public void dislikeBtn(View view) {
        Log.i(BaseURL.TAG, ClothesInfo.getInstance().getSize() + "");
        if (ClothesInfo.getInstance().getSize() != 0) {
            chooseSp.getTopCardListener().selectRight();
        }
    }

    /*打开购物车按钮监听事件*/
    public void goShopping(View view) {
        ARouter.getInstance().build(BaseURL.ACTIVITY_URL_SHOP).navigation();
    }
}
