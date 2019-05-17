package com.mystery.funclothes.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mystery.funclothes.Activity.CameraActivity;
import com.mystery.funclothes.Bean.ScenesInfo;
import com.mystery.funclothes.R;


/**
 * Created by Vindicated-Rt
 * 2019/4/22 8:10 PM
 * 场景 RecyclerView 适配器
 */

public class ScenesAdapter extends RecyclerView.Adapter<ScenesAdapter.ViewHolder> {

    private ScenesInfo scenesInfo;//场景数据对象

    public ScenesAdapter (ScenesInfo scenesInfo){
        this.scenesInfo = scenesInfo;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scenes_cardview, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        viewHolder.scenesImage.setBackgroundResource(scenesInfo.getImageId(i));
        viewHolder.scenesDescription.setText(scenesInfo.getTitle(i));
        final int postion = i;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCamera = new Intent(v.getContext(), CameraActivity.class);
                Bundle bundle= ActivityOptions.makeSceneTransitionAnimation((Activity) v.getContext(), v, "sharedView").toBundle();
                startCamera.putExtra("postion", postion);
                v.getContext().startActivity(startCamera,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return scenesInfo.getSize();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView scenesImage;
        public TextView scenesDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            scenesImage = itemView.findViewById(R.id.scence_pic);
            scenesDescription = itemView.findViewById(R.id.scence_des);
        }
    }
}
