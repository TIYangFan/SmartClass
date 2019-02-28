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
public class UnfocusedDistributionFragment extends Fragment implements BaseChartView {

    @BindView(R.id.unfocusedDistributionPieChart)
    PieChart pieChart;

    private PieData pieData;

    public static UnfocusedDistributionFragment newInstance() {

        Bundle args = new Bundle();

        UnfocusedDistributionFragment fragment = new UnfocusedDistributionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_unfocused_distribution, container, false);
        ButterKnife.bind(this, root);

//        List<PieEntry> strings = new ArrayList<>();
//        strings.add(new PieEntry(80f,"aaa"));
//        strings.add(new PieEntry(20f,"bbb"));
//
//        PieDataSet dataSet = new PieDataSet(strings,"Label");
//        PieData pieData = new PieData(dataSet);
//
//        this.pieData = pieData;
//
//        PieChartManager pieChartManager = new PieChartManager(pieChart);
//        pieChartManager.initChartView();
//        pieChartManager.setChartData(pieData);

        return root;
    }

    public void setPieData(){
        setPieChartData();
        PieChartManager pieChartManager = new PieChartManager(pieChart);
        pieChartManager.setChartData(pieData);
        pieChartManager.initChartView();
    }

    public void setPieChartData(){
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"aaa"));
        strings.add(new PieEntry(70f,"bbb"));

        PieDataSet dataSet = new PieDataSet(strings,"Label");
        PieData pieData = new PieData(dataSet);

        this.pieData = pieData;
    }

    @Override
    public void setChartData(ArrayList chartData, String dataType) {

    }

    @Override
    public void initChartView() {

    }

    @Override
    public void updateChartData() {

    }
}
