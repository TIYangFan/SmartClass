package com.example.smartclass.presenter;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassInfoAboutTimeAndRelatedInfoBean;
import com.example.smartclass.bean.ClassRankingBean;
import com.example.smartclass.bean.DateAndPercentageBean;
import com.example.smartclass.contract.OverallRecentRecordContract;
import com.example.smartclass.model.OverallRecentRecordModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.util.SharedPreferencesUtil;
import com.example.smartclass.view.OverallRecentRecordFragment;

import io.reactivex.functions.Consumer;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class OverallRecentRecordPresenter extends BaseMvpPresenter<OverallRecentRecordFragment> implements OverallRecentRecordContract.Presenter {

    private OverallRecentRecordContract.Model model;
    private String jobNumber;

    public OverallRecentRecordPresenter(OverallRecentRecordFragment view) {
        super(view);
        model = new OverallRecentRecordModel();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        super.subscribe();

        mView.showLoading();
        loadJobNumber();
        loadAllStatisticsOnThePage();
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

    @Override
    public void loadJobNumber() {

        jobNumber = model.loadJobNumber(mView.getActivity());
    }

    @Override
    public void loadAllStatisticsOnThePage() {

        mView.showLoading();
        loadAttendanceProfile();
        loadAttendanceStatistics();
        loadClassStatusStatistics();
        loadClassRankingStatistics();
    }

    @Override
    public void loadAttendanceProfile() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.loadAttendanceProfile(jobNumber)
                .compose(RxScheduler.<AttendanceProfileBean>Flo_io_main())
                .as(mView.<AttendanceProfileBean>bindAutoDispose())
                .subscribe(new Consumer<AttendanceProfileBean>() {
                    @Override
                    public void accept(AttendanceProfileBean bean) throws Exception {
                        mView.showAttendanceProfile(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void loadAttendanceStatistics() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.loadAttendanceStatistics(jobNumber)
                .compose(RxScheduler.<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>>Flo_io_main())
                .as(mView.<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>>bindAutoDispose())
                .subscribe(new Consumer<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>>() {
                    @Override
                    public void accept(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean) throws Exception {
                        mView.showAttendanceLineChart(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void loadClassStatusStatistics() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.loadClassStatusStatistics(jobNumber)
                .compose(RxScheduler.<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>>Flo_io_main())
                .as(mView.<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>>bindAutoDispose())
                .subscribe(new Consumer<BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>>>() {
                    @Override
                    public void accept(BaseArrayBean<ClassInfoAboutTimeAndRelatedInfoBean<DateAndPercentageBean>> bean) throws Exception {
                        mView.showClassStatusLineChart(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void loadClassRankingStatistics() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.loadClassRankingStatistics(jobNumber)
                .compose(RxScheduler.<ClassRankingBean>Flo_io_main())
                .as(mView.<ClassRankingBean>bindAutoDispose())
                .subscribe(new Consumer<ClassRankingBean>() {
                    @Override
                    public void accept(ClassRankingBean bean) throws Exception {
                        mView.showClassRankingBarChart(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }
}
