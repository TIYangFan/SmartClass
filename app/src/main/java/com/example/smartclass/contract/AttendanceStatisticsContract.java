package com.example.smartclass.contract;

import android.content.Context;

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

        /**
         * 加载用户工号
         * @param context 当前 fragment 的上下文，用来通过 sharedPreferences 获取工号
         * @return 返回用户的工号
         */
        String loadJobNumber(Context context);

        /**
         * 加载当前课堂的出勤概况
         * @param jobNumber 用户工号
         * @return 返回当前课堂的出勤概况
         */
        Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber);

        /**
         * 加载当前课堂总体的出勤数据
         * @param jobNumber 用户工号
         * @return 返回包含时间和出勤人数所对应组成的 bean 列表
         */
        Flowable<BaseArrayBean<TimeAndNumberOfPeopleBean>> loadOverallAttendanceStatistics(String jobNumber);

        /**
         * 加载当前课堂各个班级的出勤数据
         * @param jobNumber 用户工号
         * @return 返回包含班号和出勤率所组成的 bean 列表
         */
        Flowable<BaseArrayBean<ClassAndPercentageBean>> loadClassAttendanceStatistics(String jobNumber);

        /**
         * 加载当前课堂出勤异常的同学数据（迟到、旷课、早退、请假）
         * @return 返回由同学的班级、姓名、学号组成的 bean 列表
         */
        Flowable<StudentsWithAttendanceProblemsBean> loadProblemStudentStatistics();
    }

    interface View extends BaseView {

        /**
         * 显示当前课堂的出勤概况
         * @param bean 当前课堂的出勤概况数据
         */
        void showAttendanceProfile(AttendanceProfileBean bean);

        /**
         * 显示当前课堂的总体出勤变化曲线
         * @param bean 当前课堂的总体出勤人数随时间变化的曲线
         */
        void showOverallAttendanceLineChart(BaseArrayBean<TimeAndNumberOfPeopleBean> bean);

        /**
         * 显示当前课堂各个班级的出勤率随时间变化曲线
         * @param bean 当前课堂各个班级的出勤率随时间变化的曲线
         */
        void showClassAttendanceHorizontalBarChart(BaseArrayBean<ClassAndPercentageBean> bean);

        /**
         * 显示出勤异常同学的列表
         * @param bean 出勤异常同学的详细数据（班级、姓名、学号）
         */
        void showProblemStudentList(StudentsWithAttendanceProblemsBean bean);
    }

    interface Presenter {

        /**
         * 加载用户工号
         */
        void loadJobNumber();

        /**
         * 加载当前页面的全部信息
         */
        void loadAllStatisticsOnThePage();

        /**
         * 加载当前课堂的出勤概况
         */
        void loadAttendanceProfile();

        /**
         * 加载当前课堂总体的出勤人数随时间变化的数据
         */
        void loadOverallAttendanceStatistics();

        /**
         * 加载当前课堂各班的出勤率随时间变化的数据
         */
        void loadClassAttendanceStatistics();

        /**
         * 加载当前课堂出勤异常同学的数据
         */
        void loadProblemStudentStatistics();
    }
}
