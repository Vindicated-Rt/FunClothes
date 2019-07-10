package com.mystery.funclothes.Bean;

import android.graphics.Bitmap;

import com.mystery.funclothes.Util.BitmapFactoryUtil;

import java.util.ArrayList;

/**
 * Created by Vindicated-Rt
 * 2019/7/10 4:46 PM
 * 推荐衣服
 */
public class ClothesInfo {
    private static ClothesInfo instance = new ClothesInfo();
    private ArrayList<Bitmap> clothes = new ArrayList<>();

    /*构造方法*/
    public ClothesInfo() {
    }

    /*单例模式唯一对象*/
    public static ClothesInfo getInstance() {
        return instance;
    }

    public int getSize() {
        return clothes.size();
    }

    public ArrayList<Bitmap> getClothes() {
        return clothes;
    }

    public void addInfo(Bitmap clotheImage){
        clothes.add(clotheImage);
    }

    public void clearImage(){
        clothes.clear();
    }


}
