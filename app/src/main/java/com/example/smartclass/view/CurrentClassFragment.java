package com.example.smartclass.view;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.test;
import com.example.smartclass.contract.CurrentClassContract;
import com.example.smartclass.model.LoginModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.presenter.CurrentClassPresenter;
import com.example.smartclass.util.CircleBarView;
import com.example.smartclass.util.CircleBarViewUtil;

import java.util.Objects;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by YangFan
 * On 2019/1/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class CurrentClassFragment extends BaseMvpFragment<CurrentClassPresenter> implements CurrentClassContract.View {

    @BindView(R.id.currentClassToolbar)
    Toolbar currentClassToolbar;
    @BindView(R.id.currentClassCircleProgressBar)
    CircleBarView circleBarView;
    @BindView(R.id.currentClassProgressText)
    TextView currentClassProgressText;

    @BindView(R.id.currentClassName)
    TextView currentClassName;
    @BindView(R.id.currentClassClassesName)
    TextView currentClassClassesName;
    @BindView(R.id.currentClassPersonOfLateTextView)
    TextView currentClassPersonOfLateTextView;
    @BindView(R.id.currentClassOverallPersonOfLateTextView)
    TextView currentClassOverallPersonOfLateTextView;
    @BindView(R.id.currentClassPersonOfAbsenteeTextView)
    TextView currentClassPersonOfAbsenteeTextView;
    @BindView(R.id.currentClassOverallPersonOfAbsenteeTextView)
    TextView currentClassOverallPersonOfAbsenteeTextView;
    @BindView(R.id.currentClassPersonOfLeaveEarlyTextView)
    TextView currentClassPersonOfLeaveEarlyTextView;
    @BindView(R.id.currentClassOverallPersonOfLeaveEarlyTextView)
    TextView currentClassOverallPersonOfLeaveEarlyTextView;
    @BindView(R.id.currentClassPersonOfAbnormalTextView)
    TextView currentClassPersonOfAbnormalTextView;
    @BindView(R.id.currentClassOverallPersonOfAbnormalTextView)
    TextView currentClassOverallPersonOfAbnormalTextView;

    @BindView(R.id.currentClassLoadingProgressBar)
    LinearLayout loadingProgressBar;

    public static CurrentClassFragment newInstance() {

        Bundle args = new Bundle();

        CurrentClassFragment fragment = new CurrentClassFragment();
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

        initToolbar();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_current_class;
    }

    @Override
    public void showAttendanceProfile(AttendanceProfileBean bean) {

        setAttendanceProfileText(bean);
        CircleBarViewUtil.setAttendanceCircleBarView(circleBarView, bean.getCurrent_attendance(), currentClassProgressText);
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

    /**
     * 设置当前课堂出勤统计的出勤概况
     * @param bean 出勤概况
     */
    private void setAttendanceProfileText(AttendanceProfileBean bean){

        String currentStudents = String.valueOf(bean.getTotal_students());
        currentClassOverallPersonOfAbnormalTextView.setText(currentStudents);
        currentClassOverallPersonOfAbsenteeTextView.setText(currentStudents);
        currentClassOverallPersonOfLateTextView.setText(currentStudents);
        currentClassOverallPersonOfLeaveEarlyTextView.setText(currentStudents);

        currentClassPersonOfLateTextView.setText(formatAttendanceProfileText(bean.getLate()));
        currentClassPersonOfAbsenteeTextView.setText(formatAttendanceProfileText(bean.getAbsent()));
        currentClassPersonOfLeaveEarlyTextView.setText(formatAttendanceProfileText(bean.getEarly()));
        currentClassPersonOfAbnormalTextView.setText(formatAttendanceProfileText(bean.getQingjia()));
    }

    /**
     * 规范化出勤概况数据
     * @param num 未规范的出勤概况数据
     * @return 规范后的出勤概况
     */
    private String formatAttendanceProfileText(int num){
        return String.valueOf(num) + "人";
    }

    @OnClick({R.id.attendanceStatisticsImageView, R.id.attendanceStatisticsTextView})
    public void openAttendanceStatistics(){
        Intent intent = new Intent(getContext(), AttendanceStatisticsActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @OnClick({R.id.studentStatusImageView, R.id.studentStatusTextView})
    public void openStudentStatus(){
        Intent intent = new Intent(getContext(), StudentStatusActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void initToolbar(){

        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(currentClassToolbar);
        currentClassToolbar.setTitle("");
    }
}
