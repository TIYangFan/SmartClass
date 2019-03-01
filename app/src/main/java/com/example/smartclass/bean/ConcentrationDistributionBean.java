package com.example.smartclass.bean;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/2/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ConcentrationDistributionBean {

    private ArrayList<ClassAndPercentageBean> focus;
    private ArrayList<ClassAndPercentageBean> unfocus;

    public ArrayList<ClassAndPercentageBean> getFocus() {
        return focus;
    }

    public void setFocus(ArrayList<ClassAndPercentageBean> focus) {
        this.focus = focus;
    }

    public ArrayList<ClassAndPercentageBean> getUnfocus() {
        return unfocus;
    }

    public void setUnfocus(ArrayList<ClassAndPercentageBean> unfocus) {
        this.unfocus = unfocus;
    }
}
