package com.uniapp.library.baselib.ui.dialog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uniapp.library.baselib.R;

/**
 * Created by HyFun on 2020/3/27.
 * Email: 775183940@qq.com
 * Description:
 */
@SuppressLint("ValidFragment")
public class SdrLoadingDialog extends DialogFragment {

    FragmentActivity activity;
    TextView textView;

    public SdrLoadingDialog(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.SDR_Theme_Dialog_NoBlur);
    }

    @Override
    public void onStart() {
        super.onStart();
//        //设置背景半透明
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sdr_layout_public_dialog_loading, null);
        textView = view.findViewById(R.id.sdr_loading_progress_tv_content);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.BLACK);
        drawable.setAlpha(150);
        drawable.setCornerRadius(10);
        view.setBackgroundDrawable(drawable);
        return view;
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        if (textView == null) {
            return;
        }
        textView.setText(title);
        show();
    }

    public void show() {
        show(activity.getSupportFragmentManager(), "SdrLoadingDialog");
    }
}
