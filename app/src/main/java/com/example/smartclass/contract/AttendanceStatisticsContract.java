package com.example.smartclass.contract;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;

import java.util.ArrayList;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface AttendanceStatisticsContract {

    interface Model {

        /**
         * 向后端请求关于当前课堂的出勤概况
         * @return 当前课堂的出勤概况
         */
        Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber);

        Flowable<BaseArrayBean<TimeAndNumberOfPeopleBean>> loadOverallAttendanceStatistics(String jobNumber);

        Flowable<ArrayList> loadClassAttendanceStatistics();

        Flowable<ArrayList<ArrayList>> loadProblemStudentStatistics();
    }

    interface View extends BaseView {

        void showAttendanceProfile(AttendanceProfileBean bean);

        void showOverallAttendanceLineChart(BaseArrayBean<TimeAndNumberOfPeopleBean> bean);

        void showClassAttendanceHorizontalBarChart();

        void showProblemStudentList();
    }

    interface Presenter {

        void loadAllStatisticsOnThePage();

        void loadAttendanceProfile();

        void loadOverallAttendanceStatistics();

        void loadClassAttendanceStatistics();

        void loadProblemStudentStatistics();
    }
}
