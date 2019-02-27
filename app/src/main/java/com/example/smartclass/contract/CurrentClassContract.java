package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface CurrentClassContract {

    interface View extends BaseView {

        void showTheMainInformationOfTheClass();
    }

    interface Presenter {

        void loadTheMainInformationOfTheClass();
    }

    interface Model {

    }
}
