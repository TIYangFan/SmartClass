package com.example.smartclass.presenter;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.contract.LoginContract;
import com.example.smartclass.model.LoginModel;
import com.example.smartclass.view.LoginFragment;


/**
 * Created by YangFan
 * On 2019/1/22
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class LoginPresenter extends BaseMvpPresenter<LoginFragment> implements LoginContract.Presenter {

    private LoginContract.Model model;

    public LoginPresenter(LoginFragment view) {
        super(view);
        model = new LoginModel();
        mView.setPresenter(this);
    }

    @Override
    public void login() {

    }
}
