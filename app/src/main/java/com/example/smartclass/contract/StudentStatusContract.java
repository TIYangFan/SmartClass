package com.example.smartclass.contract;

import com.example.smartclass.base.BasePresenter;
import com.example.smartclass.base.BaseView;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface StudentStatusContract {

    interface View extends BaseView<Presenter>{

        void showCurrentStatusScatterChart();

        void showStateChangeLineChart();

        void showConcentrationDistributionPieChart();

        void showUnfocusedDistributionPieChart();

        void showUnfocusedStudentList();

        void setLoadingIndicator(boolean active);

        void showLoadingClassInformationError();
    }

    interface Presenter extends BasePresenter{

        void loadCurrentStatusStatistics();

        void loadStateChangeStatistics();

        void loadConcentrationDistributionStatistics();

        void loadUnfocusedDistributionStatistics();

        void loadUnfocusedStudentStatistics();
    }
}
