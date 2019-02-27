package com.example.smartclass.view;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseMvpActivity;
import com.example.smartclass.presenter.AttendanceStatisticsPresenter;
import com.example.smartclass.util.ActivityUtils;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class AttendanceStatisticsActivity extends BaseMvpActivity<AttendanceStatisticsPresenter> {


    @Override
    public int getLayoutId() {

        return R.layout.activity_attendance_statistics;
    }

    @Override
    public void initView() {

        AttendanceStatisticsFragment attendanceStatisticsFragment = (AttendanceStatisticsFragment)getSupportFragmentManager().
                findFragmentById(R.id.attendanceStatisticsContentFrame);

        if(attendanceStatisticsFragment == null){
            attendanceStatisticsFragment = AttendanceStatisticsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), attendanceStatisticsFragment, R.id.attendanceStatisticsContentFrame);
        }

        mPresenter = new AttendanceStatisticsPresenter(attendanceStatisticsFragment);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
}
