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

        void loadUserInformation();
    }

    interface View extends BaseView {

        void showUserInformation();
    }

    interface Presenter {

        void loadUserInformation();
    }
}
