package com.mystery.funclothes.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mystery.funclothes.Bean.ClothesInfo;
import com.mystery.funclothes.R;

import java.util.ArrayList;


/**
 * Created by Vindicated-Rt
 * 2019/5/19 2:01 AM
 * 推荐衣服视图 适配器
 */
public class ClothesAdapter extends BaseAdapter {

    private ArrayList<Bitmap> bitmaps;

    public ClothesAdapter() {
        bitmaps = ClothesInfo.getInstance().getClothes();
    }

    @Override
    public int getCount() {
        return  bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return  bitmaps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choose_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.chooseItemIv.setImageBitmap(bitmaps.get(position));
        return convertView;
    }

    public static class ViewHolder {
        private ImageView chooseItemIv;
        private ImageView chooseItemLikeIv;
        private ImageView chooseItemPassIv;

        public ViewHolder(View itemView) {
            chooseItemIv = itemView.findViewById(R.id.choose_item_iv);
            chooseItemLikeIv = itemView.findViewById(R.id.choose_item_like_iv);
            chooseItemPassIv = itemView.findViewById(R.id.choose_item_pass_iv);
        }
    }

    public ArrayList<Bitmap> getBitmaps() {
        return bitmaps;
    }
}
