package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseMvpActivity;
import com.example.smartclass.presenter.StudentStatusPresenter;
import com.example.smartclass.util.ActivityUtils;

import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class StudentStatusActivity extends BaseMvpActivity<StudentStatusPresenter> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_student_status;
    }

    @Override
    public void initView() {

        StudentStatusFragment studentStatusFragment = (StudentStatusFragment)getSupportFragmentManager().
                findFragmentById(R.id.studentStatusContentFrame);

        if(studentStatusFragment == null){
            studentStatusFragment = StudentStatusFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), studentStatusFragment, R.id.studentStatusContentFrame);
        }

        mPresenter = new StudentStatusPresenter(studentStatusFragment);
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
