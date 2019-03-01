package com.example.smartclass.Formatter;

import android.icu.text.DecimalFormat;
import android.os.Build;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import androidx.annotation.RequiresApi;

/**
 * Created by YangFan
 * On 2019/2/18
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class PercentageAxisValueFormatter implements IAxisValueFormatter {

    private DecimalFormat mFormat;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public PercentageAxisValueFormatter(){
        mFormat = new DecimalFormat("0.0");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
         return mFormat.format(value * 100) + "%";
    }
}
