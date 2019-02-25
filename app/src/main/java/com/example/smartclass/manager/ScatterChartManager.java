package com.example.smartclass.manager;

import android.graphics.Color;

import com.example.smartclass.base.BaseChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

/**
 * Created by YangFan
 * On 2019/2/19
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ScatterChartManager extends BaseChart<ScatterChart, ScatterData> implements OnChartValueSelectedListener {

    public ScatterChartManager(ScatterChart chart) {
        super(chart);
    }

    /**
     * 图表总体样式设置
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

        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
    }

    /**
     * y轴样式设置
     */
    @Override
    protected void setYAxis() {
        super.setYAxis();
        yAxis = chart.getAxisLeft();

        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(true);
    }

    @Override
    protected void setDataStyle() {
        super.setDataStyle();
        for(int i = 0; i < data.getDataSetCount(); i++){
            ScatterDataSet dataSet = (ScatterDataSet)data.getDataSetByIndex(i);
            dataSet.setColor(colorArray[i]);
        }
    }

    /**
     * x轴和y轴的限制线设置
     * @param averageValue x轴数据的平均值和y轴数据的平均值
     */
    public void setLimitLine(Entry averageValue){

        //设置均值
        LimitLine ll1 = new LimitLine(Math.round(averageValue.getX()), "均值");
        ll1.setLabel("均值");
        ll1.setTextColor(Color.parseColor("#5dbcfe"));
        ll1.setLineWidth(1f);
        ll1.setEnabled(true);
        ll1.setLineColor(Color.parseColor("#5dbcfe"));
        ll1.enableDashedLine(5f, 10f, 0f);//三个参数，第一个线宽长度，第二个线段之间宽度，第三个一般为0，是个补偿
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);//标签位置
        ll1.setTextSize(10f);

        //设置均值
        LimitLine ll2 = new LimitLine(Math.round(averageValue.getY()), "均值");
        ll2.setLabel("均值");
        ll2.setTextColor(Color.parseColor("#5dbcfe"));
        ll2.setLineWidth(1f);
        ll2.setEnabled(true);
        ll2.setLineColor(Color.parseColor("#5dbcfe"));
        ll2.enableDashedLine(5f, 10f, 0f);//三个参数，第一个线宽长度，第二个线段之间宽度，第三个一般为0，是个补偿
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);//标签位置
        ll2.setTextSize(10f);

        yAxis.addLimitLine(ll1);
        xAxis.addLimitLine(ll2);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
