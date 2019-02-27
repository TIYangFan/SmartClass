package com.example.smartclass.bean;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/2/27
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class BaseArrayBean<T> {

    private ArrayList<T> data;

    public ArrayList<T> getArrayList() {
        return data;
    }

    public void setArrayList(ArrayList<T> arrayList) {
        this.data = arrayList;
    }
}
