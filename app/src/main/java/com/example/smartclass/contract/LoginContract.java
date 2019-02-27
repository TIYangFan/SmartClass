package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.test;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/1/22
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface LoginContract {

    interface Model {
        Flowable<test> login();
    }

    interface View extends BaseView {

        void test(test bean);
    }

    interface Presenter {

        void login();
    }
}
