package com.example.smartclass.adapter;

import android.content.res.Resources;
import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseTabLayoutView;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.contract.ClassRecentRecordContract;
import com.example.smartclass.util.CircleBarView;
import com.example.smartclass.util.WrapContentHeightViewPager;
import com.example.smartclass.view.RecentOverallStudentStatusRankingsFragment;
import com.example.smartclass.view.StateChangeFragment;
import com.example.smartclass.view.StudentsWithAttendanceProblemsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/31
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassRecentRecordExpandableListAdapter extends BaseExpandableListAdapter {

    private String[] groupString = {"2017级/设计/1班", "2017级/设计/2班", "2017级/设计/3班", "2017级/设计/3班"};
    private String[][] childString = {
            {"孙尚香"},
            {"孙膑"},
            {"张飞"},
            {"张飞"},
    };

    private Fragment parentFragment;
    private GroupViewHolder groupViewHolder;
    private ChildViewHolder childViewHolder;

    public ClassRecentRecordExpandableListAdapter(Fragment fragment){
        parentFragment = fragment;
    }

    @Override
    public int getGroupCount() {
        return groupString.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childString[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupString[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childString[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_list_view_group_class,
                    parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.expandableListItemTitle.setText(groupString[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_list_view_child_class,
                    parent, false);
            childViewHolder = new ChildViewHolder(convertView, parentFragment);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder)convertView.getTag();
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void showClassInformation() {

        groupViewHolder.showClassInformation();
    }

    public void showAttendanceProfile() {

    }

    public void showAttendanceLineChart() {

        childViewHolder.showAttendanceLineChart();
    }

    public void showClassStatusLineChart() {

        childViewHolder.showClassStatusLineChart();
    }

    public void showProblemStudentsList() {

        childViewHolder.showProblemStudentsList();
    }

    static class GroupViewHolder{

        @BindView(R.id.expandableListItemTitle)
        TextView expandableListItemTitle;
        @BindView(R.id.classRecentRecordCircleProgressBar)
        CircleBarView circleBarView;
        @BindView(R.id.classRecentRecordProgressText)
        TextView classRecentRecordProgressText;


        @BindView(R.id.recentRecordPersonOfLateTextView)
        TextView recentRecordPersonOfLateTextView;
        @BindView(R.id.recentRecordOverallPersonOfLateTextView)
        TextView recentRecordOverallPersonOfLateTextView;
        @BindView(R.id.recentRecordPersonOfAbsenteeTextView)
        TextView recentRecordPersonOfAbsenteeTextView;
        @BindView(R.id.recentRecordOverallPersonOfAbsenteeTextView)
        TextView recentRecordOverallPersonOfAbsenteeTextView;
        @BindView(R.id.recentRecordPersonOfLeaveEarlyTextView)
        TextView recentRecordPersonOfLeaveEarlyTextView;
        @BindView(R.id.recentRecordOverallPersonOfLeaveEarlyTextView)
        TextView recentRecordOverallPersonOfLeaveEarlyTextView;
        @BindView(R.id.recentRecordPersonOfAbnormalTextView)
        TextView recentRecordPersonOfAbnormalTextView;
        @BindView(R.id.recentRecordOverallPersonOfAbnormalTextView)
        TextView recentRecordOverallPersonOfAbnormalTextView;

        GroupViewHolder(View view){
            ButterKnife.bind(this, view);
        }

        public void showClassInformation() {

        }

        public void showAttendanceProfile(AttendanceProfileBean bean) {

            setAttendanceProfileText(bean);
            setAttendanceCircleBarView(bean.getCurrent_attendance());
        }

        /**
         * 设置当前课堂出勤统计的出勤概况
         * @param bean 出勤概况
         */
        private void setAttendanceProfileText(AttendanceProfileBean bean){

            String currentStudents = String.valueOf(bean.getTotal_students());
            recentRecordOverallPersonOfAbnormalTextView.setText(currentStudents);
            recentRecordOverallPersonOfAbsenteeTextView.setText(currentStudents);
            recentRecordOverallPersonOfLateTextView.setText(currentStudents);
            recentRecordOverallPersonOfLeaveEarlyTextView.setText(currentStudents);

            recentRecordPersonOfLateTextView.setText(formatAttendanceProfileText(bean.getLate()));
            recentRecordPersonOfAbsenteeTextView.setText(formatAttendanceProfileText(bean.getAbsent()));
            recentRecordPersonOfLeaveEarlyTextView.setText(formatAttendanceProfileText(bean.getEarly()));
            recentRecordPersonOfAbnormalTextView.setText(formatAttendanceProfileText(bean.getQingjia()));
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

            circleBarView.setTextView(classRecentRecordProgressText);
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

    static class ChildViewHolder implements BaseTabLayoutView {

        @BindView(R.id.recentRecordStatisticsTabLayout)
        TabLayout recentRecordStatisticsTabLayout;
        @BindView(R.id.recentRecordStatisticsDetailTabLayout)
        TabLayout recentRecordStatisticsDetailTabLayout;
        @BindView(R.id.recentRecordStatisticsViewPager)
        ViewPager recentRecordStatisticsViewPager;
        @BindView(R.id.recentRecordStatisticsDetailViewPager)
        WrapContentHeightViewPager recentRecordStatisticsDetailViewPager;

        private List<Fragment> studentStatusFragments;
        private List<Fragment> studentsWithAttendanceProblemsFragments;

        private Fragment parentFragment;

        private String[] studentStatusTitles;
        private String[] studentsWithAttendanceProblemsTitles;

        ChildViewHolder(View view, Fragment fragment){
            ButterKnife.bind(this, view);
            parentFragment = fragment;
            initView();
        }

        private void initView(){

            initTabLayoutView();
        }

        public void showLoading() {

        }

        public void hideLoading() {

        }

        public void onError(Throwable throwable) {

        }

        @Override
        public void initTabLayoutView() {

            initTitles();
            initFragments();
            initTabLayout();
        }

        @Override
        public void initTitles() {

            Resources resources = parentFragment.getResources();
            studentStatusTitles = resources.getStringArray(R.array.overall_student_recent_status_titles);
            studentsWithAttendanceProblemsTitles = resources.getStringArray(R.array.overall_student_recent_status_ranking_titles);
        }

        @Override
        public void initFragments() {

            studentStatusFragments = new ArrayList<>();
            for(int i = 0; i < studentStatusTitles.length; i++){
                studentStatusFragments.add(StateChangeFragment.newInstance());
            }

            studentsWithAttendanceProblemsFragments = new ArrayList<>();
            for(int i = 0; i < studentsWithAttendanceProblemsTitles.length; i++){
                studentsWithAttendanceProblemsFragments.add(StudentsWithAttendanceProblemsFragment.newInstance());
            }
            //studentStatusFragments.add(RecentOverallStudentStatusRankingsFragment.newInstance());
        }

        @Override
        public void initViewPagerAdapter() {

            TabFragmentPagerAdapter recentRecordStatisticsAdapter = new TabFragmentPagerAdapter(parentFragment.getFragmentManager(), studentStatusFragments);
            recentRecordStatisticsViewPager.setAdapter(recentRecordStatisticsAdapter);


            TabFragmentPagerAdapter recentRecordStatisticsDetailAdapter = new TabFragmentPagerAdapter(parentFragment.getFragmentManager(),
                    studentsWithAttendanceProblemsFragments);
            recentRecordStatisticsDetailViewPager.setAdapter(recentRecordStatisticsDetailAdapter);
            recentRecordStatisticsDetailViewPager.setOffscreenPageLimit(3);
        }

        @Override
        public void initTabLayout() {

            initViewPagerAdapter();
            recentRecordStatisticsTabLayout.setupWithViewPager(recentRecordStatisticsViewPager);
            for(int i = 0; i < studentStatusTitles.length; i++){
                Objects.requireNonNull(recentRecordStatisticsTabLayout.getTabAt(i)).setText(studentStatusTitles[i]);
            }

            recentRecordStatisticsDetailTabLayout.setupWithViewPager(recentRecordStatisticsDetailViewPager);
            for(int i  = 0; i < studentsWithAttendanceProblemsTitles.length; i++){
                Objects.requireNonNull(recentRecordStatisticsDetailTabLayout.getTabAt(i)).setText(studentsWithAttendanceProblemsTitles[i]);
            }
        }

        public void showAttendanceLineChart() {

        }

        public void showClassStatusLineChart() {

        }

        public void showProblemStudentsList() {

        }
    }
}
