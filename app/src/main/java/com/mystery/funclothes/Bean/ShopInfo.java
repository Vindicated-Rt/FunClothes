package com.mystery.funclothes.Bean;

import android.content.Context;
import android.graphics.Bitmap;

import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.BitmapFactoryUtil;

import java.util.ArrayList;

/**
 * Created by Vindicated-Rt
 * 2019/7/8 10:33 PM
 */
public class ShopInfo {
    private static ShopInfo instance = new ShopInfo();
    private ArrayList<Bitmap> imageIds = new ArrayList<>();

    public ArrayList<Bitmap> getImageIds() {
        return imageIds;
    }

    /*构造方法*/
    public ShopInfo() {
    }

    /*单例模式唯一对象*/
    public static ShopInfo getInstance() {
        return instance;
    }

    public int getSize() {
        return imageIds.size();
    }

    public void addInfo(Bitmap shopImage){
        imageIds.add(shopImage);
    }
}
