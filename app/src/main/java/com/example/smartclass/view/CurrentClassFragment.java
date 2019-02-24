package com.example.smartclass.view;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.bean.test;
import com.example.smartclass.model.LoginModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.util.CircleBarView;

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
public class CurrentClassFragment extends Fragment {

    @BindView(R.id.currentClassToolbar)
    Toolbar mCurrentClassToolbar;
    @BindView(R.id.circleProgressBar)
    CircleBarView circleProgressBar;
    @BindView(R.id.text_progress)
    TextView text_progress;
    @BindView(R.id.testButton)
    Button testButton;

    public static CurrentClassFragment newInstance() {

        Bundle args = new Bundle();

        CurrentClassFragment fragment = new CurrentClassFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_current_class, container, false);
        ButterKnife.bind(this, root);

        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(mCurrentClassToolbar);
        mCurrentClassToolbar.setTitle("");

        circleProgressBar.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
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
        circleProgressBar.setTextView(text_progress);
        circleProgressBar.setProgressNum(80,3000);

        return root;
    }

    @OnClick({R.id.attendanceStatisticsImageView, R.id.attendanceStatisticsTextView})
    public void startAttendanceStatistics(){
        Intent intent = new Intent(getContext(), AttendanceStatisticsActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @OnClick({R.id.studentStatusImageView, R.id.studentStatusTextView})
    public void startStudentStatus(){
        Intent intent = new Intent(getContext(), StudentStatusActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @OnClick(R.id.testButton)
    public void testNet(){

        LoginModel model = new LoginModel();
        model.login()
                .compose(RxScheduler.<test>Flo_io_main())
                .subscribe(new Consumer<test>() {
                    @Override
                    public void accept(test test) throws Exception {
                        Toast.makeText(getContext(), test.getName(), Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
