package com.example.smartclass.view;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseMvpActivity;
import com.example.smartclass.presenter.LoginPresenter;
import com.example.smartclass.util.ActivityUtils;

/**
 * Created by YangFan
 * On 2019/1/22
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        LoginFragment loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.loginByPasswordContentFrame);
        if (loginFragment == null){
            loginFragment = LoginFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.loginByPasswordContentFrame);
        }

        mPresenter = new LoginPresenter(loginFragment);
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
