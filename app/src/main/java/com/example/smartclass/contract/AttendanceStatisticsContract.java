package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassAndPercentageBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface AttendanceStatisticsContract {

    interface Model {

        Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber);

        Flowable<BaseArrayBean<TimeAndNumberOfPeopleBean>> loadOverallAttendanceStatistics(String jobNumber);

        Flowable<BaseArrayBean<ClassAndPercentageBean>> loadClassAttendanceStatistics(String jobNumber);

        Flowable<StudentsWithAttendanceProblemsBean> loadProblemStudentStatistics();
    }

    interface View extends BaseView {

        void showAttendanceProfile(AttendanceProfileBean bean);

        void showOverallAttendanceLineChart(BaseArrayBean<TimeAndNumberOfPeopleBean> bean);

        void showClassAttendanceHorizontalBarChart(BaseArrayBean<ClassAndPercentageBean> bean);

        void showProblemStudentList(StudentsWithAttendanceProblemsBean bean);
    }

    interface Presenter {

        void loadAllStatisticsOnThePage();

        void loadAttendanceProfile();

        void loadOverallAttendanceStatistics();

        void loadClassAttendanceStatistics();

        void loadProblemStudentStatistics();
    }
}
