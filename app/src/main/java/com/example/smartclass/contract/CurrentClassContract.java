package com.example.smartclass.contract;

import android.content.Context;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.AttendanceProfileBean;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface CurrentClassContract {

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
    }

    interface View extends BaseView {

        /**
         * 显示班级当前的出勤概况
         * @param bean 班级近期出勤概况数据
         */
        void showAttendanceProfile(AttendanceProfileBean bean);
    }

    interface Presenter {

        /**
         * 加载用户工号
         */
        void loadJobNumber();


        /**
         * 加载班级近期出勤概况
         */
        void loadAttendanceProfile();
    }
}
