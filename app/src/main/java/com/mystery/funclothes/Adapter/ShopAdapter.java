package com.mystery.funclothes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Bean.ShopInfo;
import com.mystery.funclothes.R;


/**
 * Created by Vindicated-Rt
 * 2019/7/8 10:35 PM
 * 购物车 适配器
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private Context mContext;

    public ShopAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_cardview, viewGroup, false);
        return new ShopAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Bitmap bitmap = ShopInfo.getInstance().getImageIds().get(i);
        viewHolder.shopImage.setImageBitmap(bitmap);
        viewHolder.toShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkApkExist(mContext, BaseURL.TAOBAO_PACKAGE)){
                    MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, "title", "description");
                    Intent i = mContext.getPackageManager().getLaunchIntentForPackage(BaseURL.TAOBAO_PACKAGE);
                    mContext.startActivity(i);
                } else {
                    Uri uri = Uri.parse("market://details?id="+BaseURL.TAOBAO_PACKAGE);//id为包名
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(it);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ShopInfo.getInstance().getSize();
    }

    /*ItemView -- CardView*/
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView shopImage;
        public ImageView toShop;

        public ViewHolder(View itemView) {
            super(itemView);
            shopImage = itemView.findViewById(R.id.shop_iv);
            toShop = itemView.findViewById(R.id.toShop_iv);
        }
    }

    public static boolean checkApkExist(Context context, String packageName){
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
