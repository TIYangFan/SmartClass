package com.example.smartclass.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseMvpFragment;
import com.example.smartclass.contract.PersonalCenterContract;
import com.example.smartclass.presenter.PersonalCenterPresenter;
import com.example.smartclass.util.SharedPreferencesUtil;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class PersonalCenterFragment extends BaseMvpFragment<PersonalCenterPresenter> implements PersonalCenterContract.View {

    @BindView(R.id.personalCenterToolbar)
    Toolbar personalCenterToolbar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userJobNumber)
    TextView userJobNumber;


    public static PersonalCenterFragment newInstance() {

        Bundle args = new Bundle();

        PersonalCenterFragment fragment = new PersonalCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {

        initToolbar();
        showUserInformation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_center;
    }

    @Override
    public void showUserInformation() {

        String userJobNumber = SharedPreferencesUtil.getStoreJobNumber(getActivity());
        this.userJobNumber.setText(userJobNumber);
        String userName = SharedPreferencesUtil.getStoreUserName(getActivity());
        this.userName.setText(userName);
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

    private void initToolbar(){

        AppCompatActivity activity = (AppCompatActivity)getActivity();
        Objects.requireNonNull(activity).setSupportActionBar(personalCenterToolbar);
        personalCenterToolbar.setTitle("");
    }
}
