package com.mystery.funclothes.Presenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cazaea.sweetalert.SweetAlertDialog;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mystery.funclothes.Adapter.ClothesAdapter;
import com.mystery.funclothes.Base.BaseURL;
import com.mystery.funclothes.Bean.ClothesInfo;
import com.mystery.funclothes.Bean.ShopInfo;
import com.mystery.funclothes.R;
import com.mystery.funclothes.Util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vindicated-Rt
 * 2019/6/8 1:35 PM
 * 衣服选择页面功能表现层
 */
public class ChoosePresenter {

    private Context mContext;
    private SwipeFlingAdapterView.onFlingListener flingListener;
    private SwipeFlingAdapterView mChooseSp;
    private String content;
    private String result;
    private boolean flag = false;

    /*构造方法*/
    public ChoosePresenter(Context context, SwipeFlingAdapterView chooseSp, final ClothesAdapter clothesAdapter, String content) {
        this.mContext = context;
        this.mChooseSp = chooseSp;
        this.content = content;
        setFlingListener(clothesAdapter);
    }

    /*返回监听事件实例*/
    public SwipeFlingAdapterView.onFlingListener getFlingListener() {
        return flingListener;
    }

    /*滑动监听事件*/
    public void setFlingListener(final ClothesAdapter clothesAdapter) {
        flingListener = new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                clothesAdapter.getBitmaps().remove(0);
                clothesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {
                Bitmap bitmap = (Bitmap) o;
                ShopInfo.getInstance().addInfo(bitmap);
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                if (flag) {
                    refresh();
                } else {
                    flag = true;
                }
            }

            @Override
            public void onScroll(float v) {
                View view = mChooseSp.getSelectedView();
                if (view != null) {
                    view.findViewById(R.id.choose_item_pass_iv).setAlpha(v < 0 ? -v : 0);
                    view.findViewById(R.id.choose_item_like_iv).setAlpha(v > 0 ? v : 0);
                } else {
                    mChooseSp.getTopCardListener().leftBorder();
                    clothesAdapter.notifyDataSetChanged();
                }
            }
        };
    }

    /*重新发送请求方法*/
    public void refresh() {
        final SweetAlertDialog loading = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        loading.setTitleText("Loading").setCancelable(false);
        loading.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = HttpUtil.post(BaseURL.WHF_URL, content);
                } catch (Exception e) {
                    Log.i(BaseURL.TAG, "链接失败");
                    e.printStackTrace();
                }
            }
        }).start();
        new CountDownTimer(1000 * 15, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (result != null && !result.equals("")) {
                    loading.setTitleText("Success!")
                            .setConfirmText("OK")
                            .showConfirmButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    try {
                                        ClothesInfo.getInstance().clearImage();
                                        JSONObject jsonObject = new JSONObject(result);
                                        for (int i = 0; i < 8; i++) {
                                            String pic = jsonObject.optString("pic_" + i);
                                            byte[] resultBytes = Base64.decode(pic, Base64.DEFAULT);
                                            Bitmap bitmap = BitmapFactory.decodeByteArray(resultBytes, 0, resultBytes.length);
                                            ClothesInfo.getInstance().addInfo(bitmap);
                                        }
                                        ARouter.getInstance()
                                                .build(BaseURL.ACTIVITY_URL_CHOOSE)
                                                .withString("content", content)
                                                .navigation();
                                        ((Activity) mContext).finish();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    loading.cancel();
                                }
                            })
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                } else {
                    loading.setTitleText("无法连接网络或推荐系统出错")
                            .setConfirmText("好吧")
                            .changeAlertType(SweetAlertDialog.WARNING_TYPE);
                }
            }
        }.start();
    }
}
