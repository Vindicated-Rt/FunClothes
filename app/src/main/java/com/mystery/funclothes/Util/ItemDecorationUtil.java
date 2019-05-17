package com.mystery.funclothes.Util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Vindicated-Rt
 * 2019/4/26 9:30 PM
 * 重写 RecyclerView 的ItemDecoration
 * 控制 item 之间的间隔
 */

public class ItemDecorationUtil extends RecyclerView.ItemDecoration {

    private int space;

    public ItemDecorationUtil(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect,View view,RecyclerView parent,RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0){
            outRect.top = space;    //最上方item不设置间距
        }
        outRect.left = space;
        outRect.bottom = space;
        outRect.right = space;
    }
}
