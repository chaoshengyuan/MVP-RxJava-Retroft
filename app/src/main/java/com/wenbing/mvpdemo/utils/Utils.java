package com.wenbing.mvpdemo.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author: wenbing
 * @date: 2020/3/5 15:54
 */
public class Utils {
    /**
     * px像素，转换成dp
     *
     * @param context
     * @param Pixels
     * @return
     */
    public static int pixelsToDip(Context context, int Pixels) {
        int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Pixels,
                context.getResources().getDisplayMetrics());
        return dip;
    }
}
