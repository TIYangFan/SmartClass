package com.example.smartclass.bean;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/3/2
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class AttendanceAndStatusBean {

    private ArrayList<DateAndPercentageBean> attendance;
    private ArrayList<DateAndPercentageBean> focus;

    public ArrayList<DateAndPercentageBean> getAttendance() {
        return attendance;
    }

    public void setAttendance(ArrayList<DateAndPercentageBean> attendance) {
        this.attendance = attendance;
    }

    public ArrayList<DateAndPercentageBean> getFocus() {
        return focus;
    }

    public void setFocus(ArrayList<DateAndPercentageBean> focus) {
        this.focus = focus;
    }
}
