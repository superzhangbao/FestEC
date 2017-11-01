package com.henghao.festec;

import android.app.Application;

import com.henghao.latte.app.Latte;

/**
 * Created by zb on 2017/11/1.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .configure();
    }
}
