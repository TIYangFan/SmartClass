package com.example.smartclass.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.Formatter.StringAxisValueFormatter;
import com.example.smartclass.Formatter.PercentageAxisValueFormatter;
import com.example.smartclass.R;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.bean.ClassInfoAboutTimeAndRelatedInfoBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.bean.DateAndPercentageBean;
import com.example.smartclass.manager.LineChartManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

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
public class StateChangeFragment extends Fragment implements BaseChartView {

    @BindView(R.id.stateChangeLineChart)
    LineChart lineChart;

    private LineData lineData;
    private String[] XAxisValue;
    private String dataType;
    private float minOfYAxis = Integer.MAX_VALUE;
    private float maxOfYAxis = Integer.MIN_VALUE;

    public static final String OVERALL_ATTENDANCE_STATISTICS = "overallAttendanceStatistics";
    public static final String STATE_CHANGE_STATISTICS = "stateChangeStatistics";
    public static final String RECENT_OVERALL_ATTENDANCE_STATISTICS = "recentOverallAttendanceStatistics";
    public static final String RECENT_OVERALL_CLASS_STATUS_STATISTICS = "recentOverallClassStatusStatistics";

    public static StateChangeFragment newInstance() {

        Bundle args = new Bundle();

        StateChangeFragment fragment = new StateChangeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_state_change, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setChartData(ArrayList chartData, String dataType) {

        this.dataType = dataType;
        if(OVERALL_ATTENDANCE_STATISTICS.equals(dataType)){
            setOverallAttendanceStatisticsData(chartData);
        } else if(STATE_CHANGE_STATISTICS.equals(dataType)){
            setStateChangeStatisticsData(chartData);
        } else if(RECENT_OVERALL_ATTENDANCE_STATISTICS.equals(dataType) || RECENT_OVERALL_CLASS_STATUS_STATISTICS.equals(dataType)){
            setRecentOverallClassStatistics(chartData);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initChartView(){

        if (lineChart == null) return;
        LineChartManager lineChartManager = new LineChartManager(lineChart);
        lineChartManager.setChartData(lineData);
        lineChartManager.setMinOfYAxis(minOfYAxis);
        lineChartManager.setMaxOfYAxis(maxOfYAxis);
        setXAxisValueFormatter(lineChartManager);
        if(RECENT_OVERALL_ATTENDANCE_STATISTICS.equals(dataType) || RECENT_OVERALL_CLASS_STATUS_STATISTICS.equals(dataType)){
            lineChartManager.setPercentage(true);
            setYAxisValueFormatter(lineChartManager);
        }
        lineChartManager.initChartView();
    }

    @Override
    public void updateChartData() {

    }

    /**
     * 设置 x轴坐标值格式化
     * @param lineChartManager 曲线管理工具类
     */
    private void setXAxisValueFormatter(LineChartManager lineChartManager){

        if(XAxisValue != null){
            StringAxisValueFormatter xAxisValueFormatter = new StringAxisValueFormatter(XAxisValue);
            lineChartManager.setXAxisValueFormatter(xAxisValueFormatter);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setYAxisValueFormatter(LineChartManager lineChartManager){

        PercentageAxisValueFormatter percentageAxisValueFormatter = new PercentageAxisValueFormatter();
        lineChartManager.setYAxisValueFormatter(percentageAxisValueFormatter);
    }

    /**
     * 设置当前课堂——出勤统计页面的总体出勤统计数据
     * @param chartData 总体出勤统计数据
     */
    private void setOverallAttendanceStatisticsData(ArrayList chartData){

        List<Entry> chartDataList = new ArrayList<>();
        List<ILineDataSet> dataSets = new ArrayList<>();

        XAxisValue = new String[chartData.size()];
        TimeAndNumberOfPeopleBean bean;

        for(int i = 0; i < chartData.size(); i++){

            bean = (TimeAndNumberOfPeopleBean)chartData.get(i);
            XAxisValue[i] = bean.getTime();
            chartDataList.add(new Entry(i, bean.getPresent_students()));
            minOfYAxis = Math.min(minOfYAxis, bean.getPresent_students());
            maxOfYAxis = Math.max(maxOfYAxis, bean.getPresent_students());
        }

        LineDataSet lineDataSet = new LineDataSet(chartDataList, "overallAttendanceStatistics");
        dataSets.add(lineDataSet);
        setLineData(dataSets);
    }

    /**
     * 设置当前课堂——出勤统计页面的总体出勤统计数据
     * @param chartData 总体出勤统计数据
     */
    private void setStateChangeStatisticsData(ArrayList chartData){

        List<Entry> chartDataList = new ArrayList<>();
        List<ILineDataSet> dataSets = new ArrayList<>();

        XAxisValue = new String[chartData.size()];
        TimeAndNumberOfPeopleBean bean;

        for(int i = 0; i < chartData.size(); i++){

            bean = (TimeAndNumberOfPeopleBean)chartData.get(i);
            XAxisValue[i] = bean.getTime();
            chartDataList.add(new Entry(i, bean.getPresent_students()));
            minOfYAxis = Math.min(minOfYAxis, bean.getPresent_students());
            maxOfYAxis = Math.max(maxOfYAxis, bean.getPresent_students());
        }

        LineDataSet lineDataSet = new LineDataSet(chartDataList, "stateChangeStatistics");
        dataSets.add(lineDataSet);
        setLineData(dataSets);
    }

    /**
     * 设置当前课堂——出勤统计页面的总体出勤统计数据
     * @param chartData 总体出勤统计数据
     */
    private void setRecentOverallClassStatistics(ArrayList chartData){

        List<ILineDataSet> dataSets = new ArrayList<>();

        ClassInfoAboutTimeAndRelatedInfoBean itemData;
        ArrayList beans;
        DateAndPercentageBean bean;

        for(int i = 0; i < chartData.size(); i++){

            List<Entry> chartDataList = new ArrayList<>();
            itemData = (ClassInfoAboutTimeAndRelatedInfoBean)chartData.get(i);
            beans = itemData.getInfo();
            if(i == 0){
                XAxisValue = new String[beans.size()];
            }
            for(int j = 0; j < beans.size(); j++){

                bean = (DateAndPercentageBean)beans.get(j);
                if(i == 0){
                    XAxisValue[j] = bean.getDate();
                }
                chartDataList.add(new Entry(j, bean.getPercent()));
                minOfYAxis = Math.min(minOfYAxis, bean.getPercent());
                maxOfYAxis = Math.max(maxOfYAxis, bean.getPercent());
            }

            LineDataSet lineDataSet = new LineDataSet(chartDataList, itemData.getClass_no());
            dataSets.add(lineDataSet);
        }

        setLineData(dataSets);
    }

    /**
     * 打包创建 LineData
     * @param dataSets 曲线数据集合
     */
    private void setLineData(List<ILineDataSet> dataSets){

        lineData = new LineData(dataSets);
    }
}
