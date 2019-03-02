package com.example.smartclass.contract;

import android.content.Context;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassInfoAboutTimeAndRelatedInfoBean;
import com.example.smartclass.bean.ClassRankingBean;
import com.example.smartclass.bean.DateAndPercentageBean;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface OverallRecentRecordContract {

    interface Model {

        String loadJobNumber(Context context);

        Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber);

        Flowable<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>> loadAttendanceStatistics(String jobNumber);

        Flowable<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>> loadClassStatusStatistics(String jobNumber);

        Flowable<ClassRankingBean> loadClassRankingStatistics(String jobNumber);
    }

    interface View extends BaseView {

        void showAttendanceProfile(AttendanceProfileBean bean);

        void showAttendanceLineChart(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean);

        void showClassStatusLineChart(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean);

        void showClassRankingBarChart(ClassRankingBean bean);
    }

    interface Presenter {

        void loadJobNumber();

        void loadAllStatisticsOnThePage();

        void loadAttendanceProfile();

        void loadAttendanceStatistics();

        void loadClassStatusStatistics();

        void loadClassRankingStatistics();
    }
}
