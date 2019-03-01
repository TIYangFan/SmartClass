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
import com.example.smartclass.adapter.UnfocusedStudentDetailsRecyclerViewAdapter;
import com.example.smartclass.bean.StudentStatusDetailsBean;
import com.example.smartclass.bean.UnfocusedStudentDetailsBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class UnfocusedStudentDetailsFragment extends Fragment {

    @BindView(R.id.unfocusedDetailStudentRecyclerView)
    RecyclerView unfocusedDetailStudentRecyclerView;

    private UnfocusedStudentDetailsRecyclerViewAdapter recyclerViewAdapter;

//    private String[][] data = {
//            {"15min", "/45min", "杨帆", "计科1706", "1033170614"},
//            {"15min", "/45min", "杨帆", "计科1706", "1033170614"},
//            {"15min", "/45min", "杨帆", "计科1706", "1033170614"},
//            {"15min", "/45min", "杨帆", "计科1706", "1033170614"},
//            {"15min", "/45min", "杨帆", "计科1706", "1033170614"},
//            {"15min", "/45min", "杨帆", "计科1706", "1033170614"},
//    };

    public static UnfocusedStudentDetailsFragment newInstance() {

        Bundle args = new Bundle();

        UnfocusedStudentDetailsFragment fragment = new UnfocusedStudentDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_unfocused_student_details, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    public void initRecyclerView(ArrayList<StudentStatusDetailsBean> listData){

        recyclerViewAdapter = new UnfocusedStudentDetailsRecyclerViewAdapter(listData);
        unfocusedDetailStudentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        unfocusedDetailStudentRecyclerView.setAdapter(recyclerViewAdapter);
    }

    public void updateListData(ArrayList<StudentStatusDetailsBean> listData){

        recyclerViewAdapter.setListData(listData);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
