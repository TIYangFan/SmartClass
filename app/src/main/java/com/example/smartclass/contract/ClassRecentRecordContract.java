package com.example.smartclass.contract;

import android.content.Context;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.AttendanceAndStatusBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassRecentRecordBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;

import org.reactivestreams.Publisher;

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

        Flowable<StudentsWithAttendanceProblemsBean> loadProblemStudentStatistics(String jobNumber, String classId);
    }

    interface View extends BaseView {

        void showClassRecentRecord(BaseArrayBean<ClassRecentRecordBean> bean);

        void showClassRecentRecordDetails(AttendanceAndStatusBean bean, StudentsWithAttendanceProblemsBean biBean, int groupPosition);

        void showAttendanceProfile();

        void showProblemStudentsList(StudentsWithAttendanceProblemsBean bean);
    }

    interface Presenter {

        void loadJobNumber();

        void loadClassRecentRecord();

        void loadClassRecentRecordDetails(String classId, int groupPosition);

        void loadProblemStudentStatistics(String classId, int groupPosition, AttendanceAndStatusBean bean);
    }
}
