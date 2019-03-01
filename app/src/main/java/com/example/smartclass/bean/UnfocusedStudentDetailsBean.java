package com.example.smartclass.bean;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/3/1
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class UnfocusedStudentDetailsBean {

    private ArrayList<StudentStatusDetailsBean> sleep;
    private ArrayList<StudentStatusDetailsBean> check_out_phone;
    private ArrayList<StudentStatusDetailsBean> distracted;

    public ArrayList<StudentStatusDetailsBean> getSleep() {
        return sleep;
    }

    public void setSleep(ArrayList<StudentStatusDetailsBean> sleep) {
        this.sleep = sleep;
    }

    public ArrayList<StudentStatusDetailsBean> getCheck_out_phone() {
        return check_out_phone;
    }

    public void setCheck_out_phone(ArrayList<StudentStatusDetailsBean> check_out_phone) {
        this.check_out_phone = check_out_phone;
    }

    public ArrayList<StudentStatusDetailsBean> getDistracted() {
        return distracted;
    }

    public void setDistracted(ArrayList<StudentStatusDetailsBean> distracted) {
        this.distracted = distracted;
    }
}
