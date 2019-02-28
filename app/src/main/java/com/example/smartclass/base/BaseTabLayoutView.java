package com.example.smartclass.base;

/**
 * Created by YangFan
 * On 2019/2/28
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface BaseTabLayoutView {

    /**
     * 初始化 TabLayoutView
     */
    void initTabLayoutView();

    /**
     * 初始化 TabLayoutView 的标题文字
     */
    void initTitles();

    /**
     * 初始化 TabLayoutView 中的 fragments
     */
    void initFragments();

    /**
     * 设置 TabLayoutView ViewPager 的 Adapter
     */
    void initViewPagerAdapter();

    /**
     * 初始化 TabLayout
     */
    void initTabLayout();
}
