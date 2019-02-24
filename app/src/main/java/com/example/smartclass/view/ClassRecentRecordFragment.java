package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.smartclass.R;
import com.example.smartclass.adapter.ClassExpandableListAdapter;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/31
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassRecentRecordFragment extends Fragment {

    @BindView(R.id.classExpandableListView)
    ExpandableListView mClassExpandableListView;

    public String[] groupString = {"射手", "辅助", "坦克", "法师"};
    public String[][] childString = {
            {"孙尚香", "后羿", "马可波罗", "狄仁杰"},
            {"孙膑", "蔡文姬", "鬼谷子", "杨玉环"},
            {"张飞", "廉颇", "牛魔", "项羽"},
            {"诸葛亮", "王昭君", "安琪拉", "干将"}

    };

    public static ClassRecentRecordFragment newInstance() {

        Bundle args = new Bundle();

        ClassRecentRecordFragment fragment = new ClassRecentRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_recent_record, container, false);
        ButterKnife.bind(this, root);

        mClassExpandableListView.setGroupIndicator(null);
        mClassExpandableListView.setDivider(null);

        mClassExpandableListView.setAdapter(new ClassExpandableListAdapter(this));

        mClassExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                v.findViewById(R.id.classGroupViewRoot).setBackground(getResources().getDrawable(R.drawable.bg_top_border_radius));
                Toast.makeText(getActivity().getApplicationContext(), groupString[groupPosition], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mClassExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity().getApplicationContext(), childString[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return root;
    }

}
