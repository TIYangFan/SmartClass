package com.example.smartclass.presenter;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.TimeAndNumberOfPeopleBean;
import com.example.smartclass.contract.AttendanceStatisticsContract;
import com.example.smartclass.model.AttendanceStatisticsModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.util.SharedPreferencesUtil;
import com.example.smartclass.view.AttendanceStatisticsFragment;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class AttendanceStatisticsPresenter extends BaseMvpPresenter<AttendanceStatisticsFragment> implements AttendanceStatisticsContract.Presenter {

    private AttendanceStatisticsContract.Model model;
    private String jobNumber;

    public AttendanceStatisticsPresenter(AttendanceStatisticsFragment view) {
        super(view);
        model = new AttendanceStatisticsModel();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        super.subscribe();

        jobNumber = SharedPreferencesUtil.getStoreJobNumber(mView.getActivity());
        loadAllStatisticsOnThePage();
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

    @Override
    public void loadAllStatisticsOnThePage() {

        loadAttendanceProfile();
        loadOverallAttendanceStatistics();
    }

    @Override
    public void loadAttendanceProfile() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        model.loadAttendanceProfile(jobNumber)
                .compose(RxScheduler.<AttendanceProfileBean>Flo_io_main())
                .as(mView.<AttendanceProfileBean>bindAutoDispose())
                .subscribe(new Consumer<AttendanceProfileBean>() {
                    @Override
                    public void accept(AttendanceProfileBean bean) throws Exception {
                        mView.showAttendanceProfile(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void loadOverallAttendanceStatistics() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        model.loadOverallAttendanceStatistics(jobNumber)
                .compose(RxScheduler.<BaseArrayBean<TimeAndNumberOfPeopleBean>>Flo_io_main())
                .as(mView.<BaseArrayBean<TimeAndNumberOfPeopleBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseArrayBean<TimeAndNumberOfPeopleBean>>() {
                    @Override
                    public void accept(BaseArrayBean<TimeAndNumberOfPeopleBean> bean) throws Exception {
                        mView.showOverallAttendanceLineChart(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void loadClassAttendanceStatistics() {

    }

    @Override
    public void loadProblemStudentStatistics() {

    }
}
