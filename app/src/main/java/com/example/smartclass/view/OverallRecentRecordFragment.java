package com.example.smartclass.view;

import android.content.res.Resources;
import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import com.example.smartclass.bean.ClassInfoAboutTimeAndRelatedInfoBean;
import com.example.smartclass.bean.ClassRankingBean;
import com.example.smartclass.bean.DateAndPercentageBean;
import com.example.smartclass.contract.OverallRecentRecordContract;
import com.example.smartclass.presenter.OverallRecentRecordPresenter;
import com.example.smartclass.util.CircleBarView;
import com.example.smartclass.util.CircleBarViewUtil;
import com.example.smartclass.util.NoScrollViewPager;
import com.example.smartclass.util.TabLayoutUnderLineChangeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import butterknife.BindView;

/**
 * Created by YangFan
 * On 2019/2/17
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class OverallRecentRecordFragment extends BaseMvpFragment<OverallRecentRecordPresenter> implements OverallRecentRecordContract.View, BaseTabLayoutView {

    @BindView(R.id.overallClassroomInformationRankingTabLayout)
    TabLayout overallClassroomInformationRankingTabLayout;
    @BindView(R.id.overallClassroomInformationTabLayout)
    TabLayout overallClassroomInformationTabLayout;
    @BindView(R.id.overallClassroomInformationRankingViewPager)
    NoScrollViewPager overallClassroomInformationRankingViewPager;
    @BindView(R.id.overallClassroomInformationViewPager)
    NoScrollViewPager overallClassroomInformationViewPager;
    @BindView(R.id.overallRecentRecordCircleProgressBar)
    CircleBarView circleBarView;
    @BindView(R.id.overallRecentRecordProgressText)
    TextView overallRecentRecordProgressText;


    @BindView(R.id.recentPersonOfLateTextView)
    TextView recentPersonOfLateTextView;
    @BindView(R.id.recentOverallPersonOfLateTextView)
    TextView recentOverallPersonOfLateTextView;
    @BindView(R.id.recentPersonOfAbsenteeTextView)
    TextView recentPersonOfAbsenteeTextView;
    @BindView(R.id.recentOverallPersonOfAbsenteeTextView)
    TextView recentOverallPersonOfAbsenteeTextView;
    @BindView(R.id.recentPersonOfLeaveEarlyTextView)
    TextView recentPersonOfLeaveEarlyTextView;
    @BindView(R.id.recentOverallPersonOfLeaveEarlyTextView)
    TextView recentOverallPersonOfLeaveEarlyTextView;
    @BindView(R.id.recentPersonOfAbnormalTextView)
    TextView recentPersonOfAbnormalTextView;
    @BindView(R.id.recentOverallPersonOfAbnormalTextView)
    TextView recentOverallPersonOfAbnormalTextView;

    @BindView(R.id.overallRecentRecordLoadingProgressBar)
    LinearLayout loadingProgressBar;

    @BindView(R.id.leftOfFirstTabOfOverallRecentRecord)
    View leftOfFirstTabOfOverallRecentRecord;
    @BindView(R.id.rightOfFirstTabOfOverallRecentRecord)
    View rightOfFirstTabOfOverallRecentRecord;
    @BindView(R.id.leftOfSecondTabOfOverallRecentRecord)
    View leftOfSecondTabOfOverallRecentRecord;
    @BindView(R.id.rightOfSecondTabOfOverallRecentRecord)
    View rightOfSecondTabOfOverallRecentRecord;

    private List<Fragment> studentStatusFragments;
    private List<Fragment> studentStatusRankingFragments;

    private String[] studentStatusTitles;
    private String[] studentStatusRankingTitles;

    public static OverallRecentRecordFragment newInstance() {

        Bundle args = new Bundle();

        OverallRecentRecordFragment fragment = new OverallRecentRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null){
            mPresenter.subscribe();
        }
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
        return R.layout.fragment_overall_recent_record;
    }

    @Override
    public void showAttendanceProfile(AttendanceProfileBean bean) {

        setAttendanceProfileText(bean);
        CircleBarViewUtil.setAttendanceCircleBarView(circleBarView, bean.getCurrent_attendance(), overallRecentRecordProgressText, 3000);
    }

    @Override
    public void showAttendanceLineChart(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean) {

        BaseChartView chartView = (BaseChartView) studentStatusFragments.get(0);
        chartView.setChartData(bean.getArrayList(), LineChartView.RECENT_OVERALL_ATTENDANCE_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showClassStatusLineChart(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean) {

        BaseChartView chartView = (BaseChartView) studentStatusFragments.get(1);
        chartView.setChartData(bean.getArrayList(), LineChartView.RECENT_OVERALL_CLASS_STATUS_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showClassRankingBarChart(ClassRankingBean bean) {

        BaseChartView chartView = (BaseChartView) studentStatusRankingFragments.get(0);
        chartView.setChartData(bean.getAttendance(), BarChartView.RECENT_OVERALL_ATTENDANCE_RANKING_STATISTICS);
        chartView.initChartView();

        chartView = (BaseChartView) studentStatusRankingFragments.get(1);
        chartView.setChartData(bean.getFocus(), BarChartView.RECENT_OVERALL_STUDENT_STATUS_RANKING_STATISTICS);
        chartView.initChartView();
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
        studentStatusTitles = resources.getStringArray(R.array.overall_student_recent_status_titles);
        studentStatusRankingTitles = resources.getStringArray(R.array.overall_student_recent_status_ranking_titles);
    }

    @Override
    public void initFragments(){

        studentStatusFragments = new ArrayList<>();
        for(int i = 0; i < studentStatusTitles.length; i++){
            studentStatusFragments.add(LineChartView.newInstance());
        }

        studentStatusRankingFragments = new ArrayList<>();
        for(int i = 0; i < studentStatusRankingTitles.length; i++){
            studentStatusRankingFragments.add(BarChartView.newInstance());
        }
    }

    @Override
    public void initViewPagerAdapter(){

        TabFragmentPagerAdapter studentStatusAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(), studentStatusFragments);
        overallClassroomInformationViewPager.setAdapter(studentStatusAdapter);

        TabFragmentPagerAdapter studentStatusRankingAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(), studentStatusRankingFragments);
        overallClassroomInformationRankingViewPager.setAdapter(studentStatusRankingAdapter);
    }

    @Override
    public void initTabLayout(){

        initViewPagerAdapter();
        overallClassroomInformationTabLayout.setupWithViewPager(overallClassroomInformationViewPager);
        for(int i = 0; i < studentStatusTitles.length; i++){
            Objects.requireNonNull(overallClassroomInformationTabLayout.getTabAt(i)).setText(studentStatusTitles[i]);
        }

        overallClassroomInformationRankingTabLayout.setupWithViewPager(overallClassroomInformationRankingViewPager);
        for(int i = 0; i < studentStatusRankingTitles.length; i++){
            Objects.requireNonNull(overallClassroomInformationRankingTabLayout.getTabAt(i)).setText(studentStatusRankingTitles[i]);
        }
        setTabLayoutOnClickListener();
    }

    private void setTabLayoutOnClickListener(){

        List<View> firstTabLayout = new ArrayList<>();
        firstTabLayout.add(leftOfFirstTabOfOverallRecentRecord);
        firstTabLayout.add(rightOfFirstTabOfOverallRecentRecord);

        List<View> secondTabLayout = new ArrayList<>();
        secondTabLayout.add(leftOfSecondTabOfOverallRecentRecord);
        secondTabLayout.add(rightOfSecondTabOfOverallRecentRecord);

        TabLayoutUnderLineChangeUtil.changeTabLayoutUnderLineView(overallClassroomInformationTabLayout, firstTabLayout);
        TabLayoutUnderLineChangeUtil.changeTabLayoutUnderLineView(overallClassroomInformationRankingTabLayout, secondTabLayout);
    }

    private void setAttendanceProfileText(AttendanceProfileBean bean){

        String recentStudents = String.valueOf(bean.getTotal_students());
        recentOverallPersonOfAbnormalTextView.setText(recentStudents);
        recentOverallPersonOfAbsenteeTextView.setText(recentStudents);
        recentOverallPersonOfLateTextView.setText(recentStudents);
        recentOverallPersonOfLeaveEarlyTextView.setText(recentStudents);

        recentPersonOfLateTextView.setText(formatAttendanceProfileText(bean.getLate()));
        recentPersonOfAbsenteeTextView.setText(formatAttendanceProfileText(bean.getAbsent()));
        recentPersonOfLeaveEarlyTextView.setText(formatAttendanceProfileText(bean.getEarly()));
        recentPersonOfAbnormalTextView.setText(formatAttendanceProfileText(bean.getQingjia()));
    }

    private String formatAttendanceProfileText(int num){
        return String.valueOf(num) + "人";
    }
}
