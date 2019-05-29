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
    private List<ViewHolder> views = new ArrayList<>();

    public void setImageId() {
        imageIds.add(R.mipmap.scenes_1);
        imageIds.add(R.mipmap.scenes_2);
        imageIds.add(R.mipmap.scenes_3);
        imageIds.add(R.mipmap.scenes_4);
        imageIds.add(R.mipmap.scenes_5);
        imageIds.add(R.mipmap.scenes_6);
        imageIds.add(R.mipmap.scenes_7);
        imageIds.add(R.mipmap.scenes_8);
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
        ViewHolder viewHolder = new ViewHolder(convertView, position);
        viewHolder.chooseItemIv.setBackgroundResource(imageIds.get(position));
        views.add(viewHolder);
        return convertView;
    }

    public ViewHolder getViews(int postion) {
        return views.get(postion);
    }

    public static class ViewHolder {
        private int _id;
        private ImageView chooseItemIv;
        private ImageView chooseItemLikeIv;
        private ImageView chooseItemPassIv;

        public ViewHolder(View itemView,int position) {
            _id = position;
            chooseItemIv = itemView.findViewById(R.id.choose_item_iv);
            chooseItemLikeIv = itemView.findViewById(R.id.choose_item_like_iv);
            chooseItemPassIv = itemView.findViewById(R.id.choose_item_pass_iv);
        }

        public int getPosition() {
            return _id;
        }

        public ImageView getChooseItemIv() {
            return chooseItemIv;
        }

        public ImageView getChooseItemLikeIv() {
            return chooseItemLikeIv;
        }

        public ImageView getChooseItemPassIv() {
            return chooseItemPassIv;
        }
    }
}
