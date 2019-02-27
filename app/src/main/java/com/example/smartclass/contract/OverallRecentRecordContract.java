package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface OverallRecentRecordContract {

    interface View extends BaseView {

        void showTheMainInformationOfTheClass();

        void showAttendanceLineChart();

        void showClassStatusLineChart();

        void showAttendanceStatusRankingBarChart();

        void showClassStatusRankingBarChart();

        void setLoadingIndicator(boolean active);

        void showLoadingClassInformationError();
    }

    interface Presenter {

        void loadTheMainInformationOfTheClass();

        void loadAttendanceStatistics();

        void loadClassStatusStatistics();

        void loadAttendanceStatusRankingStatistics();

        void loadClassStatusRankingStatistics();
    }

    interface Model {

    }
}
