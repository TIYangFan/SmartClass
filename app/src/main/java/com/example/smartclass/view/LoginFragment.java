package com.example.smartclass.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.bean.test;
import com.example.smartclass.contract.LoginContract;
import com.example.smartclass.presenter.LoginPresenter;
import com.example.smartclass.util.PageSwitchingAnimation;
import com.example.smartclass.util.SharedPreferencesUtil;
import com.example.smartclass.util.SmartClass;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by YangFan
 * On 2019/1/22
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class LoginFragment extends BaseMvpFragment<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.buttonLoginByPassword)
    Button mButtonLoginByPassword;
    @BindView(R.id.textViewRegistered)
    TextView mTextViewRegistered;
    @BindView(R.id.textViewVerificationCodeLogin)
    TextView mTextViewVerificationCodeLogin;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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

    public void test(test bean){
        Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), bean.getName(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.buttonLoginByPassword)
    public void loginByPassword() {

        //mPresenter.login();
        SharedPreferencesUtil.setStoreJobNumber(getActivity(), "1030416601");
        //((SmartClass) Objects.requireNonNull(getActivity()).getApplication()).setJobNumber("1030416601");
        Intent intent = new Intent(getContext(), TabLayoutActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        PageSwitchingAnimation.startActivityAnimation(getActivity());
    }

    @OnClick(R.id.textViewRegistered)
    public void registered(){

        Intent intent = new Intent(getContext(), RegisteredActivity.class);
        startActivity(intent);
        PageSwitchingAnimation.startActivityAnimation(getActivity());
    }

    @OnClick(R.id.textViewVerificationCodeLogin)
    public void loginByVerificationCode(){

        Intent intent = new Intent(getContext(), VerificationCodeLoginActivity.class);
        startActivity(intent);
        PageSwitchingAnimation.startActivityAnimation(getActivity());
    }

}
