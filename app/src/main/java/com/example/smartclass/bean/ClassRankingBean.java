package com.example.smartclass.bean;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/3/1
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassRankingBean {

    private ArrayList<ClassAndPercentageBean> attendance;
    private ArrayList<ClassAndPercentageBean> focus;

    public ArrayList<ClassAndPercentageBean> getAttendance() {
        return attendance;
    }

    public void setAttendance(ArrayList<ClassAndPercentageBean> attendance) {
        this.attendance = attendance;
    }

    public ArrayList<ClassAndPercentageBean> getFocus() {
        return focus;
    }

    public void setFocus(ArrayList<ClassAndPercentageBean> focus) {
        this.focus = focus;
    }
}
