package com.uniapp.library.baselib.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uniapp.library.baselib.R;
import com.uniapp.library.baselib.widget.MarqueeTextView;

/**
 * Created by HyFun on 2020/3/27.
 * Email: 775183940@qq.com
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements IBase, HeaderBarDefaultConfig {

    private View loadingView;
    private View realContentView;
    protected Toolbar toolbar;


    private HeaderBarDefaultConfig headerBarDefaultConfig;
    private Drawable navigationIconDrawable;
    private AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headerBarDefaultConfig = onHeaderBarConfig();
        // 隐藏actionbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置屏幕方向
        setRequestedOrientation(onActivityOrientation());
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null, false);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        View rootView = LayoutInflater.from(this).inflate(R.layout.sdr_layout_base, null, false);
        FrameLayout headerBarContainer = rootView.findViewById(R.id.layout_base_header_container);
        FrameLayout bodyContainer = rootView.findViewById(R.id.layout_base_body_container);
        loadingView = LayoutInflater.from(this).inflate(R.layout.sdr_layout_public_loading_view, null, false);
        realContentView = view;
        if (onHeaderBarToolbarRes() == 0) {
            headerBarContainer.setVisibility(View.GONE);
        } else {
            // 加载headerbarview
            LinearLayout headerBar = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.sdr_layout_base_header, null, false);
            toolbar = (Toolbar) LayoutInflater.from(getContext()).inflate(onHeaderBarToolbarRes(), null, false);
            headerBar.addView(toolbar);
            headerBarContainer.addView(headerBar);

            navigationIconDrawable = toolbar.getNavigationIcon();
            toolbar.setBackgroundColor(Color.TRANSPARENT);
            toolbar.setNavigationIcon(null);
            // 设置headerbar背景
            headerBar.setBackgroundDrawable(onHeaderBarDrawable());
        }
        // 添加真正的内容
        bodyContainer.addView(realContentView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        // 添加loadingview
        bodyContainer.addView(loadingView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        super.setContentView(rootView);
        // 设置activity全屏
        Util.setFullScreen(getActivity());
        // 加载动画
        ImageView loadingImage = loadingView.findViewById(R.id.sdr_base_activity_iv_loading);
        mAnimationDrawable = (AnimationDrawable) loadingImage.getDrawable();
        showContentView();
    }

    @Override
    public void showLoadingView() {
        if (loadingView != null && loadingView.getVisibility() != View.VISIBLE) {
            loadingView.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (realContentView.getVisibility() != View.GONE) {
            realContentView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showContentView() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (realContentView.getVisibility() != View.VISIBLE) {
            realContentView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setTitle(String title) {
        if (toolbar == null) return;
        toolbar.setTitle("");
        toolbar.setContentInsetStartWithNavigation(0);
        // 添加自定义的textview 首先寻找
        MarqueeTextView textView = Util.getHeaderBarTitleView(toolbar);

        if (textView == null) {
            textView = new MarqueeTextView(getContext());
            textView.setText(title);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
            params.gravity = onHeaderBarTextGravity();
            toolbar.addView(textView, params);
        } else {
            textView.setText(title);
        }
    }

    @Override
    public void setDisplayHomeAsUpEnabled() {
        if (toolbar == null) {
            return;
        }
        toolbar.setNavigationIcon(navigationIconDrawable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNavigationOnClickListener();
            }
        });
    }

    @Override
    public void setNavigationOnClickListener() {
        finish();
    }

    @Override
    public abstract HeaderBarDefaultConfig onHeaderBarConfig();

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

    // ——————————————————————————————————————————————————————————————————————————————————————————————————————————————

    @Override
    public int onHeaderBarToolbarRes() {
        return headerBarDefaultConfig.onHeaderBarToolbarRes();
    }

    @Override
    public int onHeaderBarTextGravity() {
        return headerBarDefaultConfig.onHeaderBarTextGravity();
    }

    @Override
    public Drawable onHeaderBarDrawable() {
        return headerBarDefaultConfig.onHeaderBarDrawable();
    }

    @Override
    public int onActivityOrientation() {
        return headerBarDefaultConfig.onActivityOrientation();
    }
}
