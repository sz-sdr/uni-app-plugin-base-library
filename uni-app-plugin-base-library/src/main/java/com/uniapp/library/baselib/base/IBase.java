package com.uniapp.library.baselib.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HyFun on 2020/3/27.
 * Email: 775183940@qq.com
 * Description:
 */
interface IBase {
    void showLoadingView();

    void showContentView();

    void setTitle(String title);

    void setDisplayHomeAsUpEnabled();

    void setNavigationOnClickListener();

    HeaderBarDefaultConfig onHeaderBarConfig();

    AppCompatActivity getActivity();

    Context getContext();
}
