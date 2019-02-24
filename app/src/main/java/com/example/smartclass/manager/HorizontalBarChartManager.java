package com.example.smartclass.manager;

import com.example.smartclass.base.BaseChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

/**
 * Created by YangFan
 * On 2019/2/19
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class HorizontalBarChartManager extends BaseChart<HorizontalBarChart, BarData> implements OnChartValueSelectedListener {


    public HorizontalBarChartManager(HorizontalBarChart chart, BarData chartData) {
        super(chart, chartData);
    }

    public HorizontalBarChartManager(HorizontalBarChart chart, BarData chartData, boolean isSingleLine) {
        super(chart, chartData, isSingleLine);
    }

    @Override
    protected void setChartStyle() {
        super.setChartStyle();

        chart.setOnChartValueSelectedListener(this);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.getAxisRight().setEnabled(false);
        chart.setFitBars(true);
    }

    @Override
    protected void setXAxis() {
        super.setXAxis();
        xAxis = chart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1);
    }

    @Override
    protected void setYAxis() {
        super.setYAxis();
        yAxis = chart.getAxisLeft();

        yAxis.setDrawAxisLine(true);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMinimum(0f);
    }

    @Override
    protected void setAnimate() {
        super.setAnimate();

        chart.animateY(2500);
    }

    @Override
    protected void setSingleDataStyle() {
        super.setSingleDataStyle();
        data.setValueTextSize(10f);
        data.setBarWidth(0.5f);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
