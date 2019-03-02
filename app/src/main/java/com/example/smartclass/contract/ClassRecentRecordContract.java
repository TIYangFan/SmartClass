package com.example.smartclass.contract;

import android.content.Context;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.AttendanceAndStatusBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassRecentRecordBean;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface ClassRecentRecordContract {

    interface Model {

        String loadJobNumber(Context context);

        Flowable<BaseArrayBean<ClassRecentRecordBean>> loadClassRecentRecord(String jobNumber);

        Flowable<AttendanceAndStatusBean> loadClassRecentRecordDetails(String jobNumber, String classId);
    }

    interface View extends BaseView {

        void showClassRecentRecord(BaseArrayBean<ClassRecentRecordBean> bean);

        void showClassRecentRecordDetails(AttendanceAndStatusBean bean);

        void showClassInformation();

        void showAttendanceProfile();

        void showAttendanceLineChart();

        void showClassStatusLineChart();

        void showProblemStudentsList();
    }

    interface Presenter {

        void loadJobNumber();

        void loadClassRecentRecord();
    }
}
