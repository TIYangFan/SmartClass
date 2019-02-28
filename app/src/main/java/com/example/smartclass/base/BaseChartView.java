package com.example.smartclass.base;

import java.util.ArrayList;

/**
 * Created by YangFan
 * On 2019/2/27
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface BaseChartView {


    /**
     * 设置图表数据
     * @param chartData 图表数据
     * @param dataType 图表数据类别
     */
    void setChartData(ArrayList chartData, String dataType);


    /**
     * 初始化图表
     */
    void initChartView();


    /**
     * 更新图表数据
     */
    void updateChartData();
}
