package com.example.smartclass.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.adapter.ClassRecentRecordExpandableListAdapter;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.bean.AttendanceAndStatusBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassRecentRecordBean;
import com.example.smartclass.contract.ClassRecentRecordContract;
import com.example.smartclass.presenter.ClassRecentRecordPresenter;

import java.util.ArrayList;

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

    public static ClassRecentRecordFragment newInstance() {

        Bundle args = new Bundle();

        ClassRecentRecordFragment fragment = new ClassRecentRecordFragment();
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

        initExpandableListView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class_recent_record;
    }

    @Override
    public void showClassRecentRecord(BaseArrayBean<ClassRecentRecordBean> bean) {

        ArrayList<ClassRecentRecordBean> arrayList = bean.getArrayList();
        for(int i = 0; i < arrayList.size(); i++){
            expandableListAdapter.addTitleToGroup(arrayList.get(i).getClass_name());
        }
        expandableListAdapter.notifyDataSetChanged();
        Log.e("RECORD", bean.getArrayList().get(0).getClass_name());
    }

    @Override
    public void showClassRecentRecordDetails(AttendanceAndStatusBean bean) {

        Log.e("RECORD", bean.getFocus().get(0).getDate());
    }

    @Override
    public void showClassInformation() {

    }

    @Override
    public void showAttendanceProfile() {

    }

    @Override
    public void showAttendanceLineChart() {

    }

    @Override
    public void showClassStatusLineChart() {

    }

    @Override
    public void showProblemStudentsList() {

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

    private void initExpandableListView(){

        expandableListAdapter = new ClassRecentRecordExpandableListAdapter(this);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setDivider(null);
    }
}
