package com.example.smartclass.view;

import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartclass.R;
import com.example.smartclass.adapter.TabFragmentPagerAdapter;
import com.example.smartclass.util.CircleBarView;
import com.example.smartclass.util.WrapContentHeightViewPager;

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
public class AttendanceStatisticsFragment extends Fragment {

    @BindView(R.id.attendanceStatisticsTabLayout)
    TabLayout attendanceStatisticsTabLayout;
    @BindView(R.id.unfocusedDetailTabLayout)
    TabLayout unfocusedDetailTabLayout;
    @BindView(R.id.attendanceStatisticsViewPager)
    ViewPager attendanceStatisticsViewPager;
    @BindView(R.id.unfocusedDetailViewPager)
    WrapContentHeightViewPager unfocusedDetailViewPager;
    @BindView(R.id.attendanceStatisticsCircleProgressBar)
    CircleBarView circleBarView;
    @BindView(R.id.attendanceStatisticsProgressText)
    TextView attendanceStatisticsProgressText;

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

        circleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String howToChangeText(float interpolatedTime, float progressNum, float maxNum) {
                DecimalFormat decimalFormat=new DecimalFormat("0");
                String s = decimalFormat.format(interpolatedTime * progressNum / maxNum * 100) + "%";
                return s;
            }

            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float updateNum, float maxNum) {

            }
        });
        circleBarView.setTextView(attendanceStatisticsProgressText);
        circleBarView.setProgressNum(80,3000);

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
//        mFragment1.add(OverallAttendanceStatisticsFragment.newInstance());
        mFragment1.add(StateChangeFragment.newInstance());
        mFragment1.add(ClassAttendanceStatisticsFragment.newInstance());

        mFragment2 = new ArrayList<>();
        mFragment2.add(UnfocusedDetailFragment.newInstance());
        mFragment2.add(UnfocusedDetailFragment.newInstance());
        mFragment2.add(UnfocusedDetailFragment.newInstance());
        mFragment2.add(UnfocusedDetailFragment.newInstance());
    }
}
