package com.example.smartclass.net;

import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassAndPercentBean;
import com.example.smartclass.bean.ConcentrationDistributionBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;
import com.example.smartclass.bean.TimeAndConcentrationBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.bean.test;

import java.util.ArrayList;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface APIService {

    @POST("test.php")
    Flowable<test> login();

    @GET("courses/current_lesson/main/")
    Flowable<AttendanceProfileBean> getAttendanceProfile(@Query("job_no")String jobNumber);

    @GET("courses/current_lesson/attendance/total/")
    Flowable<BaseArrayBean<TimeAndNumberOfPeopleBean>> getOverallAttendanceStatistics(@Query("job_no")String jobNumber);

    @GET("courses/current_lesson/attendance/per_class/")
    Flowable<BaseArrayBean<ClassAndPercentBean>> getClassAttendanceStatistics(@Query("job_no")String jobNumber);

    @GET("courses/current_lesson/student_state/focus_rate/")
    Flowable<ConcentrationDistributionBean> getConcentrationDistributionStatistics(@Query("job_no")String jobNumber);

    @GET("courses/absent_students/")
    Flowable<StudentsWithAttendanceProblemsBean> getProblemStudentStatistics();

    @GET("courses/current_lesson/student_state/trend/")
    Flowable<BaseArrayBean<TimeAndConcentrationBean>> getStateChangeStatistics(@Query("job_no")String jobNumber);
}
