package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.smartclass.R;
import com.example.smartclass.util.ActivityUtils;

import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/30
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class VerificationCodeLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code_login);
        ButterKnife.bind(this);

        VerificationCodeLoginFragment verificationCodeLoginFragment = (VerificationCodeLoginFragment)
                getSupportFragmentManager().findFragmentById(R.id.verificationCodeLoginContentFrame);
        if(verificationCodeLoginFragment == null){
            verificationCodeLoginFragment = VerificationCodeLoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), verificationCodeLoginFragment,
                    R.id.verificationCodeLoginContentFrame);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
