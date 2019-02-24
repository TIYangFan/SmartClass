package com.example.smartclass.net;

import com.example.smartclass.bean.test;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface APIService {

    @POST("test.php")
    Flowable<test> login();
}
