package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface StudentStatusContract {

    interface View extends BaseView {

        /**
         * 显示当前学生状态的散点图
         */
        void showCurrentStatusScatterChart();

        /**
         * 显示一段时间内学生状态变化的曲线图
         */
        void showStateChangeLineChart();

        void showConcentrationDistributionPieChart();

        void showUnfocusedDistributionPieChart();

        void showUnfocusedStudentList();
    }

    interface Presenter {

        void loadCurrentStatusStatistics();

        void loadStateChangeStatistics();

        void loadConcentrationDistributionStatistics();

        void loadUnfocusedDistributionStatistics();

        void loadUnfocusedStudentStatistics();
    }

    interface Model {

    }
}
