package com.example.smartclass.model;

import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassAndPercentageBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.contract.AttendanceStatisticsContract;
import com.example.smartclass.net.RetrofitClient;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class AttendanceStatisticsModel implements AttendanceStatisticsContract.Model {

    @Override
    public Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getAttendanceProfile(jobNumber);
    }

    @Override
    public Flowable<BaseArrayBean<TimeAndNumberOfPeopleBean>> loadOverallAttendanceStatistics(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getOverallAttendanceStatistics(jobNumber);
    }

    @Override
    public Flowable<BaseArrayBean<ClassAndPercentageBean>> loadClassAttendanceStatistics(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getClassAttendanceStatistics(jobNumber);
    }

    @Override
    public Flowable<StudentsWithAttendanceProblemsBean> loadProblemStudentStatistics() {
        return RetrofitClient.getInstance().getApi().getProblemStudentStatistics();
    }
}
