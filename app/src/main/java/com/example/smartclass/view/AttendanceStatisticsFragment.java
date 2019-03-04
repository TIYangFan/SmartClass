package com.example.smartclass.view;

import android.content.res.Resources;
import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartclass.R;
import com.example.smartclass.adapter.TabFragmentPagerAdapter;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.base.BaseTabLayoutView;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassAndPercentageBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.contract.AttendanceStatisticsContract;
import com.example.smartclass.presenter.AttendanceStatisticsPresenter;
import com.example.smartclass.util.CircleBarView;
import com.example.smartclass.util.CircleBarViewUtil;
import com.example.smartclass.util.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import butterknife.BindView;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class AttendanceStatisticsFragment extends BaseMvpFragment<AttendanceStatisticsPresenter> implements AttendanceStatisticsContract.View, BaseTabLayoutView {

    @BindView(R.id.attendanceStatisticsTabLayout)
    TabLayout attendanceStatisticsTabLayout;
    @BindView(R.id.studentsWithAttendanceProblemsTabLayout)
    TabLayout studentsWithAttendanceProblemsTabLayout;
    @BindView(R.id.attendanceStatisticsViewPager)
    ViewPager attendanceStatisticsViewPager;
    @BindView(R.id.studentsWithAttendanceProblemsViewPager)
    WrapContentHeightViewPager studentsWithAttendanceProblemsViewPager;
    @BindView(R.id.attendanceStatisticsCircleProgressBar)
    CircleBarView circleBarView;
    @BindView(R.id.attendanceStatisticsProgressText)
    TextView attendanceStatisticsProgressText;

    @BindView(R.id.currentPersonOfLateTextView)
    TextView currentPersonOfLateTextView;
    @BindView(R.id.currentOverallPersonOfLateTextView)
    TextView currentOverallPersonOfLateTextView;
    @BindView(R.id.currentPersonOfAbsenteeTextView)
    TextView currentPersonOfAbsenteeTextView;
    @BindView(R.id.currentOverallPersonOfAbsenteeTextView)
    TextView currentOverallPersonOfAbsenteeTextView;
    @BindView(R.id.currentPersonOfLeaveEarlyTextView)
    TextView currentPersonOfLeaveEarlyTextView;
    @BindView(R.id.currentOverallPersonOfLeaveEarlyTextView)
    TextView currentOverallPersonOfLeaveEarlyTextView;
    @BindView(R.id.currentPersonOfAbnormalTextView)
    TextView currentPersonOfAbnormalTextView;
    @BindView(R.id.currentOverallPersonOfAbnormalTextView)
    TextView currentOverallPersonOfAbnormalTextView;

    @BindView(R.id.loadingProgressBar)
    LinearLayout loadingProgressBar;

    private List<Fragment> attendanceStatisticsFragments;
    private List<Fragment> studentsWithAttendanceProblemsFragments;

    private String[] attendanceStatisticsTitles;
    private String[] studentsWithAttendanceProblemsTitles;


    public static AttendanceStatisticsFragment newInstance() {

        Bundle args = new Bundle();

        AttendanceStatisticsFragment fragment = new AttendanceStatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void initView(View view) {

        initTabLayoutView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attendance_statistics;
    }

    @Override
    public void showAttendanceProfile(AttendanceProfileBean bean) {

        setAttendanceProfileText(bean);
        CircleBarViewUtil.setAttendanceCircleBarView(circleBarView, bean.getCurrent_attendance(), attendanceStatisticsProgressText, 3000);
    }

    @Override
    public void showOverallAttendanceLineChart(BaseArrayBean<TimeAndNumberOfPeopleBean> bean) {

        BaseChartView chartView = (BaseChartView) attendanceStatisticsFragments.get(0);
        chartView.setChartData(bean.getArrayList(), LineChartView.OVERALL_ATTENDANCE_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showClassAttendanceHorizontalBarChart(BaseArrayBean<ClassAndPercentageBean> bean) {

        BaseChartView chartView = (BaseChartView) attendanceStatisticsFragments.get(1);
        chartView.setChartData(bean.getArrayList(), HorizontalBarChartView.CLASS_ATTENDANCE_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showProblemStudentList(StudentsWithAttendanceProblemsBean bean) {

        StudentsWithAttendanceProblemsFragment fragment;
        fragment = (StudentsWithAttendanceProblemsFragment)studentsWithAttendanceProblemsFragments.get(0);
        fragment.initRecyclerView(bean.getEarly());
        fragment = (StudentsWithAttendanceProblemsFragment)studentsWithAttendanceProblemsFragments.get(1);
        fragment.initRecyclerView(bean.getAbsent());
        fragment = (StudentsWithAttendanceProblemsFragment)studentsWithAttendanceProblemsFragments.get(2);
        fragment.initRecyclerView(bean.getEarly());
        fragment = (StudentsWithAttendanceProblemsFragment)studentsWithAttendanceProblemsFragments.get(3);
        fragment.initRecyclerView(bean.getQingjia());
    }

    @Override
    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void initTabLayoutView() {

        initTitles();
        initFragments();
        initTabLayout();
    }

    @Override
    public void initTitles(){

        Resources resources = Objects.requireNonNull(getActivity()).getResources();
        attendanceStatisticsTitles = resources.getStringArray(R.array.attendance_statistics_titles);
        studentsWithAttendanceProblemsTitles = resources.getStringArray(R.array.attendance_statistics_details_titles);
    }

    @Override
    public void initFragments(){

        attendanceStatisticsFragments = new ArrayList<>();
        attendanceStatisticsFragments.add(LineChartView.newInstance());
        attendanceStatisticsFragments.add(HorizontalBarChartView.newInstance());

        studentsWithAttendanceProblemsFragments = new ArrayList<>();
        for(int i = 0; i < studentsWithAttendanceProblemsTitles.length; i++){
            studentsWithAttendanceProblemsFragments.add(StudentsWithAttendanceProblemsFragment.newInstance());
        }
    }

    @Override
    public void initViewPagerAdapter(){

        TabFragmentPagerAdapter attendanceStatisticsAdapter = new TabFragmentPagerAdapter(getFragmentManager(), attendanceStatisticsFragments);
        attendanceStatisticsViewPager.setAdapter(attendanceStatisticsAdapter);

        TabFragmentPagerAdapter attendanceStatisticsDetailsAdapter = new TabFragmentPagerAdapter(getFragmentManager(), studentsWithAttendanceProblemsFragments);
        studentsWithAttendanceProblemsViewPager.setAdapter(attendanceStatisticsDetailsAdapter);
        studentsWithAttendanceProblemsViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void initTabLayout(){

        initViewPagerAdapter();
        attendanceStatisticsTabLayout.setupWithViewPager(attendanceStatisticsViewPager);
        for(int i = 0; i < attendanceStatisticsTitles.length; i++){
            Objects.requireNonNull(attendanceStatisticsTabLayout.getTabAt(i)).setText(attendanceStatisticsTitles[i]);
        }

        studentsWithAttendanceProblemsTabLayout.setupWithViewPager(studentsWithAttendanceProblemsViewPager);
        for(int i = 0; i < studentsWithAttendanceProblemsTitles.length; i++){
            Objects.requireNonNull(studentsWithAttendanceProblemsTabLayout.getTabAt(i)).setText(studentsWithAttendanceProblemsTitles[i]);
        }
    }

    private void setAttendanceProfileText(AttendanceProfileBean bean){

        String currentStudents = String.valueOf(bean.getTotal_students());
        currentOverallPersonOfAbnormalTextView.setText(currentStudents);
        currentOverallPersonOfAbsenteeTextView.setText(currentStudents);
        currentOverallPersonOfLateTextView.setText(currentStudents);
        currentOverallPersonOfLeaveEarlyTextView.setText(currentStudents);

        currentPersonOfLateTextView.setText(formatAttendanceProfileText(bean.getLate()));
        currentPersonOfAbsenteeTextView.setText(formatAttendanceProfileText(bean.getAbsent()));
        currentPersonOfLeaveEarlyTextView.setText(formatAttendanceProfileText(bean.getEarly()));
        currentPersonOfAbnormalTextView.setText(formatAttendanceProfileText(bean.getQingjia()));
    }

    private String formatAttendanceProfileText(int num){
        return String.valueOf(num) + "人";
    }
}
