package com.henghao.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.henghao.latte.delegate.LatteDelegate;

/**
 * Created by zb on 2017/11/3.
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
