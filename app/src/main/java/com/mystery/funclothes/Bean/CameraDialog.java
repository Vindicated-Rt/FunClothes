package com.mystery.funclothes.Bean;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mystery.funclothes.R;

/**
 * Created by Vindicated-Rt
 * 2019/5/22 10:35 PM
 */
public class CameraDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private ImageView cancelIv;

    public CameraDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.camera_dailog);
        cancelIv = findViewById(R.id.camera_dailog_cancel_iv);
        cancelIv.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera_dailog_cancel_iv:
                CameraDialog.this.dismiss();
                break;
        }
    }
}
