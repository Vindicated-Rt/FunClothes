package com.mystery.funclothes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mystery.funclothes.Adapter.ClothesAdapter;
import com.mystery.funclothes.Base.BaseURL;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);
        init();
    }

    /*初始化视图，设置视图数据*/
    private void init(){
        chooseSp = findViewById(R.id.choose_sp);
        clothesAdapter = new ClothesAdapter();
        ChoosePresenter choosePresenter = new ChoosePresenter(this,chooseSp,clothesAdapter);
        chooseSp.setAdapter(clothesAdapter);
        chooseSp.setFlingListener(choosePresenter.getFlingListener());
    }

    public void likeBtn(View view) {
        chooseSp.getTopCardListener().selectLeft();
    }

    public void dislikeBtn(View view) {
        chooseSp.getTopCardListener().selectRight();
    }

    public void goShopping(View view) {
        ARouter.getInstance().build(BaseURL.ACTIVITY_URL_SHOP).navigation();
    }
}
