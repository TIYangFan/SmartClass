package com.example.smartclass.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.Formatter.StringAxisValueFormatter;
import com.example.smartclass.Formatter.YAxisValueFormatter;
import com.example.smartclass.R;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.bean.ClassAndPercentBean;
import com.example.smartclass.manager.HorizontalBarChartManager;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassAttendanceStatisticsFragment extends Fragment implements BaseChartView {

    @BindView(R.id.classAttendanceStatisticsHorizontalBarChart)
    HorizontalBarChart horizontalBarChart;

    private BarData barData;
    private String[] XAxisValue;
    private float minOfYAxis = Integer.MAX_VALUE;
    public static final String  CLASS_ATTENDANCE_STATISTICS = "classAttendanceStatistics";

    public static ClassAttendanceStatisticsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ClassAttendanceStatisticsFragment fragment = new ClassAttendanceStatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_attendance_statistics, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setChartData(ArrayList chartData, String dataType) {

        if(CLASS_ATTENDANCE_STATISTICS.equals(dataType)){
            setClassAttendanceStatisticsData(chartData);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initChartView() {

        HorizontalBarChartManager horizontalBarChartManager = new HorizontalBarChartManager(horizontalBarChart);
        horizontalBarChartManager.setChartData(barData);
        horizontalBarChartManager.setMinOfYAxis(minOfYAxis);
        setXAxisValueFormatter(horizontalBarChartManager);
        setYAxisValueFormatter(horizontalBarChartManager);
        horizontalBarChartManager.initChartView();
    }

    @Override
    public void updateChartData() {

    }

    /**
     * 设置 x轴坐标值格式化
     * @param horizontalBarChartManager 水平柱状图管理工具类
     */
    private void setXAxisValueFormatter(HorizontalBarChartManager horizontalBarChartManager){

        if(XAxisValue != null){
            StringAxisValueFormatter xAxisValueFormatter = new StringAxisValueFormatter(XAxisValue);
            horizontalBarChartManager.setXAxisValueFormatter(xAxisValueFormatter);
        }
    }

    /**
     * 设置 x轴坐标值格式化
     * @param horizontalBarChartManager 水平柱状图管理工具类
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setYAxisValueFormatter(HorizontalBarChartManager horizontalBarChartManager){

        YAxisValueFormatter yAxisValueFormatter = new YAxisValueFormatter();
        horizontalBarChartManager.setYAxisValueFormatter(yAxisValueFormatter);
    }

    private void setClassAttendanceStatisticsData(ArrayList chartData){

        List<BarEntry> chartDataList = new ArrayList<>();
        XAxisValue = new String[chartData.size()];
        ClassAndPercentBean bean;

        for(int i = 0; i < chartData.size(); i++){

            bean = (ClassAndPercentBean)chartData.get(i);
            XAxisValue[i] = bean.getClass_no();
            chartDataList.add(new BarEntry(i, bean.getPercent()));
            minOfYAxis = Math.min(minOfYAxis, bean.getPercent());
        }

        BarDataSet barDataSet = new BarDataSet(chartDataList, "classAttendanceStatistics");
        setBarData(barDataSet);
    }

    /**
     * 打包创建 BarData
     * @param barDataSet 水平柱状图数据集合
     */
    private void setBarData(BarDataSet barDataSet){

        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        barData = new BarData(dataSets);
    }
}
