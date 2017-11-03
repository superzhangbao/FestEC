package com.henghao.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.henghao.latte.app.Latte;

/**
 * Created by zb on 2017/11/3.
 */

public class DimenUtil {

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
