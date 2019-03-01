package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface ClassRecentRecordContract {

    interface Model {

        void loadClassRecentRecord();
    }

    interface View extends BaseView {

        void showClassInformation();

        void showAttendanceProfile();

        void showAttendanceLineChart();

        void showClassStatusLineChart();

        void showProblemStudentsList();
    }

    interface Presenter {

        void loadClassRecentRecord();
    }
}
