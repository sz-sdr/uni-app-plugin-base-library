package com.uniapp.library.baselib;

import android.app.Application;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.uniapp.library.baselib.util.CommonUtil;

/**
 * Created by HyFun on 2020/4/1.
 * Email: 775183940@qq.com
 * Description:
 */
public class UniAppSDRLibrary {
    private static UniAppSDRLibrary instance;

    public static UniAppSDRLibrary getInstance() {
        if (instance == null) {
            synchronized (UniAppSDRLibrary.class) {
                if (instance == null) {
                    instance = new UniAppSDRLibrary();
                }
            }
        }
        return instance;
    }


    // 注册
    private Application application;
    private boolean debug;


    public void register(Application application) {
        this.application = application;
        this.debug = CommonUtil.isApkInDebug(application);


        // 初始化logger
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
//                .methodCount(0)         // (Optional) How many method line to show. Default 2
//                .methodOffset(2)        // (Optional) Hides internal method calls up to offset. Default 5
                //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("Logger日志")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return debug;
            }
        });
        Logger.addLogAdapter(new DiskLogAdapter(CsvFormatStrategy.newBuilder().path(application.getExternalCacheDir().getAbsolutePath()).build()));
    }


    public Application getApplication() {
        return application;
    }


    public boolean isDebug() {
        return debug;
    }
}
