package com.example.smartclass.Formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by YangFan
 * On 2019/2/18
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class StringAxisValueFormatter implements IAxisValueFormatter {

    private final String[] mLabels;
    public StringAxisValueFormatter(String[] labels) {
        mLabels = labels;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        try {
            return mLabels[(int) value];
        } catch (Exception e) {
            e.printStackTrace();
            return mLabels[0];
        }
    }
}
