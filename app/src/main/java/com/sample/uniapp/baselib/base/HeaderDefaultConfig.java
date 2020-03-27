package com.sample.uniapp.baselib.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

import com.sample.uniapp.baselib.R;
import com.uniapp.library.baselib.base.HeaderBarDefaultConfig;

/**
 * Created by HyFun on 2020/3/27.
 * Email: 775183940@qq.com
 * Description:
 */
public class HeaderDefaultConfig implements HeaderBarDefaultConfig {
    @Override
    public int onHeaderBarToolbarRes() {
        return R.layout.sdr_layout_public_toolbar_white;
    }

    @Override
    public int onHeaderBarTextGravity() {
        return Gravity.CENTER;
    }

    @Override
    public Drawable onHeaderBarDrawable() {
        return new ColorDrawable(Color.parseColor("#333333"));
    }

    @Override
    public int onActivityOrientation() {
        return ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
    }
}
