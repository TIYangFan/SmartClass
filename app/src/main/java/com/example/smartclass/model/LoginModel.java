package com.example.smartclass.model;

import com.example.smartclass.bean.test;
import com.example.smartclass.contract.LoginContract;
import com.example.smartclass.net.RetrofitClient;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/1/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public Flowable<test> login() {
        return RetrofitClient.getInstance().getApi().login();
    }
}
