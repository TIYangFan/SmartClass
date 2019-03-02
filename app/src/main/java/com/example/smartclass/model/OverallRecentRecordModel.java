package com.example.smartclass.model;

import android.content.Context;

import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassInfoAboutTimeAndRelatedInfoBean;
import com.example.smartclass.bean.ClassRankingBean;
import com.example.smartclass.bean.DateAndPercentageBean;
import com.example.smartclass.contract.OverallRecentRecordContract;
import com.example.smartclass.net.RetrofitClient;
import com.example.smartclass.util.SharedPreferencesUtil;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class OverallRecentRecordModel implements OverallRecentRecordContract.Model {

    @Override
    public String loadJobNumber(Context context) {
        return SharedPreferencesUtil.getStoreJobNumber(context);
    }

    @Override
    public Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getAttendanceProfile(jobNumber);
    }

    @Override
    public Flowable<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>> loadAttendanceStatistics(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getAttendanceStatistics(jobNumber);
    }

    @Override
    public Flowable<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>> loadClassStatusStatistics(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getClassStatusStatistics(jobNumber);
    }

    @Override
    public Flowable<ClassRankingBean> loadClassRankingStatistics(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getClassRankingStatistics(jobNumber);
    }
}
