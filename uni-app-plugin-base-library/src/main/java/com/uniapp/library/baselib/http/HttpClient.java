package com.uniapp.library.baselib.http;

import com.google.gson.Gson;
import com.uniapp.library.baselib.UniAppSDRLibrary;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HYF on 2018/10/27.
 * Email：775183940@qq.com
 */

public class HttpClient {
    public static final String TAG = "OkHttp";


    public static final Gson gson = new Gson();

    private static HttpClient httpClient;

    private OkHttpClient okHttpClient;

    private HttpClient() {
        // 初始化okhttp client
        okHttpClient = createOkHttpClient();
    }

    public static HttpClient getInstance() {
        if (httpClient == null) {
            synchronized (HttpClient.class) {
                if (httpClient == null) {
                    httpClient = new HttpClient();
                }
            }
        }
        return httpClient;
    }

    public <T> T createRetrofit(String url, OkHttpClient okHttpClient, Class<T> clazz) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        builder.baseUrl(url);//设置远程地址
        builder.addConverterFactory(new NullOnEmptyConverterFactory());
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build().create(clazz);
    }


    /**
     * 获取okhttp client  实例
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 创建一个okhttp client实例
     *
     * @return
     */

    private OkHttpClient createOkHttpClient() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            // Install the all-trusting trust manager TLS
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            // 缓存  只会存放get缓存 存放在data/data/cache/NetCache文件夹下
            File cacheFile = new File(HttpClientUtil.getNetCachePath(UniAppSDRLibrary.getInstance().getApplication().getApplicationContext()));
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            // 超时时间 30秒
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);
            //设置缓存
            AddCacheInterceptor cacheInterceptor = new AddCacheInterceptor(UniAppSDRLibrary.getInstance().getApplication().getApplicationContext());
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.cache(cache);
            // interceptor  log
            builder.addNetworkInterceptor(getLoggingInterceptor(UniAppSDRLibrary.getInstance().isDebug()));
            //错误重连
            builder.retryOnConnectionFailure(true);
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 日志的Interceptor
     *
     * @return
     */
    private HttpLoggingInterceptor getLoggingInterceptor(boolean debug) {
         HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLogger());
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (debug) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // 测试
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE); // 打包
        }
        return interceptor;
    }


    final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }};

}
