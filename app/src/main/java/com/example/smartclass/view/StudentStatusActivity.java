package com.example.smartclass.view;

import android.view.View;
import android.widget.ImageView;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseMvpActivity;
import com.example.smartclass.presenter.StudentStatusPresenter;
import com.example.smartclass.util.ActivityUtils;

import butterknife.BindView;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class StudentStatusActivity extends BaseMvpActivity<StudentStatusPresenter> {

    @BindView(R.id.backFromStudentStatus)
    ImageView backFromStudentStatus;

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

        setBackButton();
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

    private void setBackButton() {

        backFromStudentStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
