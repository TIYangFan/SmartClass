package com.example.smartclass.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.smartclass.R;
import com.example.smartclass.adapter.TabFragmentPagerAdapter;
import com.example.smartclass.presenter.ClassRecentRecordPresenter;
import com.example.smartclass.presenter.CurrentClassPresenter;
import com.example.smartclass.presenter.OverallRecentRecordPresenter;
import com.example.smartclass.presenter.PersonalCenterPresenter;

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
public class TabLayoutActivity extends AppCompatActivity {

    private List<Fragment> fragments;
    private String[] mTitles;
    private int[] mImages = {R.drawable.ic_tab_selector_rr, R.drawable.ic_tab_selector_cc, R.drawable.ic_tab_selector_pc};

    private List<Fragment> mFragments;


    private PersonalCenterPresenter personalCenterPresenter;
    private CurrentClassPresenter currentClassPresenter;
    private OverallRecentRecordPresenter overallRecentRecordPresenter;
    private ClassRecentRecordPresenter classRecentRecordPresenter;

    @BindView(R.id.tabViewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        Resources resources = getResources();
        mTitles = resources.getStringArray(R.array.bottom_tab_bar_titles);

        initFragment();

        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(1);

        mTabLayout.setupWithViewPager(mViewPager);
        for(int i = 0; i < mTitles.length; i++){
            View view = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            ImageView imageView = (ImageView)view.findViewById(R.id.tabImageView);
            TextView textView = (TextView)view.findViewById(R.id.tabTextView);
            textView.setText(mTitles[i]);
            imageView.setImageResource(mImages[i]);
            mTabLayout.getTabAt(i).setCustomView(view);
        }
    }

    private void initFragment(){

        initFragments();
        fragments = new ArrayList<>();
        fragments.add(RecentRecordFragment.newInstance(mFragments));
        fragments.add(CurrentClassFragment.newInstance());
        fragments.add(PersonalCenterFragment.newInstance());

        initPresenter();
    }

    private void initPresenter(){

        personalCenterPresenter = new PersonalCenterPresenter();
        currentClassPresenter = new CurrentClassPresenter();

        RecentRecordFragment recentRecordFragment = (RecentRecordFragment)fragments.get(0);
        OverallRecentRecordFragment overallRecentRecordFragment = (OverallRecentRecordFragment) recentRecordFragment.getFragmentByIndex(0);
        overallRecentRecordPresenter = new OverallRecentRecordPresenter(overallRecentRecordFragment);

        ClassRecentRecordFragment classRecentRecordFragment = (ClassRecentRecordFragment) recentRecordFragment.getFragmentByIndex(1);
        classRecentRecordPresenter = new ClassRecentRecordPresenter(classRecentRecordFragment);
    }

    public void initFragments(){

        mFragments = new ArrayList<>();
        mFragments.add(OverallRecentRecordFragment.newInstance());
        mFragments.add(ClassRecentRecordFragment.newInstance());
    }

}
