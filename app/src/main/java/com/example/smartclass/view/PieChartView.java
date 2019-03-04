package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.bean.ClassAndPercentageBean;
import com.example.smartclass.manager.PieChartManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

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
public class PieChartView extends Fragment implements BaseChartView {

    @BindView(R.id.pieChartView)
    PieChart pieChart;

    private PieData pieData;

    public static final String  CONCENTRATION_DISTRIBUTION = "concentrationDistribution";

    public static PieChartView newInstance() {

        Bundle args = new Bundle();

        PieChartView fragment = new PieChartView();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_pie_chart, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setChartData(ArrayList chartData, String dataType) {

        if(CONCENTRATION_DISTRIBUTION.equals(dataType)){
            setConcentrationDistributionData(chartData);
        }
    }

    @Override
    public void initChartView() {

        PieChartManager pieChartManager = new PieChartManager(pieChart);
        pieChartManager.setChartData(pieData);
        pieChartManager.initChartView();
    }

    @Override
    public void updateChartData() {

    }

    private void setConcentrationDistributionData(ArrayList chartData) {

        List<PieEntry> pieDataList = new ArrayList<>();
        ClassAndPercentageBean bean;

        for(int i = 0; i < chartData.size(); i++){

            bean = (ClassAndPercentageBean)chartData.get(i);
            pieDataList.add(new PieEntry(bean.getPercent() * 100, bean.getClass_no()));
        }

        PieDataSet pieDataSet = new PieDataSet(pieDataList, "");
        setPieData(pieDataSet);
    }

    private void setPieData(PieDataSet pieDataSet){

        pieData = new PieData(pieDataSet);
    }

}
