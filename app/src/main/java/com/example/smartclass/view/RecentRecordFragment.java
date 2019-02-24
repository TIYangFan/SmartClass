package com.example.smartclass.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartclass.R;
import com.example.smartclass.adapter.TabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class RecentRecordFragment extends Fragment {

    @BindView(R.id.recentRecordToolbar)
    Toolbar mRecentRecordToolbar;
    @BindView(R.id.recentRecordViewPager)
    ViewPager mRecentRecordViewPager;
    @BindView(R.id.topTabLayout)
    TabLayout mTopTabLayout;

    private List<Fragment> mFragments;
    private String[] mTitles;

    public static RecentRecordFragment newInstance() {

        Bundle args = new Bundle();

        RecentRecordFragment fragment = new RecentRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recent_record, container, false);
        ButterKnife.bind(this, root);
        initView();

        return root;
    }

    private void initView(){

        Resources resources = getResources();
        mTitles = resources.getStringArray(R.array.top_tab_bar_titles);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mRecentRecordToolbar);
        mRecentRecordToolbar.setTitle("");
        initFragment();
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getChildFragmentManager(), mFragments);
        mRecentRecordViewPager.setAdapter(adapter);
        mTopTabLayout.setupWithViewPager(mRecentRecordViewPager);
        for (int i = 0; i < mTitles.length; i++){
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_top_tab, null);
            TextView textView = (TextView)view.findViewById(R.id.topTabTextView);
            textView.setText(mTitles[i]);
            mTopTabLayout.getTabAt(i).setCustomView(view);
        }
    }

    private void initFragment(){

        mFragments = new ArrayList<>();
        mFragments.add(OverallRecentRecordFragment.newInstance());
        mFragments.add(ClassRecentRecordFragment.newInstance());
    }


}
