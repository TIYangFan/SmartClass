package com.example.smartclass.manager;

import com.example.smartclass.base.BaseChart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

/**
 * Created by YangFan
 * On 2019/2/24
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class BarChartManager extends BaseChart<BarChart, BarData> implements OnChartValueSelectedListener {

    private float minOfYAxis;

    public BarChartManager(BarChart chart) {
        super(chart);
    }

    /**
     * 图表总体样式设置
     */
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

    /**
     * x轴样式设置
     */
    @Override
    protected void setXAxis() {
        super.setXAxis();
        xAxis = chart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1);

        if(xAxisValueFormatter != null){
            xAxis.setValueFormatter(xAxisValueFormatter);
        }
    }

    /**
     * y轴样式设置
     */
    @Override
    protected void setYAxis() {
        super.setYAxis();
        yAxis = chart.getAxisLeft();

        yAxis.setDrawAxisLine(true);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(1f);

        if(yAxisValueFormatter != null){
            yAxis.setValueFormatter(yAxisValueFormatter);
        }
    }

    /**
     * 图表动画设置
     */
    @Override
    protected void setAnimate() {
        super.setAnimate();

        chart.animateY(2500);
    }

    /**
     * 单组数据样式设置
     */
    @Override
    protected void setDataStyle() {
        super.setDataStyle();
        data.setValueTextSize(10f);
        data.setBarWidth(0.5f);

        for(int i = 0; i < data.getDataSetCount(); i++){
            BarDataSet dataSet = (BarDataSet)data.getDataSetByIndex(i);
            dataSet.setColor(colorArray[i]);
        }
    }

    @Override
    protected void setLegend() {
        super.setLegend();
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public void setMinOfYAxis(float minOfYAxis){
        this.minOfYAxis = minOfYAxis;
    }

    public void setXAxisValueFormatter(IAxisValueFormatter iAxisValueFormatter){
        this.xAxisValueFormatter = iAxisValueFormatter;
    }

    public void setYAxisValueFormatter(IAxisValueFormatter iAxisValueFormatter){
        this.yAxisValueFormatter = iAxisValueFormatter;
    }
}
