package com.example.smartclass.bean;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/2/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class StudentsWithAttendanceProblemsBean {

    private ArrayList<StudentInformationBean> late;
    private ArrayList<StudentInformationBean> absent;
    private ArrayList<StudentInformationBean> early;
    private ArrayList<StudentInformationBean> qingjia;

    public ArrayList<StudentInformationBean> getLate() {
        return late;
    }

    public void setLate(ArrayList<StudentInformationBean> late) {
        this.late = late;
    }

    public ArrayList<StudentInformationBean> getAbsent() {
        return absent;
    }

    public void setAbsent(ArrayList<StudentInformationBean> absent) {
        this.absent = absent;
    }

    public ArrayList<StudentInformationBean> getEarly() {
        return early;
    }

    public void setEarly(ArrayList<StudentInformationBean> early) {
        this.early = early;
    }

    public ArrayList<StudentInformationBean> getQingjia() {
        return qingjia;
    }

    public void setQingjia(ArrayList<StudentInformationBean> qingjia) {
        this.qingjia = qingjia;
    }
}
