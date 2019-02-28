package com.example.smartclass.view;

import android.content.res.Resources;
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
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.adapter.TabFragmentPagerAdapter;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.base.BaseTabLayoutView;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ConcentrationDistributionBean;
import com.example.smartclass.bean.TimeAndConcentrationBean;
import com.example.smartclass.contract.StudentStatusContract;
import com.example.smartclass.presenter.StudentStatusPresenter;
import com.example.smartclass.util.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class StudentStatusFragment extends BaseMvpFragment<StudentStatusPresenter> implements StudentStatusContract.View, BaseTabLayoutView {

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

    private List<Fragment> studentStatusFragments;
    private List<Fragment> concentrationDistributionFragments;
    private List<Fragment> unfocusedStudentDetailsFragments;

    private String[] studentStatusTitles;
    private String[] concentrationDistributionTitles;
    private String[] unfocusedStudentDetailsTitles;

    public static StudentStatusFragment newInstance() {

        Bundle args = new Bundle();

        StudentStatusFragment fragment = new StudentStatusFragment();
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
        return R.layout.fragment_student_status;
    }

    @Override
    public void showCurrentStatusScatterChart() {

    }

    @Override
    public void showStateChangeLineChart(BaseArrayBean<TimeAndConcentrationBean> bean) {
        Toast.makeText(getActivity(), bean.getArrayList().get(0).getTime(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showConcentrationDistributionPieChart(ConcentrationDistributionBean bean) {

        BaseChartView focusChartView = (BaseChartView) concentrationDistributionFragments.get(0);
        focusChartView.setChartData(bean.getFocus(), ConcentrationDistributionFragment.CONCENTRATION_DISTRIBUTION);
        focusChartView.initChartView();

        BaseChartView unfocusedChartView = (BaseChartView) concentrationDistributionFragments.get(1);
        unfocusedChartView.setChartData(bean.getUnfocus(), ConcentrationDistributionFragment.CONCENTRATION_DISTRIBUTION);
        unfocusedChartView.initChartView();
    }

    @Override
    public void showUnfocusedDistributionPieChart() {

    }

    @Override
    public void showUnfocusedStudentList() {

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

    @Override
    public void initTitles(){

        Resources resources = Objects.requireNonNull(getActivity()).getResources();
        studentStatusTitles = resources.getStringArray(R.array.student_status_titles);
        concentrationDistributionTitles = resources.getStringArray(R.array.concentration_distribution_titles);
        unfocusedStudentDetailsTitles = resources.getStringArray(R.array.unfocused_student_details_titles);
    }

    @Override
    public void initFragments(){

        studentStatusFragments = new ArrayList<>();
        studentStatusFragments.add(CurrentStateFragment.newInstance());
        studentStatusFragments.add(StateChangeFragment.newInstance());

        concentrationDistributionFragments = new ArrayList<>();
        for(int i = 0; i < concentrationDistributionTitles.length; i++){
            concentrationDistributionFragments.add(ConcentrationDistributionFragment.newInstance());
        }
//        fragments3.add(UnfocusedDistributionFragment.newInstance());

        unfocusedStudentDetailsFragments = new ArrayList<>();
        for(int i = 0; i < unfocusedStudentDetailsTitles.length; i++){
            unfocusedStudentDetailsFragments.add(UnfocusedStudentDetailsFragment.newInstance());
        }
    }

    @Override
    public void initViewPagerAdapter(){

        TabFragmentPagerAdapter studentStatusStatisticsAdapter = new TabFragmentPagerAdapter(getFragmentManager(), studentStatusFragments);
        studentStatusStatisticsViewPager.setAdapter(studentStatusStatisticsAdapter);

        TabFragmentPagerAdapter concentrationDistributionAdapter = new TabFragmentPagerAdapter(getFragmentManager(), concentrationDistributionFragments);
        concentrationDistributionViewPager.setAdapter(concentrationDistributionAdapter);

        TabFragmentPagerAdapter unfocusedStudentStatisticsAdapter = new TabFragmentPagerAdapter(getFragmentManager(), unfocusedStudentDetailsFragments);
        unfocusedStudentStatisticsViewPager.setAdapter(unfocusedStudentStatisticsAdapter);

    }

    @Override
    public void initTabLayout(){

        initViewPagerAdapter();
        studentStatusStatisticsTabLayout.setupWithViewPager(studentStatusStatisticsViewPager);
        for(int i = 0; i < studentStatusTitles.length; i++){
            Objects.requireNonNull(studentStatusStatisticsTabLayout.getTabAt(i)).setText(studentStatusTitles[i]);
        }

        concentrationDistributionTabLayout.setupWithViewPager(concentrationDistributionViewPager);
        for(int i = 0; i < concentrationDistributionTitles.length; i++){
            Objects.requireNonNull(concentrationDistributionTabLayout.getTabAt(i)).setText(concentrationDistributionTitles[i]);
        }

        unfocusedStudentStatisticsTabLayout.setupWithViewPager(unfocusedStudentStatisticsViewPager);
        for(int i = 0; i < unfocusedStudentDetailsTitles.length; i++){
            Objects.requireNonNull(unfocusedStudentStatisticsTabLayout.getTabAt(i)).setText(unfocusedStudentDetailsTitles[i]);
        }
    }
}
