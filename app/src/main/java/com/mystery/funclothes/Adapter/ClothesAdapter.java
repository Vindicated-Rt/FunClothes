package com.mystery.funclothes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fashare.stack_layout.StackLayout;
import com.mystery.funclothes.Bean.ScenesInfo;
import com.mystery.funclothes.R;

/**
 * Created by Vindicated-Rt
 * 2019/5/19 2:01 AM
 */
public class ClothesAdapter extends StackLayout.Adapter<ClothesAdapter.ViewHolder> {

    private ScenesInfo scenesInfo;//场景数据对象

    public ClothesAdapter(ScenesInfo scenesInfo) {
        this.scenesInfo = scenesInfo;
    }

    @Override
    public ClothesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_cardview, parent, false);
        return new ClothesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.scenesImage.setBackgroundResource(scenesInfo.getImageId(position));
    }

    @Override
    public int getItemCount() {
        return scenesInfo.getSize();
    }

    /*ItemView -- CardView*/
    public static class ViewHolder extends StackLayout.ViewHolder {
        public ImageView scenesImage;

        public ViewHolder(View itemView) {
            super(itemView);
            scenesImage = itemView.findViewById(R.id.choose_item_iv);
        }
    }
}
