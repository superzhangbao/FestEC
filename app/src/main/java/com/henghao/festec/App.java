package com.henghao.festec;


import android.app.Application;

import com.henghao.latte.app.Latte;
import com.henghao.latte.ec.icon.FontEcModule;
import com.joanzapata.android.iconify.fonts.FontAwesomeModule;

/**
 * Created by zb on 2017/11/1.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }

}
