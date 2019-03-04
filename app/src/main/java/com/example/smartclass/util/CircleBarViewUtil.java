package com.example.smartclass.util;

import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

/**
 * Created by YangFan
 * On 2019/3/2
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class CircleBarViewUtil {

    /**
     * 设置出勤率环状进度条
     * @param currentAttendance 当前出勤率
     */
    public static void setAttendanceCircleBarView(CircleBarView circleBarView, float currentAttendance, TextView textView, int animTime){

        circleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String howToChangeText(float interpolatedTime, float progressNum, float maxNum) {
                DecimalFormat decimalFormat = new DecimalFormat("0.0");
                String s = decimalFormat.format(interpolatedTime * progressNum / maxNum * 100) + "%";
                return s;
            }

            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float updateNum, float maxNum) {

            }
        });

        circleBarView.setTextView(textView);
        circleBarView.setProgressNum(currentAttendance * 100f, animTime);
    }
}
