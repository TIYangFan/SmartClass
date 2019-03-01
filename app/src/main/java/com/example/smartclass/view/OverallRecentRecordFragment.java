package com.example.smartclass.view;

import android.content.res.Resources;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

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
    ViewPager overallClassroomInformationRankingViewPager;
    @BindView(R.id.overallClassroomInformationViewPager)
    ViewPager overallClassroomInformationViewPager;
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
        return R.layout.fragment_overall_recent_record;
    }

    @Override
    public void showAttendanceProfile(AttendanceProfileBean bean) {

        setAttendanceProfileText(bean);
        setAttendanceCircleBarView(bean.getCurrent_attendance());
    }

    @Override
    public void showAttendanceLineChart(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean) {

        BaseChartView chartView = (BaseChartView) studentStatusFragments.get(0);
        chartView.setChartData(bean.getArrayList(), StateChangeFragment.RECENT_OVERALL_ATTENDANCE_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showClassStatusLineChart(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean) {

        BaseChartView chartView = (BaseChartView) studentStatusFragments.get(1);
        chartView.setChartData(bean.getArrayList(), StateChangeFragment.RECENT_OVERALL_CLASS_STATUS_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showClassRankingBarChart(ClassRankingBean bean) {

        BaseChartView chartView = (BaseChartView) studentStatusRankingFragments.get(0);
        chartView.setChartData(bean.getAttendance(), RecentOverallStudentStatusRankingsFragment.RECENT_OVERALL_ATTENDANCE_RANKING_STATISTICS);
        chartView.initChartView();

        chartView = (BaseChartView) studentStatusRankingFragments.get(1);
        chartView.setChartData(bean.getFocus(), RecentOverallStudentStatusRankingsFragment.RECENT_OVERALL_STUDENT_STATUS_RANKING_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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

    /**
     * 初始化各部分标题
     */
    @Override
    public void initTitles(){

        Resources resources = Objects.requireNonNull(getActivity()).getResources();
        studentStatusTitles = resources.getStringArray(R.array.overall_student_recent_status_titles);
        studentStatusRankingTitles = resources.getStringArray(R.array.overall_student_recent_status_ranking_titles);
    }

    /**
     * 初始化图表部分的 fragment
     */
    @Override
    public void initFragments(){

        studentStatusFragments = new ArrayList<>();
        for(int i = 0; i < studentStatusTitles.length; i++){
            studentStatusFragments.add(StateChangeFragment.newInstance());
        }

        studentStatusRankingFragments = new ArrayList<>();
        for(int i = 0; i < studentStatusRankingTitles.length; i++){
            studentStatusRankingFragments.add(RecentOverallStudentStatusRankingsFragment.newInstance());
        }


        //        fragments1.add(RecentOverallAttendanceFragment.newInstance());
//        fragments2.add(RecentOverallAttendanceRankingsFragment.newInstance());

    }

    /**
     * 初始化 ViewPagerAdapter
     */
    @Override
    public void initViewPagerAdapter(){

        TabFragmentPagerAdapter studentStatusAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(), studentStatusFragments);
        overallClassroomInformationViewPager.setAdapter(studentStatusAdapter);

        TabFragmentPagerAdapter studentStatusRankingAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(), studentStatusRankingFragments);
        overallClassroomInformationRankingViewPager.setAdapter(studentStatusRankingAdapter);
    }

    /**
     * 初始化 TabLayout
     */
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
    }

    /**
     * 设置当前课堂出勤统计的出勤概况
     * @param bean 出勤概况
     */
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

    /**
     * 设置出勤率环状进度条
     * @param currentAttendance 当前出勤率
     */
    private void setAttendanceCircleBarView(float currentAttendance){

        circleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String howToChangeText(float interpolatedTime, float progressNum, float maxNum) {
                DecimalFormat decimalFormat = new DecimalFormat("0.0");
                String s = decimalFormat.format(interpolatedTime * progressNum / maxNum * 100) + "%";
                return s;
            }

            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float updateNum, float maxNum) {

            }
        });

        circleBarView.setTextView(overallRecentRecordProgressText);
        circleBarView.setProgressNum(currentAttendance * 100f,3000);
    }

    /**
     * 规范化出勤概况数据
     * @param num 未规范的出勤概况数据
     * @return 规范后的出勤概况
     */
    private String formatAttendanceProfileText(int num){
        return String.valueOf(num) + "人";
    }
}
