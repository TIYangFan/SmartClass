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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/17
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class OverallRecentRecordFragment extends Fragment {

    @BindView(R.id.overallClassroomInformationRankingTabLayout)
    TabLayout overallClassroomInformationRankingTabLayout;
    @BindView(R.id.overallClassroomInformationTabLayout)
    TabLayout overallClassroomInformationTabLayout;
    @BindView(R.id.overallClassroomInformationRankingViewPager)
    ViewPager overallClassroomInformationRankingViewPager;
    @BindView(R.id.overallClassroomInformationViewPager)
    ViewPager overallClassroomInformationViewPager;
    @BindView(R.id.overallRecentRecordCircleProgressBar)
    CircleBarView circleBarView;
    @BindView(R.id.overallRecentRecordProgressText)
    TextView overallRecentRecordProgressText;

    private List<Fragment> fragments1;
    private List<Fragment> fragments2;

    private String[] title1 = {"出勤情况", "课堂状态"};
    private String[] title2 = {"出勤情况排行", "课堂状态排行"};

    public static OverallRecentRecordFragment newInstance() {

        Bundle args = new Bundle();

        OverallRecentRecordFragment fragment = new OverallRecentRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_overall_recent_record, container, false);
        ButterKnife.bind(this, root);
        initView();

        circleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String howToChangeText(float interpolatedTime, float progressNum, float maxNum) {
                DecimalFormat decimalFormat = new DecimalFormat("0");
                String s = decimalFormat.format(interpolatedTime * progressNum / maxNum * 100) + "%";
                return s;
            }

            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float updateNum, float maxNum) {

            }
        });
        circleBarView.setTextView(overallRecentRecordProgressText);
        circleBarView.setProgressNum(80,3000);

        return root;
    }

    private void initView(){

        initFragment();
        TabFragmentPagerAdapter adapter1 = new TabFragmentPagerAdapter(getChildFragmentManager(), fragments1);
        overallClassroomInformationViewPager.setAdapter(adapter1);
        overallClassroomInformationTabLayout.setupWithViewPager(overallClassroomInformationViewPager);
        for(int i = 0; i < title1.length; i++){
            overallClassroomInformationTabLayout.getTabAt(i).setText(title1[i]);
        }

        TabFragmentPagerAdapter adapter2 = new TabFragmentPagerAdapter(getChildFragmentManager(), fragments2);
        overallClassroomInformationRankingViewPager.setAdapter(adapter2);
        overallClassroomInformationRankingTabLayout.setupWithViewPager(overallClassroomInformationRankingViewPager);
        for(int i = 0; i < title2.length; i++){
            overallClassroomInformationRankingTabLayout.getTabAt(i).setText(title2[i]);
        }
    }

    private void initFragment(){

        fragments1 = new ArrayList<>();
//        fragments1.add(RecentOverallAttendanceFragment.newInstance());
        fragments1.add(RecentOverallStudentStatusFragment.newInstance());
        fragments1.add(RecentOverallStudentStatusFragment.newInstance());

        fragments2 = new ArrayList<>();
        fragments2.add(RecentOverallStudentStatusRankingsFragment.newInstance());
        fragments2.add(RecentOverallStudentStatusRankingsFragment.newInstance());
//        fragments2.add(RecentOverallAttendanceRankingsFragment.newInstance());
    }
}
