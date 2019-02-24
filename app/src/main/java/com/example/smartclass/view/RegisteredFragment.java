package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.smartclass.R;

import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/30
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class RegisteredFragment extends Fragment {

    public static RegisteredFragment newInstance() {

        Bundle args = new Bundle();

        RegisteredFragment fragment = new RegisteredFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_registered, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
}
