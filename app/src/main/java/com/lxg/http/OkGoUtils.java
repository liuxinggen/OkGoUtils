package com.lxg.http;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * 类名：com.lxg.http
 * 时间：2018/1/30 14:23
 * 描述：OkGo网络请求框架封装
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author Liu_xg
 */

public class OkGoUtils<T> {
    private static final String TAG = "OkGoUtils";

    /**
    * 单列模式
    **/
    private static OkGoUtils mOkGoUtils = null;
        /**
         * 构造函数私有化
         **/
        private OkGoUtils() {
        }
        /**
         * 公有的静态函数，对外暴露获取单例对象的接口
         **/
        public static OkGoUtils getInstance() {
            if (mOkGoUtils == null) {
                synchronized (OkGoUtils.class) {
                    if (mOkGoUtils == null) {
                        mOkGoUtils = new OkGoUtils();
                    }
                }
            }
            return mOkGoUtils;
        }


    /**
     * get请求封装方法
     *
     * @param activity
     * @param url      请求url
     * @param params   参数
     * @param clazz    实体类
     * @param callback 回调
     */
    public void getByOkGo(Activity activity, String url,
                          HttpParams params, Class<T> clazz,
                          final Callback<T> callback) {
        OkGo
                // 请求方式和请求url
                .<T>get(url)
                .params(params)
                // 请求的 tag, 主要用于取消对应的请求
                .tag(this)
                // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheKey("cacheKey")
                // 缓存模式，详细请看缓存介绍
                .cacheMode(CacheMode.DEFAULT)
                .execute(new DialogCallback<T>(activity, clazz) {
                    @Override
                    public void onSuccess(Response<T> response) {
                        Log.i(TAG, "onSuccess: " + response);
                        callback.onSuccess(response.body(), 1);
                    }

                    @Override
                    public void onError(Response<T> response) {
                        super.onError(response);
                        Throwable throwable = response.getException();
                        if (throwable != null) {
                            throwable.printStackTrace();
                            callback.onError(throwable, 2);
                        }

                    }

                });
    }

    /**
     * post请求封装方法
     *
     * @param activity
     * @param url      请求url
     * @param params   参数
     * @param clazz    实体类
     * @param callback 回调
     */
    public void postByOkGo(Activity activity, String url,
                          HttpParams params, Class<T> clazz,
                          final Callback<T> callback) {
        OkGo
                // 请求方式和请求url
                .<T>post(url)
                .params(params)
                // 请求的 tag, 主要用于取消对应的请求
                .tag(this)
                // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheKey("cacheKey")
                // 缓存模式，详细请看缓存介绍
                .cacheMode(CacheMode.DEFAULT)
                .execute(new DialogCallback<T>(activity, clazz) {
                    @Override
                    public void onSuccess(Response<T> response) {
                        Log.i(TAG, "onSuccess: " + response);
                        callback.onSuccess(response.body(), 1);
                    }

                    @Override
                    public void onError(Response<T> response) {
                        super.onError(response);
                        Throwable throwable = response.getException();
                        if (throwable != null) {
                            throwable.printStackTrace();
                            callback.onError(throwable, 2);
                        }

                    }

                });
    }


}
