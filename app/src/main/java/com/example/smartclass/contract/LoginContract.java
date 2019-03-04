package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;

/**
 * Created by YangFan
 * On 2019/1/22
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface LoginContract {

    /**
     * 用户登陆
     */
    interface Model {
        void login();
    }

    interface View extends BaseView {

    }

    /**
     * 用户登陆
     */
    interface Presenter {

        void login();
    }
}
