package com.example.smartclass.base;

import android.graphics.Color;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by YangFan
 * On 2019/2/24
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public abstract class BaseChart<T extends Chart, E extends ChartData> {

    protected T chart;
    protected E data;
    protected XAxis xAxis;
    protected YAxis yAxis;
    protected IAxisValueFormatter xAxisValueFormatter;
    protected IAxisValueFormatter yAxisValueFormatter;
    protected int[] colorArray = new int[]{ Color.parseColor("#27B197"), Color.parseColor("#00A6D5")};
    protected boolean isSingleLine = true;

    public BaseChart(T chart){

        this.chart = chart;
    }

    /**
     * 初始化图表
     */
    public void initChartView(){

        setAxis();
        setChartStyle();
    };

    /**
     * 设置图表数据
     */
    public void setChartData(E chartData){

        chart.clear();
        this.data = chartData;
        chart.setData(chartData);
        setDataStyle();
        chart.notifyDataSetChanged();
        chart.invalidate();
    };

    /**
     * 数据颜色设置
     */
    public void setColorArray(int[] colorArray) {
        this.colorArray = colorArray;
    }

    /**
     * 图表本身样式设置
     */
    protected void setChartStyle(){

        setHighlight();
        setSlidingRelated();
        setAnimate();
        setLegend();
    };

    /**
     * 滑动相关设置
     */
    private void setSlidingRelated(){

    };

    /**
     * 高亮设置
     */
    private void setHighlight(){

    };

    /**
     * 坐标轴设置
     */
    private void setAxis(){

        setXAxis();
        setYAxis();
    }

    /**
     * x轴设置
     */
    protected void setXAxis(){

    };

    /**
     * y轴设置
     */
    protected void setYAxis(){

    };

    /**
     * 图例设置
     */
    protected void setLegend(){

    };

    /**
     * 数据样式设置
     */
    protected void setDataStyle(){

    };

    /**
     * 动画设置
     */
    protected void setAnimate(){

    };

}
