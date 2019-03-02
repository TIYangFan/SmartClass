package com.example.smartclass.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.smartclass.R;
import com.example.smartclass.adapter.TabFragmentPagerAdapter;
import com.example.smartclass.base.BaseActivity;
import com.example.smartclass.presenter.ClassRecentRecordPresenter;
import com.example.smartclass.presenter.CurrentClassPresenter;
import com.example.smartclass.presenter.OverallRecentRecordPresenter;
import com.example.smartclass.presenter.PersonalCenterPresenter;
import com.example.smartclass.util.NoScrollViewPager;
import com.example.smartclass.util.SharedPreferencesUtil;
import com.example.smartclass.util.SmartClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by YangFan
 * On 2019/1/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class TabLayoutActivity extends BaseActivity {

    @BindView(R.id.tabViewPager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    private int[] tabImages = {R.drawable.ic_tab_selector_rr, R.drawable.ic_tab_selector_cc, R.drawable.ic_tab_selector_pc};
    private List<Fragment> mainPageFragments;
    private String[] tabTitles;

    private PersonalCenterPresenter personalCenterPresenter;
    private CurrentClassPresenter currentClassPresenter;
    private OverallRecentRecordPresenter overallRecentRecordPresenter;
    private ClassRecentRecordPresenter classRecentRecordPresenter;

    @Override
    protected void onStart() {
        super.onStart();
        //String jobNumber = ((SmartClass) getApplication()).getJobNumber();
        String jobNumber = SharedPreferencesUtil.getStoreJobNumber(this);
        if(jobNumber == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    public void initView(){

        initTitles();
        initFragments();
        initPresenter();
        initTabLayoutView();
    }

    @Override
    protected void onDestroy() {

        if (personalCenterPresenter != null) {
            personalCenterPresenter.detachView();
        }
        if (currentClassPresenter != null) {
            currentClassPresenter.detachView();
        }
        if (overallRecentRecordPresenter != null) {
            overallRecentRecordPresenter.detachView();
        }
        if (classRecentRecordPresenter != null) {
            classRecentRecordPresenter.detachView();
        }
        super.onDestroy();
    }

    private void initTitles(){

        Resources resources = getResources();
        tabTitles = resources.getStringArray(R.array.bottom_tab_bar_titles);
    }

    public void initFragments(){

        List<Fragment> recentRecordFragments = new ArrayList<>();
        recentRecordFragments.add(OverallRecentRecordFragment.newInstance());
        recentRecordFragments.add(ClassRecentRecordFragment.newInstance());

        mainPageFragments = new ArrayList<>();
        mainPageFragments.add(RecentRecordFragment.newInstance(recentRecordFragments));
        mainPageFragments.add(CurrentClassFragment.newInstance());
        mainPageFragments.add(PersonalCenterFragment.newInstance());
    }

    private void initPresenter(){

        RecentRecordFragment recentRecordFragment = (RecentRecordFragment)mainPageFragments.get(0);
        OverallRecentRecordFragment overallRecentRecordFragment = (OverallRecentRecordFragment) recentRecordFragment.getFragmentByIndex(0);
        overallRecentRecordPresenter = new OverallRecentRecordPresenter(overallRecentRecordFragment);

        ClassRecentRecordFragment classRecentRecordFragment = (ClassRecentRecordFragment) recentRecordFragment.getFragmentByIndex(1);
        classRecentRecordPresenter = new ClassRecentRecordPresenter(classRecentRecordFragment);

        CurrentClassFragment currentClassFragment = (CurrentClassFragment)mainPageFragments.get(1);
        currentClassPresenter = new CurrentClassPresenter(currentClassFragment);

        PersonalCenterFragment personalCenterFragment = (PersonalCenterFragment)mainPageFragments.get(2);
        personalCenterPresenter = new PersonalCenterPresenter(personalCenterFragment);
    }

    private void initTabLayoutView(){

        initViewPager();
        initTabLayout();
    }

    private void initViewPager(){

        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), mainPageFragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(1);
    }

    private void initTabLayout(){

        mTabLayout.setupWithViewPager(mViewPager);
        for(int i = 0; i < tabTitles.length; i++){
            setCustomView(i);
        }
    }

    private void setCustomView(int index){

        @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ImageView imageView = view.findViewById(R.id.tabImageView);
        TextView textView = view.findViewById(R.id.tabTextView);
        textView.setText(tabTitles[index]);
        imageView.setImageResource(tabImages[index]);
        Objects.requireNonNull(mTabLayout.getTabAt(index)).setCustomView(view);
    }
}
