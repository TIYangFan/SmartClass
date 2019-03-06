package com.example.smartclass.contract;

import android.content.Context;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ConcentrationDistributionBean;
import com.example.smartclass.bean.ScatterCoordinateBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.bean.UnfocusedStudentDetailsBean;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface StudentStatusContract {

    interface Model {

        /**
         * 加载用户工号
         * @param context 当前 fragment 的上下文，用来通过 sharedPreferences 获取工号
         * @return 返回用户的工号
         */
        String loadJobNumber(Context context);


        /**
         * 加载当前课堂专注度分布
         * @return 当前课堂专注度坐标集合
         */
        Flowable<BaseArrayBean<ScatterCoordinateBean>> loadCurrentStatusStatistics();

        /**
         * 加载当前课堂状态数据变化
         * @param jobNumber 用户工号
         * @return 当前课堂状态数据变化
         */
        Flowable<BaseArrayBean<TimeAndNumberOfPeopleBean>> loadStateChangeStatistics(String jobNumber);

        /**
         * 加载当前课堂专注度分布数据
         * @param jobNumber 用户工号
         * @return 当前课堂专注度班级分布数据
         */
        Flowable<ConcentrationDistributionBean> loadConcentrationDistributionStatistics(String jobNumber);

        /**
         * 加载当前课堂未专注同学名单
         * @return 当前课堂未专注同学名单
         */
        Flowable<UnfocusedStudentDetailsBean> loadUnfocusedStudentStatistics();
    }

    interface View extends BaseView {

        /**
         * 显示当前课堂的专注度分布散点图
         * @param bean 当前课堂专注度分布坐标集合
         */
        void showCurrentStatusScatterChart(BaseArrayBean<ScatterCoordinateBean> bean);

        /**
         * 显示当前课堂专注度随时间变化曲线图
         * @param bean 当前课堂专注度随时间变化数据
         */
        void showStateChangeLineChart(BaseArrayBean<TimeAndNumberOfPeopleBean> bean);

        /**
         * 显示当前课堂专注度分布环状图
         * @param bean 当前课堂专注度班级分布环状图
         */
        void showConcentrationDistributionPieChart(ConcentrationDistributionBean bean);

        /**
         * 显示当前课堂未专注同学名单列表
         * @param bean 当前课堂未专注同学名单
         */
        void showUnfocusedStudentList(UnfocusedStudentDetailsBean bean);
    }

    interface Presenter {

        /**
         * 加载用户工号
         */
        void loadJobNumber();

        /**
         * 加载当前页面全部信息
         */
        void loadAllStatisticsOnThePage();

        /**
         * 加载当前课堂状态点状分布数据
         */
        void loadCurrentStatusStatistics();

        /**
         * 加载当前课堂状态随时间变化数据
         */
        void loadStateChangeStatistics();

        /**
         * 加载当前课堂专注度班级分布数据
         */
        void loadConcentrationDistributionStatistics();

        /**
         * 加载未专注同学名单
         */
        void loadUnfocusedStudentStatistics();
    }
}
