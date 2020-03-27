package com.sample.uniapp.baselib;

import android.view.View;

/**
 * Created by HyFun on 2020/3/27.
 * Email: 775183940@qq.com
 * Description:
 */
public class Item {
    private String title;
    private View.OnClickListener  onClickListener;

    public Item(String title, View.OnClickListener onClickListener) {
        this.title = title;
        this.onClickListener = onClickListener;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
