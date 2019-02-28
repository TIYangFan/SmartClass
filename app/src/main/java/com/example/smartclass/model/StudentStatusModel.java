package com.example.smartclass.model;

import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ConcentrationDistributionBean;
import com.example.smartclass.bean.TimeAndConcentrationBean;
import com.example.smartclass.contract.StudentStatusContract;
import com.example.smartclass.net.RetrofitClient;

import java.util.ArrayList;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class StudentStatusModel implements StudentStatusContract.Model {

    @Override
    public void loadCurrentStatusStatistics() {

    }

    @Override
    public Flowable<BaseArrayBean<TimeAndConcentrationBean>> loadStateChangeStatistics(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getStateChangeStatistics(jobNumber);
    }

    @Override
    public Flowable<ConcentrationDistributionBean> loadConcentrationDistributionStatistics(String jobNumber) {
        return RetrofitClient.getInstance().getApi().getConcentrationDistributionStatistics(jobNumber);
    }

    @Override
    public void loadUnfocusedStudentStatistics() {

    }
}
