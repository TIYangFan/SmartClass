package com.example.smartclass.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.R;
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
public class ConcentrationDistributionFragment extends Fragment {

    @BindView(R.id.concentrationDistributionPieChart)
    PieChart pieChart;

    private PieData pieData;

    public static ConcentrationDistributionFragment newInstance() {

        Bundle args = new Bundle();

        ConcentrationDistributionFragment fragment = new ConcentrationDistributionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_concentration_distribution, container, false);
        ButterKnife.bind(this, root);

        setPieChartData();
        PieChartManager pieChartManager = new PieChartManager(pieChart);
        pieChartManager.initChartView();
        pieChartManager.setChartData(pieData);

        return root;
    }

    public void setPieChartData(){
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"aaa"));
        strings.add(new PieEntry(70f,"bbb"));

        PieDataSet dataSet = new PieDataSet(strings,"Label");
        PieData pieData = new PieData(dataSet);

        this.pieData = pieData;
    }
}
