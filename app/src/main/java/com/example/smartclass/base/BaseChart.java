package com.example.smartclass.base;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;

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
    private boolean isSingleLine = true;

    public BaseChart(T chart, E chartData){

        this.chart = chart;
        this.data = chartData;
    }

    public BaseChart(T chart, E chartData, boolean isSingleLine){

        this.chart = chart;
        this.data = chartData;
        this.isSingleLine = isSingleLine;
    }

    /**
     * 初始化图表
     */
    public void initChartView(){

        setAxis();
        setChartStyle();

        setChartData();
    };

    /**
     * 设置图表数据
     */
    protected void setChartData(){

        if(isSingleLine){
            setSingleDataStyle();
        }else{
            setMoreDataStyle();
        }

        chart.setData(data);
        chart.invalidate();
    };

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
    protected void setSlidingRelated(){

    };

    /**
     * 高亮设置
     */
    protected void setHighlight(){

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
     * 单一数据样式设置
     */
    protected void setSingleDataStyle(){

    };

    /**
     * 多组数据样式设置
     */
    protected void setMoreDataStyle(){

    };

    /**
     * 动画设置
     */
    protected void setAnimate(){

    };
}
