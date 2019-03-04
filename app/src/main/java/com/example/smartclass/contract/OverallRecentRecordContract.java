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

        /**
         * 加载用户工号
         * @param context 当前 fragment 的上下文，用来通过 sharedPreferences 获取工号
         * @return 返回用户的工号
         */
        String loadJobNumber(Context context);

        /**
         * 加载总体近期出勤概况数据
         * @param jobNumber 用户工号
         * @return 由总体信息和其所对应的近期出勤概况组成的 bean 列表
         */
        Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber);

        /**
         * 加载总体近期出勤状况数据
         * @param jobNumber 用户工号
         * @return 各个班级近期出勤率和其对应时间
         */
        Flowable<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>> loadAttendanceStatistics(String jobNumber);

        /**
         * 加载总体近期课堂状态数据
         * @param jobNumber 用户工号
         * @return 各个班级近期课堂状态和其对应时间
         */
        Flowable<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>> loadClassStatusStatistics(String jobNumber);

        /**
         * 加载近期各个班级的出勤率和课堂状态排行数据
         * @param jobNumber 用户工号
         * @return 各个班级出勤率和课堂状态对应数据
         */
        Flowable<ClassRankingBean> loadClassRankingStatistics(String jobNumber);
    }

    interface View extends BaseView {

        /**
         * 显示近期总体的出勤概况
         * @param bean 近期总体出勤概况数据
         */
        void showAttendanceProfile(AttendanceProfileBean bean);

        /**
         * 显示各个班级近期的出勤状况和对应时间所组成的曲线图
         * @param bean 班级近期的出勤状况和对应时间数据
         */
        void showAttendanceLineChart(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean);

        /**
         * 显示各个班级近期的课堂状态和对应时间所组成的曲线图
         * @param bean 班级近期的课堂状态和对应时间数据
         */
        void showClassStatusLineChart(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean);

        /**
         * 显示各个班级的出勤率和课堂状态排行
         * @param bean 各个班级出勤率和课堂状态对应数据
         */
        void showClassRankingBarChart(ClassRankingBean bean);
    }

    interface Presenter {

        /**
         * 加载用户工号
         */
        void loadJobNumber();

        /**
         * 加载当前页面的所有数据
         */
        void loadAllStatisticsOnThePage();

        /**
         * 加载近期总体的出勤概况
         */
        void loadAttendanceProfile();

        /**
         * 加载近期各个班级的出勤状况和时间对应数据
         */
        void loadAttendanceStatistics();

        /**
         * 加载近期各个班级的课堂状态和时间对应数据
         */
        void loadClassStatusStatistics();

        /**
         * 加载各个班级出勤率和课堂状态排行
         */
        void loadClassRankingStatistics();
    }
}
