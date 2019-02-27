package com.example.smartclass.base;


import com.uber.autodispose.AutoDisposeConverter;

/**
 * Created by YangFan
 * On 2019/2/24
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface BaseView {

    /**
     * 显示加载中
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 数据获取失败
     * @param throwable
     */
    void onError(Throwable throwable);

    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();

}
