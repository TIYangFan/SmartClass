package com.example.smartclass.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.contract.LoginContract;
import com.example.smartclass.presenter.LoginPresenter;
import com.example.smartclass.util.PageSwitchingAnimation;
import com.example.smartclass.util.SharedPreferencesUtil;

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
    @BindView(R.id.inputUserName)
    EditText inputUserName;
    @BindView(R.id.inputPassword)
    EditText inputPassword;

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

    @OnClick(R.id.buttonLoginByPassword)
    public void loginByPassword() {

        String userName = inputUserName.getText().toString();
        String password = inputPassword.getText().toString();

        if(!userName.equals("1030416601") || !password.equals("123456")){
            Toast.makeText(getActivity(), "密码错误, 请重新输入", Toast.LENGTH_SHORT).show();
            inputPassword.setText(null);
            return;
        }

        SharedPreferencesUtil.setStoreJobNumber(getActivity(), "1030416601");
        SharedPreferencesUtil.setStoreUserName(getActivity(), "杨帆");
        Intent intent = new Intent(getContext(), TabLayoutActivity.class);
        startActivity(intent);
        PageSwitchingAnimation.startActivityAnimation(Objects.requireNonNull(getActivity()));
    }

    @OnClick(R.id.textViewRegistered)
    public void registered(){

        Intent intent = new Intent(getContext(), RegisteredActivity.class);
        startActivity(intent);
        PageSwitchingAnimation.startActivityAnimation(Objects.requireNonNull(getActivity()));
    }

    @OnClick(R.id.textViewVerificationCodeLogin)
    public void loginByVerificationCode(){

        Intent intent = new Intent(getContext(), VerificationCodeLoginActivity.class);
        startActivity(intent);
        PageSwitchingAnimation.startActivityAnimation(Objects.requireNonNull(getActivity()));
    }

}
