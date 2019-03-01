package com.example.smartclass.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.smartclass.R;
import com.example.smartclass.adapter.ClassRecentRecordExpandableListAdapter;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.contract.ClassRecentRecordContract;
import com.example.smartclass.presenter.ClassRecentRecordPresenter;

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

    private ClassRecentRecordExpandableListAdapter expandableListAdapter;

    public static ClassRecentRecordFragment newInstance() {

        Bundle args = new Bundle();

        ClassRecentRecordFragment fragment = new ClassRecentRecordFragment();
        fragment.setArguments(args);
        return fragment;
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

    }

    @Override
    public void hideLoading() {

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
