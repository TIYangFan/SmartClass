package com.example.smartclass.util;

import android.app.Activity;

import com.example.smartclass.R;

/**
 * Created by YangFan
 * On 2019/2/26
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class PageSwitchingAnimation {

    /**
     * 页面统一的入场动画
     * @param activity 当前托管fragment 的activity
     */
    public static void startActivityAnimation(Activity activity){
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    /**
     * 页面统一的出场动画
     * @param activity 当前页面的 activity
     */
    public static void finshActivityAnimation(Activity activity){
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
