package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface ClassRecentRecordContract {

    interface View extends BaseView {

        void showClassInformation();

        void showTheMainInformationOfTheClass();

        void showAttendanceLineChart();

        void showClassStatusLineChart();

        void showProblemStudentList();
    }

    interface Presenter {

        void LoadClassDetails();
    }

    interface Model {

    }
}
