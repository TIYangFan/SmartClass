package com.example.smartclass.manager;

import android.graphics.Color;

import com.example.smartclass.base.BaseChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/2/19
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class PieChartManager extends BaseChart<PieChart, PieData> implements OnChartValueSelectedListener {

    public PieChartManager(PieChart chart, PieData chartData) {
        super(chart, chartData);
    }

    public PieChartManager(PieChart chart, PieData chartData, boolean isSingleLine) {
        super(chart, chartData, isSingleLine);
    }

    @Override
    protected void setChartStyle() {
        super.setChartStyle();
        chart.getDescription().setEnabled(false);
    }

    @Override
    protected void setChartData() {
        super.setChartData();
    }

    @Override
    protected void setXAxis() {
        super.setXAxis();
    }

    @Override
    protected void setYAxis() {
        super.setYAxis();
    }

    @Override
    protected void setLegend() {
        super.setLegend();
        Legend legend = chart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
    }

    @Override
    protected void setSingleDataStyle() {
        super.setSingleDataStyle();
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter());

        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);

        PieDataSet dataSet = (PieDataSet) data.getDataSet();
        dataSet.setColors(colors);
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);
    }

    @Override
    protected void setAnimate() {
        super.setAnimate();
        chart.animateX(1000);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
