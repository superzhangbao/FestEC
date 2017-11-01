package com.henghao.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by zb on 2017/11/1.
 */

public final class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String,Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }
}
