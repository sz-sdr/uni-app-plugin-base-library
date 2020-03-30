package com.sample.uniapp.baselib.ui;

import android.os.Bundle;
import android.os.Handler;

import com.sample.uniapp.baselib.R;
import com.sample.uniapp.baselib.base.BaseActivity;

public class HeaderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        setTitle("这是标题");
        setDisplayHomeAsUpEnabled();
        showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showContentView();
            }
        },2000);
    }
}
