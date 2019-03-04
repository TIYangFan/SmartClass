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
import com.example.smartclass.Formatter.PercentageAxisValueFormatter;
import com.example.smartclass.R;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.bean.ClassAndPercentageBean;
import com.example.smartclass.manager.BarChartManager;
import com.github.mikephil.charting.charts.BarChart;
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
 * On 2019/2/17
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class BarChartView extends Fragment implements BaseChartView {

    @BindView(R.id.barChartView)
    BarChart barChart;

    private BarData barData;
    private String[] XAxisValue;
    private float minOfYAxis = Integer.MAX_VALUE;

    public static final String RECENT_OVERALL_ATTENDANCE_RANKING_STATISTICS = "recentOverallAttendanceRankingStatistics";
    public static final String RECENT_OVERALL_STUDENT_STATUS_RANKING_STATISTICS = "recentOverallStudentStatusRankingStatistics";

    public static BarChartView newInstance() {

        Bundle args = new Bundle();

        BarChartView fragment = new BarChartView();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_bar_chart, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setChartData(ArrayList chartData, String dataType) {

        if(RECENT_OVERALL_ATTENDANCE_RANKING_STATISTICS.equals(dataType) ||
                RECENT_OVERALL_STUDENT_STATUS_RANKING_STATISTICS.equals(dataType)){

            setClassRankingStatistics(chartData);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initChartView() {

        BarChartManager barChartManager = new BarChartManager(barChart);
        barChartManager.setChartData(barData);
        barChartManager.setMinOfYAxis(minOfYAxis);
        setXAxisValueFormatter(barChartManager);
        setYAxisValueFormatter(barChartManager);
        barChartManager.initChartView();
    }

    @Override
    public void updateChartData() {

    }

    private void setXAxisValueFormatter(BarChartManager barChartManager){

        if(XAxisValue != null){
            StringAxisValueFormatter xAxisValueFormatter = new StringAxisValueFormatter(XAxisValue);
            barChartManager.setXAxisValueFormatter(xAxisValueFormatter);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setYAxisValueFormatter(BarChartManager barChartManager){

        PercentageAxisValueFormatter percentageAxisValueFormatter = new PercentageAxisValueFormatter();
        barChartManager.setYAxisValueFormatter(percentageAxisValueFormatter);
    }

    private void setClassRankingStatistics(ArrayList chartData){

        List<BarEntry> chartDataList = new ArrayList<>();
        XAxisValue = new String[chartData.size()];
        ClassAndPercentageBean bean;

        for(int i = 0; i < chartData.size(); i++){

            bean = (ClassAndPercentageBean)chartData.get(i);
            XAxisValue[i] = bean.getClass_no();
            chartDataList.add(new BarEntry(i, bean.getPercent()));
            minOfYAxis = Math.min(minOfYAxis, bean.getPercent());
        }

        BarDataSet barDataSet = new BarDataSet(chartDataList, "classRankingStatistics");
        setBarData(barDataSet);
    }


    private void setBarData(BarDataSet barDataSet){

        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        barData = new BarData(dataSets);
    }
}
