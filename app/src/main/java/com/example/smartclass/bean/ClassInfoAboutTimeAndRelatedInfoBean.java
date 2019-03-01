package com.example.smartclass.bean;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/3/1
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassInfoAboutTimeAndRelatedInfoBean<T> {

    private String class_no;
    private ArrayList<T> info;

    public String getClass_no() {
        return class_no;
    }

    public void setClass_no(String class_no) {
        this.class_no = class_no;
    }

    public ArrayList<T> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<T> info) {
        this.info = info;
    }
}
