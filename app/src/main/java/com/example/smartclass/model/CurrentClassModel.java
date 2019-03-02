package com.example.smartclass.model;

import android.content.Context;

import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.contract.CurrentClassContract;
import com.example.smartclass.net.RetrofitClient;
import com.example.smartclass.util.SharedPreferencesUtil;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class CurrentClassModel implements CurrentClassContract.Model {

    @Override
    public String loadJobNumber(Context context) {
        return SharedPreferencesUtil.getStoreJobNumber(context);
    }

    @Override
    public Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getAttendanceProfile(jobNumber);
    }
}
