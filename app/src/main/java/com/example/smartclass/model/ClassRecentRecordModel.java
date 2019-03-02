package com.example.smartclass.model;

import android.content.Context;

import com.example.smartclass.bean.AttendanceAndStatusBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassRecentRecordBean;
import com.example.smartclass.contract.ClassRecentRecordContract;
import com.example.smartclass.net.RetrofitClient;
import com.example.smartclass.util.SharedPreferencesUtil;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassRecentRecordModel implements ClassRecentRecordContract.Model {

    @Override
    public String loadJobNumber(Context context) {
        return SharedPreferencesUtil.getStoreJobNumber(context);
    }

    @Override
    public Flowable<BaseArrayBean<ClassRecentRecordBean>> loadClassRecentRecord(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getClassRecentRecord(jobNumber);
    }

    @Override
    public Flowable<AttendanceAndStatusBean> loadClassRecentRecordDetails(String jobNumber, String classId) {
        return RetrofitClient.getInstance().getApi().getClassRecentRecordDetails(jobNumber, classId);
    }
}
