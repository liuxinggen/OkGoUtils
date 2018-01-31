package com.lxg.http;

/**
 * 类名：com.lxg.http
 * 时间：2018/1/30 21:28
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author Liu_xg
 */

public interface Callback<T> {
    /**
     * 数据成功时候回调
     * @param response 成功回调接口
     * @param id       成功码
     */
    void onSuccess(T response, int id);

    /**
     * 数据失败时候回调
     * @param e    失败回调异常
     * @param id   失败码
     */
    void onError(Throwable e, int id);

}
