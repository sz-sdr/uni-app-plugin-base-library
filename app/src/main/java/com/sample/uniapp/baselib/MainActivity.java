package com.sample.uniapp.baselib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sample.uniapp.baselib.base.BaseActivity;
import com.sample.uniapp.baselib.ui.FullscreenActivity;
import com.sample.uniapp.baselib.ui.HeaderActivity;
import com.uniapp.library.baselib.ui.dialog.SdrLoadingDialog;

public class MainActivity extends BaseActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("这是一个很长很长的标题这是一个很长很长的标题这是一个很长很长的标题");
        recyclerView = findViewById(R.id.recycler);
        // 添加item
        MainRecyclerAdapter recyclerAdapter = MainRecyclerAdapter.setAdapter(recyclerView);
        recyclerAdapter.addData(new Item("打开一个全屏的页面", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FullscreenActivity.class));
            }
        }));


        recyclerAdapter.addData(new Item("打开一个基本的页面", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HeaderActivity.class));
            }
        }));


        recyclerAdapter.addData(new Item("打开loading", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SdrLoadingDialog().show(getSupportFragmentManager(),"SdrLoadingDialog");
            }
        }));
    }
}
