package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartclass.R;
import com.example.smartclass.adapter.UnfocusedDetailRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class UnfocusedDetailFragment extends Fragment {

    @BindView(R.id.unfocusedDetailText)
    TextView unfocusedDetailText;
    @BindView(R.id.unfocusedDetailRecyclerView)
    RecyclerView unfocusedDetailRecyclerView;

    private String[][] data = {
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
            {"计科1706", "杨帆", "1033170614"},
    };

    public static UnfocusedDetailFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("TITLE", title);
        UnfocusedDetailFragment fragment = new UnfocusedDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_unfocused_detail, container, false);
        ButterKnife.bind(this, root);
        unfocusedDetailText.setText(getArguments().getString("TITLE"));

        unfocusedDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        unfocusedDetailRecyclerView.setAdapter(new UnfocusedDetailRecyclerViewAdapter(data));

        return root;
    }
}
