package com.mystery.funclothes.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mystery.funclothes.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Vindicated-Rt
 * 2019/5/19 2:01 AM
 */
public class ClothesAdapter extends BaseAdapter {

    private List<Integer> imageIds = new ArrayList<>();
    private Boolean flag = false;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<Integer> getImageIds() {
        return imageIds;
    }

    public void setImageId() {
        imageIds.add(R.mipmap.style_1);
        imageIds.add(R.mipmap.style_2);
        imageIds.add(R.mipmap.style_3);
        imageIds.add(R.mipmap.style_4);
        imageIds.add(R.mipmap.style_5);
        imageIds.add(R.mipmap.style_6);
        imageIds.add(R.mipmap.style_7);
        imageIds.add(R.mipmap.style_8);
    }

    public ClothesAdapter() {
        setImageId();
    }

    @Override
    public int getCount() {
        return imageIds.size();
    }

    @Override
    public Object getItem(int position) {
        return imageIds.get(position);
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
        viewHolder.chooseItemIv.setBackgroundResource(imageIds.get(position));
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
}
