package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.R;
import com.example.smartclass.manager.HorizontalBarChartManager;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassAttendanceStatisticsFragment extends Fragment {

    @BindView(R.id.classAttendanceStatisticsHorizontalBarChart)
    HorizontalBarChart horizontalBarChart;

    private BarData barData;

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

        setHBarChartData();
        HorizontalBarChartManager horizontalBarChartManager = new HorizontalBarChartManager(horizontalBarChart);
        horizontalBarChartManager.initChartView();
        horizontalBarChartManager.setChartData(barData);

        return root;
    }

    private void setHBarChartData(){

        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        yVals1.add(new BarEntry(0, 4));
        yVals1.add(new BarEntry(1, 2));
        yVals1.add(new BarEntry(2, 6));
        yVals1.add(new BarEntry(3, 1));

        BarDataSet set1;
//        if(hBarChart.getData() != null && hBarChart.getData().getDataSetCount() > 0){
//            set1 = (BarDataSet) hBarChart.getData().getDataSetByIndex(0);
//            set1.setValues(yVals1);
//            hBarChart.getData().notifyDataChanged();
//            hBarChart.notifyDataSetChanged();
//        }else{
//            set1 = new BarDataSet(yVals1, "DataSet1");
//            set1.setDrawIcons(false);
//
//            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//            dataSets.add(set1);
//
//            BarData data = new BarData(dataSets);
//            data.setValueTextSize(10f);
//            data.setBarWidth(0.5f);
//
//            hBarChart.setData(data);
//        }

        set1 = new BarDataSet(yVals1, "DataSet1");
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);

        barData = data;
    }

}
