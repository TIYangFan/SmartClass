package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface PersonalCenterContract {

    interface Model {

        /**
         * 加载用户信息
         */
        void loadUserInformation();
    }

    interface View extends BaseView {

        /**
         * 显示用户信息
         */
        void showUserInformation();
    }

    interface Presenter {

        /**
         * 加载用户信息
         */
        void loadUserInformation();
    }
}
