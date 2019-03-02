package com.example.smartclass.contract;

import android.content.Context;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ConcentrationDistributionBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.bean.UnfocusedStudentDetailsBean;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface StudentStatusContract {

    interface Model {

        String loadJobNumber(Context context);

        void loadCurrentStatusStatistics();

        Flowable<BaseArrayBean<TimeAndNumberOfPeopleBean>> loadStateChangeStatistics(String jobNumber);

        Flowable<ConcentrationDistributionBean> loadConcentrationDistributionStatistics(String jobNumber);

        Flowable<UnfocusedStudentDetailsBean> loadUnfocusedStudentStatistics();
    }

    interface View extends BaseView {

        void showCurrentStatusScatterChart();

        void showStateChangeLineChart(BaseArrayBean<TimeAndNumberOfPeopleBean> bean);

        void showConcentrationDistributionPieChart(ConcentrationDistributionBean bean);

        void showUnfocusedDistributionPieChart();

        void showUnfocusedStudentList(UnfocusedStudentDetailsBean bean);
    }

    interface Presenter {

        void loadJobNumber();

        void loadAllStatisticsOnThePage();

        void loadCurrentStatusStatistics();

        void loadStateChangeStatistics();

        void loadConcentrationDistributionStatistics();

        void loadUnfocusedStudentStatistics();
    }
}
