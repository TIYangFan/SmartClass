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
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.adapter.TabFragmentPagerAdapter;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.base.BaseTabLayoutView;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassAndPercentBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.contract.AttendanceStatisticsContract;
import com.example.smartclass.presenter.AttendanceStatisticsPresenter;
import com.example.smartclass.util.CircleBarView;
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
    private String[] attendanceStatisticsDetailsTitles;


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
        setAttendanceCircleBarView(bean.getCurrent_attendance());
    }

    @Override
    public void showOverallAttendanceLineChart(BaseArrayBean<TimeAndNumberOfPeopleBean> bean) {

        BaseChartView chartView = (BaseChartView) attendanceStatisticsFragments.get(0);
        chartView.setChartData(bean.getArrayList(), StateChangeFragment.OVERALL_ATTENDANCE_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showClassAttendanceHorizontalBarChart(BaseArrayBean<ClassAndPercentBean> bean) {

        BaseChartView chartView = (BaseChartView) attendanceStatisticsFragments.get(1);
        chartView.setChartData(bean.getArrayList(), ClassAttendanceStatisticsFragment.CLASS_ATTENDANCE_STATISTICS);
        chartView.initChartView();
    }

    @Override
    public void showProblemStudentList(StudentsWithAttendanceProblemsBean bean) {

        StudentsWithAttendanceProblemsFragment fragment;
        fragment = (StudentsWithAttendanceProblemsFragment)studentsWithAttendanceProblemsFragments.get(0);
        fragment.setListData(bean.getEarly());
        fragment = (StudentsWithAttendanceProblemsFragment)studentsWithAttendanceProblemsFragments.get(1);
        fragment.setListData(bean.getAbsent());
        fragment = (StudentsWithAttendanceProblemsFragment)studentsWithAttendanceProblemsFragments.get(2);
        fragment.setListData(bean.getEarly());
        fragment = (StudentsWithAttendanceProblemsFragment)studentsWithAttendanceProblemsFragments.get(3);
        fragment.setListData(bean.getQingjia());
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

    /**
     * 初始化各部分标题
     */
    public void initTitles(){

        Resources resources = Objects.requireNonNull(getActivity()).getResources();
        attendanceStatisticsTitles = resources.getStringArray(R.array.attendance_statistics_titles);
        attendanceStatisticsDetailsTitles = resources.getStringArray(R.array.attendance_statistics_details_titles);
    }

    /**
     * 初始化图表部分的 fragment
     */
    public void initFragments(){

        attendanceStatisticsFragments = new ArrayList<>();
//        mFragment1.add(OverallAttendanceStatisticsFragment.newInstance());
        attendanceStatisticsFragments.add(StateChangeFragment.newInstance());
        attendanceStatisticsFragments.add(ClassAttendanceStatisticsFragment.newInstance());

        studentsWithAttendanceProblemsFragments = new ArrayList<>();
        for(int i = 0; i < attendanceStatisticsDetailsTitles.length; i++){
            studentsWithAttendanceProblemsFragments.add(StudentsWithAttendanceProblemsFragment.newInstance());
        }
    }

    /**
     * 初始化 ViewPagerAdapter
     */
    public void initViewPagerAdapter(){

        TabFragmentPagerAdapter attendanceStatisticsAdapter = new TabFragmentPagerAdapter(getFragmentManager(), attendanceStatisticsFragments);
        attendanceStatisticsViewPager.setAdapter(attendanceStatisticsAdapter);

        TabFragmentPagerAdapter attendanceStatisticsDetailsAdapter = new TabFragmentPagerAdapter(getFragmentManager(), studentsWithAttendanceProblemsFragments);
        studentsWithAttendanceProblemsViewPager.setAdapter(attendanceStatisticsDetailsAdapter);
    }

    /**
     * 初始化 TabLayout
     */
    public void initTabLayout(){

        initViewPagerAdapter();
        attendanceStatisticsTabLayout.setupWithViewPager(attendanceStatisticsViewPager);
        for(int i = 0; i < attendanceStatisticsTitles.length; i++){
            Objects.requireNonNull(attendanceStatisticsTabLayout.getTabAt(i)).setText(attendanceStatisticsTitles[i]);
        }

        studentsWithAttendanceProblemsTabLayout.setupWithViewPager(studentsWithAttendanceProblemsViewPager);
        for(int i = 0; i < attendanceStatisticsDetailsTitles.length; i++){
            Objects.requireNonNull(studentsWithAttendanceProblemsTabLayout.getTabAt(i)).setText(attendanceStatisticsDetailsTitles[i]);
        }
    }


    /**
     * 设置当前课堂出勤统计的出勤概况
     * @param bean 出勤概况
     */
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

        circleBarView.setTextView(attendanceStatisticsProgressText);
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
