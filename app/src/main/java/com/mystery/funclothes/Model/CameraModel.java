package com.mystery.funclothes.Model;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

public interface CameraModel {
    Bitmap handleImage(Intent data);
    String getImagePath(Uri uri, String selection);
}
