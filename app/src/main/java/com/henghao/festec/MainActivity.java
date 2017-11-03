package com.henghao.festec;

import com.henghao.latte.activities.ProxyActivity;
import com.henghao.latte.delegate.LatteDelegate;

public class MainActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
