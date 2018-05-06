package com.example.travelparis.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.travelparis.R;

/**
 * Created by Andrei on 05.05.2018.
 */

public class BitmapProvider {
    public static Bitmap getFormedBitmap(Bitmap bitmap, int height) {
        bitmap.reconfigure(bitmap.getWidth(), height, Bitmap.Config.ARGB_8888);
        return bitmap;
    }
}
