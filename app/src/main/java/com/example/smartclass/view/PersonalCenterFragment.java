package com.example.smartclass.view;

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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class PersonalCenterFragment extends Fragment {

    @BindView(R.id.personalCenterToolbar)
    Toolbar mPersonalCenterToolbar;


    public static PersonalCenterFragment newInstance() {

        Bundle args = new Bundle();

        PersonalCenterFragment fragment = new PersonalCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal_center, container, false);
        ButterKnife.bind(this, root);

        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(mPersonalCenterToolbar);
        mPersonalCenterToolbar.setTitle("");

        return root;
    }
}
