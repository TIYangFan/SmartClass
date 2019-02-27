package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartclass.Formatter.XAxisValueFormatter;
import com.example.smartclass.R;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.manager.LineChartManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

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
    private float minOfYAxis = Integer.MAX_VALUE;

    public static final String  OVERALL_ATTENDANCE_STATISTICS = "overallAttendanceStatistics";

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

        if(OVERALL_ATTENDANCE_STATISTICS.equals(dataType)){
            setOverallAttendanceStatisticsData(chartData);
        }

    }

    @Override
    public void initChartView(){

        LineChartManager lineChartManager = new LineChartManager(lineChart);
        lineChartManager.setChartData(lineData);
        lineChartManager.setMinOfYAxis(minOfYAxis);
        setXAxisValueFormatter(lineChartManager);
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
            XAxisValueFormatter xAxisValueFormatter = new XAxisValueFormatter(XAxisValue);
            lineChartManager.setXAxisValueFormatter(xAxisValueFormatter);
        }
    }

    /**
     * 设置当前课堂——出勤统计页面的总体出勤统计数据
     * @param chartData 总体出勤统计数据
     */
    private void setOverallAttendanceStatisticsData(ArrayList chartData){

        List<Entry> chartDataList = new ArrayList<>();
        XAxisValue = new String[chartData.size()];
        TimeAndNumberOfPeopleBean bean;

        for(int i = 0; i < chartData.size(); i++){

            bean = (TimeAndNumberOfPeopleBean)chartData.get(i);
            XAxisValue[i] = bean.getTime();
            chartDataList.add(new Entry(i, bean.getPresent_students()));
            minOfYAxis = Math.min(minOfYAxis, bean.getPresent_students());
        }

        LineDataSet lineDataSet = new LineDataSet(chartDataList, "overallAttendanceStatistics");
        setLineData(lineDataSet);
    }

    /**
     * 打包创建 LineData
     * @param lineDataSet 曲线数据集合
     */
    private void setLineData(LineDataSet lineDataSet){

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        lineData = new LineData(dataSets);
    }
}
