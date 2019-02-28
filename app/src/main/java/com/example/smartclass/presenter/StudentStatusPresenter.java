package com.example.smartclass.presenter;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ConcentrationDistributionBean;
import com.example.smartclass.bean.TimeAndConcentrationBean;
import com.example.smartclass.contract.StudentStatusContract;
import com.example.smartclass.model.StudentStatusModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.util.SharedPreferencesUtil;
import com.example.smartclass.view.StudentStatusFragment;

import io.reactivex.functions.Consumer;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class StudentStatusPresenter extends BaseMvpPresenter<StudentStatusFragment> implements StudentStatusContract.Presenter {

    private StudentStatusContract.Model model;
    private String jobNumber;

    public StudentStatusPresenter(StudentStatusFragment view) {
        super(view);
        model = new StudentStatusModel();
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

        loadStateChangeStatistics();
        loadConcentrationDistributionStatistics();
    }

    @Override
    public void loadCurrentStatusStatistics() {

    }

    @Override
    public void loadStateChangeStatistics() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.loadStateChangeStatistics(jobNumber)
                .compose(RxScheduler.<BaseArrayBean<TimeAndConcentrationBean>>Flo_io_main())
                .as(mView.<BaseArrayBean<TimeAndConcentrationBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseArrayBean<TimeAndConcentrationBean>>() {
                    @Override
                    public void accept(BaseArrayBean<TimeAndConcentrationBean> bean) throws Exception {
                        mView.showStateChangeLineChart(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void loadConcentrationDistributionStatistics() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.loadConcentrationDistributionStatistics(jobNumber)
                .compose(RxScheduler.<ConcentrationDistributionBean>Flo_io_main())
                .as(mView.<ConcentrationDistributionBean>bindAutoDispose())
                .subscribe(new Consumer<ConcentrationDistributionBean>() {
                    @Override
                    public void accept(ConcentrationDistributionBean bean) throws Exception {
                        mView.showConcentrationDistributionPieChart(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void loadUnfocusedStudentStatistics() {

    }
}
