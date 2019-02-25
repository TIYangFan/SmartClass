package com.example.smartclass.manager;

import android.graphics.Color;

import com.example.smartclass.base.BaseChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;


/**
 * Created by YangFan
 * On 2019/2/19
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class LineChartManager extends BaseChart<LineChart, LineData> implements OnChartValueSelectedListener {


    public LineChartManager(LineChart chart) {
        super(chart);
    }

    /**
     * 图标总体样式设置
     */
    @Override
    protected void setChartStyle() {
        super.setChartStyle();
        chart.setOnChartValueSelectedListener(this);
        chart.getDescription().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
    }

    /**
     * x轴样式设置
     */
    @Override
    protected void setXAxis() {
        super.setXAxis();
        xAxis = chart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
    }

    /**
     * y轴样式设置
     */
    @Override
    protected void setYAxis() {
        super.setYAxis();
        yAxis = chart.getAxisLeft();

        yAxis.setLabelCount(8, false);
        yAxis.setSpaceTop(15f);
        yAxis.setAxisMinimum(0f);
        yAxis.setDrawGridLines(false);
        yAxis.setGranularity(2);
    }

    /**
     * 图标动画设置
     */
    @Override
    protected void setAnimate() {
        super.setAnimate();
        chart.animateX(2500);
    }

    /**
     * 单组数据样式设置
     */
    @Override
    protected void setDataStyle() {
        super.setDataStyle();

        for(int i = 0; i < data.getDataSetCount(); i++){
            LineDataSet setComp1 = (LineDataSet) data.getDataSetByIndex(i);
            setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
            setComp1.setColor(colorArray[i]);
            setComp1.setDrawCircles(false);
            setComp1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

}
