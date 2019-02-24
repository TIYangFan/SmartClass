package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.smartclass.R;
import com.example.smartclass.util.ActivityUtils;

import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/22
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        LoginFragment loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.loginByPasswordContentFrame);
        if (loginFragment == null){
            loginFragment = LoginFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.loginByPasswordContentFrame);
        }

    }
}
