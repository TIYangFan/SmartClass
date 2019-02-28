package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ConcentrationDistributionBean;
import com.example.smartclass.bean.TimeAndConcentrationBean;

import java.util.ArrayList;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface StudentStatusContract {

    interface Model {

        void loadCurrentStatusStatistics();

        Flowable<BaseArrayBean<TimeAndConcentrationBean>> loadStateChangeStatistics(String jobNumber);

        Flowable<ConcentrationDistributionBean> loadConcentrationDistributionStatistics(String jobNumber);

        void loadUnfocusedStudentStatistics();
    }

    interface View extends BaseView {

        void showCurrentStatusScatterChart();

        void showStateChangeLineChart(BaseArrayBean<TimeAndConcentrationBean> bean);

        void showConcentrationDistributionPieChart(ConcentrationDistributionBean bean);

        void showUnfocusedDistributionPieChart();

        void showUnfocusedStudentList();
    }

    interface Presenter {

        void loadAllStatisticsOnThePage();

        void loadCurrentStatusStatistics();

        void loadStateChangeStatistics();

        void loadConcentrationDistributionStatistics();

        void loadUnfocusedStudentStatistics();
    }
}
