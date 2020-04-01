package com.uniapp.library.baselib.rx.observer;


import com.uniapp.library.baselib.mvp.AbstractView;

/**
 * Created by HyFun on 2019/11/8.
 * Email: 775183940@qq.com
 * Description:
 */
public interface ExceptionSolver<T extends AbstractView> {
    void solve(T t, Throwable throwable);
}
