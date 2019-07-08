package com.mystery.funclothes.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mystery.funclothes.Adapter.ClothesAdapter;
import com.mystery.funclothes.Bean.ScenesInfo;
import com.mystery.funclothes.Bean.ShopInfo;
import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.BitmapFactoryUtil;

/**
 * Created by Vindicated-Rt
 * 2019/6/8 1:35 PM
 * 衣服选择页面功能表现层
 */
public class ChoosePresenter {

    private Context mContext;
    private SwipeFlingAdapterView.onFlingListener flingListener;
    private SwipeFlingAdapterView mChooseSp;

    public ChoosePresenter(Context context,SwipeFlingAdapterView chooseSp,final ClothesAdapter clothesAdapter){
        this.mContext = context;
        this.mChooseSp = chooseSp;
        setFlingListener(clothesAdapter);
    }

    public SwipeFlingAdapterView.onFlingListener getFlingListener() {
        return flingListener;
    }

    public void setFlingListener(final ClothesAdapter clothesAdapter) {
        flingListener = new SwipeFlingAdapterView.onFlingListener() {
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
                //Bitmap bitmap = (Bitmap) o;
                Bitmap bitmap = BitmapFactoryUtil.getBitmapByResource(mContext.getResources(),(int) o,1024,1024);
                ShopInfo.getInstance().addInfo(bitmap);
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                clothesAdapter.setImageId();
                clothesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float v) {
                View view = mChooseSp.getSelectedView();
                if (view != null){
                    view.findViewById(R.id.choose_item_pass_iv).setAlpha(v < 0 ? -v : 0);
                    view.findViewById(R.id.choose_item_like_iv).setAlpha(v > 0 ? v : 0);
                }else {
                    mChooseSp.getTopCardListener().leftBorder();
                    clothesAdapter.notifyDataSetChanged();
                }
            }
        };
    }
}
