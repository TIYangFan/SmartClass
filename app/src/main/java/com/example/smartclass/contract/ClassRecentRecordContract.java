package com.example.smartclass.contract;

import android.content.Context;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.AttendanceAndStatusBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassRecentRecordBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;


import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface ClassRecentRecordContract {

    interface Model {

        /**
         * 加载用户工号
         * @param context 当前 fragment 的上下文，用来通过 sharedPreferences 获取工号
         * @return 返回用户的工号
         */
        String loadJobNumber(Context context);

        /**
         * 加载班级近期出勤概况数据
         * @param jobNumber 用户工号
         * @return 由班级信息和其所对应的近期出勤概况组成的 bean 列表
         */
        Flowable<BaseArrayBean<ClassRecentRecordBean>> loadClassRecentRecord(String jobNumber);

        /**
         * 加载班级近期出勤情况和课堂状态数据
         * @param jobNumber 用户工号
         * @param classId 班号
         * @return 由班级近期出勤情况和课堂状态组成的 bean
         */
        Flowable<AttendanceAndStatusBean> loadClassRecentRecordDetails(String jobNumber, String classId);

        /**
         * 加载班级近期出勤异常同学的信息
         * @param jobNumber 工号
         * @param classId 班号
         * @return 各种情况出勤异常（迟到、旷课、早退、请假）同学的详细信息
         */
        Flowable<StudentsWithAttendanceProblemsBean> loadProblemStudentStatistics(String jobNumber, String classId);
    }

    interface View extends BaseView {

        /**
         * 显示班级近期的出勤概况
         * @param bean 班级近期出勤概况数据
         */
        void showClassRecentRecord(BaseArrayBean<ClassRecentRecordBean> bean);

        /**
         * 显示当前班级近期的出勤状况和课堂状态随时间变化曲线、出勤异常同学详细信息列表
         * @param bean 班级近期出勤状况和课堂状态随时间变化数据
         * @param biBean 班级近期出勤异常详细同学信息
         * @param groupPosition 当前班级在列表中的位置
         */
        void showClassRecentRecordDetails(AttendanceAndStatusBean bean, StudentsWithAttendanceProblemsBean biBean, int groupPosition);
    }

    interface Presenter {

        /**
         * 加载用户工号
         */
        void loadJobNumber();

        /**
         * 加载班级近期出勤概况
         */
        void loadClassRecentRecord();

        /**
         * 加载班级近期出勤状况和课堂状态随时间变化数据
         * @param classId 班号
         * @param groupPosition 班级在列表中的位置
         */
        void loadClassRecentRecordDetails(String classId, int groupPosition);

        /**
         * 加载出勤异常同学的数据
         * @param classId 班号
         * @param groupPosition 班级在列表中的位置
         * @param bean 班级近期出勤状况和课堂状态随时间变化数据
         */
        void loadProblemStudentStatistics(String classId, int groupPosition, AttendanceAndStatusBean bean);
    }
}
