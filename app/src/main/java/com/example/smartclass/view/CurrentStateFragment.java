package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.R;
import com.example.smartclass.manager.ScatterChartManager;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class CurrentStateFragment extends Fragment {

    @BindView(R.id.scatterChart)
    ScatterChart scatterChart;
    private ScatterData scatterData;
    private Entry averageValue;

    public static CurrentStateFragment newInstance() {

        Bundle args = new Bundle();

        CurrentStateFragment fragment = new CurrentStateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_current_state, container, false);
        ButterKnife.bind(this, root);

        setScatterData();
        ScatterChartManager scatterChartManager = new ScatterChartManager(scatterChart, scatterData);
        scatterChartManager.initChartView();
        scatterChartManager.setLimitLine(averageValue);

        return root;
    }

    private void setScatterData(){

        ArrayList<Entry> yVals = new ArrayList<>();

        yVals.add(new Entry(1,1));
        yVals.add(new Entry(1,3));
        yVals.add(new Entry(3,1));
        yVals.add(new Entry(3,3));
        yVals.add(new Entry(2,2));

        float avX = 0;
        float avY = 0;

        for(int i = 0; i < yVals.size(); i++){
            avX += yVals.get(i).getX();
        }

        for(int i = 0; i < yVals.size(); i++){
            avY += yVals.get(i).getY();
        }

        avX /= yVals.size();
        avY /= yVals.size();

        ScatterDataSet set1 = new ScatterDataSet(yVals, "优秀");
        ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        ScatterData data = new ScatterData(dataSets);
        scatterData = data;

        averageValue =  new Entry(avX, avY);
    }

}
