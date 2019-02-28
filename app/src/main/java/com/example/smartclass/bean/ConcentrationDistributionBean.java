package com.example.smartclass.bean;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/2/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ConcentrationDistributionBean {

    private ArrayList<ClassAndPercentBean> focus;
    private ArrayList<ClassAndPercentBean> unfocus;

    public ArrayList<ClassAndPercentBean> getFocus() {
        return focus;
    }

    public void setFocus(ArrayList<ClassAndPercentBean> focus) {
        this.focus = focus;
    }

    public ArrayList<ClassAndPercentBean> getUnfocus() {
        return unfocus;
    }

    public void setUnfocus(ArrayList<ClassAndPercentBean> unfocus) {
        this.unfocus = unfocus;
    }
}
