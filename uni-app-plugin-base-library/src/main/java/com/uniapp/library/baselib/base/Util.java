package com.uniapp.library.baselib.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.uniapp.library.baselib.widget.MarqueeTextView;

/**
 * Created by HyFun on 2020/3/27.
 * Email: 775183940@qq.com
 * Description:
 */
class Util {

    /**
     * 设置内容全屏,即内容延伸至状态栏底部,状态栏文字还在
     *
     * @param activity
     */
    public static void setFullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置透明状态栏,这样才能让 ContentView 向上
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }



    /**
     * 获取toolbar 的titleview
     *
     * @param toolbar
     * @return
     */
    public final static MarqueeTextView getHeaderBarTitleView(Toolbar toolbar) {
        MarqueeTextView textView = null;
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            if (toolbar.getChildAt(i) instanceof MarqueeTextView) {
                textView = (MarqueeTextView) toolbar.getChildAt(i);
                break;
            }
        }
        return textView;
    }
}
