package com.mystery.funclothes.Adapter;

import android.app.Activity;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Bean.StyleInfo;
import com.mystery.funclothes.R;


/**
 * Created by Vindicated-Rt
 * 2019/4/22 8:10 PM
 * 场景 RecyclerView 适配器
 */

public class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.ViewHolder> {

    private StyleInfo styleInfo;//场景数据对象

    public StyleAdapter(StyleInfo styleInfo) {
        this.styleInfo = styleInfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scenes_cardview, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.scenesImage.setBackgroundResource(styleInfo.getImageId(i));
        viewHolder.scenesDescription.setText(styleInfo.getTitle(i));
        final int position = i;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(BaseURL.ACTIVITY_URL_CAMERA)
                        .withOptionsCompat(ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(), v, "sharedView"))
                        .withInt("position", position).navigation(v.getContext());
            }
        });

    }

    @Override
    public int getItemCount() {
        return styleInfo.getSize();
    }

    /*ItemView -- CardView*/
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
