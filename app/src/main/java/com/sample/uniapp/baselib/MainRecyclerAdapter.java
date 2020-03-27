package com.sample.uniapp.baselib;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by HyFun on 2020/3/27.
 * Email: 775183940@qq.com
 * Description:
 */
public class MainRecyclerAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    public MainRecyclerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
        TextView textView = (TextView) helper.itemView;
        textView.setText(item.getTitle());
        textView.setOnClickListener(item.getOnClickListener());
    }


    /**
     * 设置adapter
     *
     * @param recyclerView
     * @return
     */
    public static MainRecyclerAdapter setAdapter(RecyclerView recyclerView) {
        MainRecyclerAdapter recyclerAdapter = new MainRecyclerAdapter(android.R.layout.simple_list_item_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(recyclerAdapter);
        return recyclerAdapter;
    }
}
