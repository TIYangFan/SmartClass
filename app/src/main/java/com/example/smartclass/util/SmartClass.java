package com.example.smartclass.util;

import android.app.Application;

/**
 * Created by YangFan
 * On 2019/3/2
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class SmartClass extends Application {

    private String jobNumber;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void clearJobNumber(){
        setJobNumber(null);
    }
}
