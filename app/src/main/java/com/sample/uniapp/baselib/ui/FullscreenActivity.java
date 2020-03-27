package com.sample.uniapp.baselib.ui;

import android.os.Bundle;

import com.sample.uniapp.baselib.R;
import com.sample.uniapp.baselib.base.BaseActivity;

public class FullscreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
    }

    @Override
    public int onHeaderBarToolbarRes() {
        return 0;
    }
}
