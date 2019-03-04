package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.adapter.ClassRecentRecordExpandableListAdapter;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.bean.AttendanceAndStatusBean;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassRecentRecordBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;
import com.example.smartclass.contract.ClassRecentRecordContract;
import com.example.smartclass.presenter.ClassRecentRecordPresenter;
import com.example.smartclass.util.OnMultiClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by YangFan
 * On 2019/1/31
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassRecentRecordFragment extends BaseMvpFragment<ClassRecentRecordPresenter> implements ClassRecentRecordContract.View {

    @BindView(R.id.classExpandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.classRecentRecordLoadingProgressBar)
    LinearLayout loadingProgressBar;

    private ClassRecentRecordExpandableListAdapter expandableListAdapter;
    private List<String> groupString = new ArrayList<>();
    private List<AttendanceProfileBean> groupAttendanceProfileBeans = new ArrayList<>();

    private List<String> classIdList = new ArrayList<>();

    private int lastGroupPosition = -1;
    private boolean isExpand = false;


    public static ClassRecentRecordFragment newInstance() {

        Bundle args = new Bundle();

        ClassRecentRecordFragment fragment = new ClassRecentRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null){
            mPresenter.subscribe();
            if (groupString.isEmpty()){
                showLoading();
                mPresenter.loadClassRecentRecord();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class_recent_record;
    }

    @Override
    public void showClassRecentRecord(BaseArrayBean<ClassRecentRecordBean> bean) {

        ArrayList<ClassRecentRecordBean> arrayList = bean.getArrayList();
        for(int i = 0; i < arrayList.size(); i++){

            classIdList.add(String.valueOf(arrayList.get(i).getClass_id()));
            groupString.add(arrayList.get(i).getClass_name());
            groupAttendanceProfileBeans.add(setAttendanceProfileBean(arrayList.get(i)));
        }
        //initExpandableListView();
        initExpandableListViewByOnce();
    }

    @Override
    public void showClassRecentRecordDetails(AttendanceAndStatusBean bean, StudentsWithAttendanceProblemsBean biBean, int groupPosition) {

        expandableListAdapter.bindDataToChildView(groupPosition, bean.getAttendance(), bean.getFocus(), biBean);
        expandableListView.expandGroup(groupPosition);
    }

    public void showClassRecentRecordDetailsByOnce(AttendanceAndStatusBean bean, StudentsWithAttendanceProblemsBean biBean, int groupPosition) {

        expandableListAdapter.bindDataToChildView(groupPosition, bean.getAttendance(), bean.getFocus(), biBean);
    }

    @Override
    public void showLoading() {
        loadingProgressBar.bringToFront();
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    private AttendanceProfileBean setAttendanceProfileBean(ClassRecentRecordBean bean){

        AttendanceProfileBean attendanceProfileBean = new AttendanceProfileBean();
        attendanceProfileBean.setCurrent_attendance(bean.getPercent());
        attendanceProfileBean.setTotal_students(bean.getTotal_students());
        attendanceProfileBean.setLate(bean.getLate());
        attendanceProfileBean.setAbsent(bean.getAbsent());
        attendanceProfileBean.setEarly(bean.getEarly());
        attendanceProfileBean.setQingjia(bean.getQingjia());
        return attendanceProfileBean;
    }

    private void initExpandableListViewByOnce(){

        expandableListAdapter = new ClassRecentRecordExpandableListAdapter(this, groupString, groupAttendanceProfileBeans);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setDivider(null);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if(lastGroupPosition == groupPosition && isExpand){
                    parent.collapseGroup(groupPosition);
                    isExpand = !isExpand;
                    return true;
                }else{
                    lastGroupPosition = groupPosition;
                    isExpand = !isExpand;
                }

                for(int i = 0; i < groupString.size(); i++){
                    if(parent.isGroupExpanded(i)){
                        parent.collapseGroup(i);
                    }
                }

                parent.expandGroup(groupPosition);
                return true;
            }
        });

        for(int groupPosition = 0; groupPosition < groupString.size(); groupPosition++){

            String classId = classIdList.get(groupPosition);
            if(expandableListAdapter.isAlreadyLoaded(groupPosition)){
                expandableListView.expandGroup(groupPosition);
            }else{
                mPresenter.loadClassRecentRecordDetails(classId, groupPosition);
                classIdList.set(groupPosition, null);
            }
        }
    }

    private void initExpandableListView(){

        expandableListAdapter = new ClassRecentRecordExpandableListAdapter(this, groupString, groupAttendanceProfileBeans);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setDivider(null);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                parent.expandGroup(groupPosition);

                if(lastGroupPosition == groupPosition && isExpand){
                    parent.collapseGroup(groupPosition);
                    isExpand = !isExpand;
                    return true;
                }else{
                    lastGroupPosition = groupPosition;
                    isExpand = !isExpand;
                }

                for(int i = 0; i < groupString.size(); i++){
                    if(parent.isGroupExpanded(i)){
                        parent.collapseGroup(i);
                    }
                }

                String classId = classIdList.get(groupPosition);
                if(expandableListAdapter.isAlreadyLoaded(groupPosition)){
                    expandableListView.expandGroup(groupPosition);
                }else{
                    mPresenter.loadClassRecentRecordDetails(classId, groupPosition);
                    classIdList.set(groupPosition, null);
                }

//                if(classId != null){
//                    mPresenter.loadClassRecentRecordDetails(classId, groupPosition);
//                    classIdList.set(groupPosition, null);
//                }else{
//                    expandableListView.expandGroup(groupPosition);
//                }

                return true;
            }
        });
    }
}
