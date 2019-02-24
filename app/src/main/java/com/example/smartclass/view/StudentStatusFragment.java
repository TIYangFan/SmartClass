package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.R;
import com.example.smartclass.adapter.TabFragmentPagerAdapter;
import com.example.smartclass.util.WrapContentHeightViewPager;

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
public class StudentStatusFragment extends Fragment {

    @BindView(R.id.studentStatusStatisticsTabLayout)
    TabLayout studentStatusStatisticsTabLayout;
    @BindView(R.id.unfocusedStudentStatisticsTabLayout)
    TabLayout unfocusedStudentStatisticsTabLayout;
    @BindView(R.id.concentrationDistributionTabLayout)
    TabLayout concentrationDistributionTabLayout;
    @BindView(R.id.studentStatusStatisticsViewPager)
    ViewPager studentStatusStatisticsViewPager;
    @BindView(R.id.unfocusedStudentStatisticsViewPager)
    WrapContentHeightViewPager unfocusedStudentStatisticsViewPager;
    @BindView(R.id.concentrationDistributionViewPager)
    ViewPager concentrationDistributionViewPager;

    private List<Fragment> fragments1;
    private List<Fragment> fragments2;
    private List<Fragment> fragments3;

    private String[] title1 = {"当前状态", "状态变化"};
    private String[] title2 = {"睡觉", "玩手机", "走神"};
    private String[] title3 = {"专注度分布", "未专注分布"};

    public static StudentStatusFragment newInstance() {

        Bundle args = new Bundle();

        StudentStatusFragment fragment = new StudentStatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_student_status, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView(){

        initFragment();
        TabFragmentPagerAdapter adapter1 = new TabFragmentPagerAdapter(getFragmentManager(), fragments1);
        studentStatusStatisticsViewPager.setAdapter(adapter1);
        studentStatusStatisticsTabLayout.setupWithViewPager(studentStatusStatisticsViewPager);
        for(int i = 0; i < title1.length; i++){
            studentStatusStatisticsTabLayout.getTabAt(i).setText(title1[i]);
        }

        TabFragmentPagerAdapter adapter2 = new TabFragmentPagerAdapter(getFragmentManager(), fragments2);
        unfocusedStudentStatisticsViewPager.setAdapter(adapter2);
        unfocusedStudentStatisticsTabLayout.setupWithViewPager(unfocusedStudentStatisticsViewPager);
        for(int i = 0; i < title2.length; i++){
            unfocusedStudentStatisticsTabLayout.getTabAt(i).setText(title2[i]);
        }

        TabFragmentPagerAdapter adapter3 = new TabFragmentPagerAdapter(getFragmentManager(), fragments3);
        concentrationDistributionViewPager.setAdapter(adapter3);
        concentrationDistributionTabLayout.setupWithViewPager(concentrationDistributionViewPager);
        for(int i = 0; i < title3.length; i++){
            concentrationDistributionTabLayout.getTabAt(i).setText(title3[i]);
        }
    }

    private void initFragment(){

        fragments1 = new ArrayList<>();
        fragments1.add(CurrentStateFragment.newInstance());
        fragments1.add(StateChangeFragment.newInstance());

        fragments2 = new ArrayList<>();
        fragments2.add(UnfocusedStudentDetailsFragment.newInstance(title2[0]));
        fragments2.add(UnfocusedStudentDetailsFragment.newInstance(title2[1]));
        fragments2.add(UnfocusedStudentDetailsFragment.newInstance(title2[2]));

        fragments3 = new ArrayList<>();
        fragments3.add(ConcentrationDistributionFragment.newInstance());
        fragments3.add(UnfocusedDistributionFragment.newInstance());
    }
}
