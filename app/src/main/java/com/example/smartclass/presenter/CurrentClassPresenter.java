package com.example.smartclass.presenter;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.contract.CurrentClassContract;
import com.example.smartclass.model.CurrentClassModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.view.CurrentClassFragment;

import io.reactivex.functions.Consumer;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class CurrentClassPresenter extends BaseMvpPresenter<CurrentClassFragment> implements CurrentClassContract.Presenter {

    private CurrentClassContract.Model model;
    private String jobNumber;

    public CurrentClassPresenter(CurrentClassFragment view) {
        super(view);
        model = new CurrentClassModel();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        super.subscribe();

        mView.showLoading();
        loadJobNumber();
        loadAttendanceProfile();
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
}
