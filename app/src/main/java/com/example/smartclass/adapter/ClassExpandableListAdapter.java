package com.example.smartclass.adapter;

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
import com.example.smartclass.util.CircleBarView;
import com.example.smartclass.util.WrapContentHeightViewPager;
import com.example.smartclass.view.ClassAttendanceStatisticsFragment;
import com.example.smartclass.view.RecentOverallStudentStatusRankingsFragment;
import com.example.smartclass.view.StateChangeFragment;
import com.example.smartclass.view.UnfocusedDetailFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/31
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassExpandableListAdapter extends BaseExpandableListAdapter {

    private String[] groupString = {"2017级/设计/1班", "2017级/设计/2班", "2017级/设计/3班", "2017级/设计/3班"};
    private String[][] childString = {
            {"孙尚香"},
            {"孙膑"},
            {"张飞"},
            {"张飞"},
    };

    private Fragment fragment;

    public ClassExpandableListAdapter(Fragment fragment){
        this.fragment = fragment;
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

        GroupViewHolder groupViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_list_view_group_class,
                    parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.textView.setText(groupString[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_list_view_child_class,
                    parent, false);
            childViewHolder = new ChildViewHolder(convertView, fragment);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder)convertView.getTag();
        }
        childViewHolder.textView.setText(childString[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder{
        @BindView(R.id.parent)
        TextView textView;
        @BindView(R.id.classRecentRecordCircleProgressBar)
        CircleBarView circleBarView;
        @BindView(R.id.classRecentRecordProgressText)
        TextView classRecentRecordProgressText;

        GroupViewHolder(View view){
            ButterKnife.bind(this, view);

            initView();
        }

        private void initView(){
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
            circleBarView.setTextView(classRecentRecordProgressText);
            circleBarView.setProgressNum(80,3000);
        }
    }

    static class ChildViewHolder{
        @BindView(R.id.child)
        TextView textView;
        @BindView(R.id.recentRecordStatisticsTabLayout)
        TabLayout recentRecordStatisticsTabLayout;
        @BindView(R.id.recentRecordStatisticsDetailTabLayout)
        TabLayout recentRecordStatisticsDetailTabLayout;
        @BindView(R.id.recentRecordStatisticsViewPager)
        ViewPager recentRecordStatisticsViewPager;
        @BindView(R.id.recentRecordStatisticsDetailViewPager)
        WrapContentHeightViewPager recentRecordStatisticsDetailViewPager;

        private List<Fragment> fragments1;
        private List<Fragment> fragments2;

        private Fragment fragment;

        private String[] title1 = {"出勤情况", "课堂状态"};
        private String[] title2 = {"迟到", "旷课", "早退", "请假"};

        ChildViewHolder(View view, Fragment fragment){
            ButterKnife.bind(this, view);
            this.fragment = fragment;
            initView();
        }

        private void initView(){
            initFragment();

            TabFragmentPagerAdapter adapter1 = new TabFragmentPagerAdapter(fragment.getFragmentManager(), fragments1);
            recentRecordStatisticsViewPager.setAdapter(adapter1);
            recentRecordStatisticsTabLayout.setupWithViewPager(recentRecordStatisticsViewPager);
            for(int i = 0; i < title1.length; i++){
                recentRecordStatisticsTabLayout.getTabAt(i).setText(title1[i]);
            }

            TabFragmentPagerAdapter adapter2 = new TabFragmentPagerAdapter(fragment.getFragmentManager(), fragments2);
            recentRecordStatisticsDetailViewPager.setAdapter(adapter2);
            recentRecordStatisticsDetailTabLayout.setupWithViewPager(recentRecordStatisticsDetailViewPager);
            for(int i  = 0; i < title2.length; i++){
                recentRecordStatisticsDetailTabLayout.getTabAt(i).setText(title2[i]);
            }
        }

        private void initFragment(){

            fragments1 = new ArrayList<>();
            fragments1.add(RecentOverallStudentStatusRankingsFragment.newInstance());
            fragments1.add(StateChangeFragment.newInstance());

            fragments2 = new ArrayList<>();
            fragments2.add(UnfocusedDetailFragment.newInstance());
            fragments2.add(UnfocusedDetailFragment.newInstance());
            fragments2.add(UnfocusedDetailFragment.newInstance());
            fragments2.add(UnfocusedDetailFragment.newInstance());
        }
    }
}
