package com.example.smartclass.presenter;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.bean.test;
import com.example.smartclass.contract.LoginContract;
import com.example.smartclass.model.LoginModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.view.LoginFragment;

import io.reactivex.functions.Consumer;

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
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        model.login()
                .compose(RxScheduler.<test>Flo_io_main())
                .as(mView.<test>bindAutoDispose())
                .subscribe(new Consumer<test>() {
                    @Override
                    public void accept(test bean) throws Exception {
                        mView.test(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }
}
