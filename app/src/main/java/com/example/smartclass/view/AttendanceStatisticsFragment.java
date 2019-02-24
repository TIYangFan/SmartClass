package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
public class AttendanceStatisticsFragment extends Fragment {

    @BindView(R.id.attendanceStatisticsTabLayout)
    TabLayout attendanceStatisticsTabLayout;
    @BindView(R.id.unfocusedDetailTabLayout)
    TabLayout unfocusedDetailTabLayout;
    @BindView(R.id.attendanceStatisticsViewPager)
    ViewPager attendanceStatisticsViewPager;
    @BindView(R.id.unfocusedDetailViewPager)
    WrapContentHeightViewPager unfocusedDetailViewPager;

    private List<Fragment> mFragment1;
    private List<Fragment> mFragment2;

    private String[] mTitles1 = {"总体出勤统计","班级出勤统计"};
    private String[] mTitles2 = {"迟到","旷课","早退","请假"};


    public static AttendanceStatisticsFragment newInstance() {

        Bundle args = new Bundle();

        AttendanceStatisticsFragment fragment = new AttendanceStatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_attendance_statistics, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView(){

        initFragment();
        TabFragmentPagerAdapter adapter1 = new TabFragmentPagerAdapter(getFragmentManager(), mFragment1);
        attendanceStatisticsViewPager.setAdapter(adapter1);
        attendanceStatisticsTabLayout.setupWithViewPager(attendanceStatisticsViewPager);
        for(int i = 0; i < mTitles1.length; i++){
            attendanceStatisticsTabLayout.getTabAt(i).setText(mTitles1[i]);
        }

        TabFragmentPagerAdapter adapter2 = new TabFragmentPagerAdapter(getFragmentManager(), mFragment2);
        unfocusedDetailViewPager.setAdapter(adapter2);
        unfocusedDetailTabLayout.setupWithViewPager(unfocusedDetailViewPager);
        for(int i = 0; i < mTitles2.length; i++){
            unfocusedDetailTabLayout.getTabAt(i).setText(mTitles2[i]);
        }
    }

    private void initFragment(){

        mFragment1 = new ArrayList<>();
        mFragment1.add(OverallAttendanceStatisticsFragment.newInstance());
        mFragment1.add(ClassAttendanceStatisticsFragment.newInstance());

        mFragment2 = new ArrayList<>();
        mFragment2.add(UnfocusedDetailFragment.newInstance(mTitles2[0]));
        mFragment2.add(UnfocusedDetailFragment.newInstance(mTitles2[1]));
        mFragment2.add(UnfocusedDetailFragment.newInstance(mTitles2[2]));
        mFragment2.add(UnfocusedDetailFragment.newInstance(mTitles2[3]));
    }
}
