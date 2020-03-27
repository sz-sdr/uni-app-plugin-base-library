package com.sample.uniapp.baselib.base;

import com.uniapp.library.baselib.base.HeaderBarDefaultConfig;

/**
 * Created by HyFun on 2020/3/27.
 * Email: 775183940@qq.com
 * Description:
 */
public class BaseActivity extends com.uniapp.library.baselib.base.BaseActivity {
    @Override
    public HeaderBarDefaultConfig onHeaderBarConfig() {
        return new HeaderDefaultConfig();
    }
}
