package com.example.smartclass.presenter;


import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.bean.AttendanceAndStatusBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassRecentRecordBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;
import com.example.smartclass.contract.ClassRecentRecordContract;
import com.example.smartclass.model.ClassRecentRecordModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.view.ClassRecentRecordFragment;

import io.reactivex.functions.Consumer;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassRecentRecordPresenter extends BaseMvpPresenter<ClassRecentRecordFragment> implements ClassRecentRecordContract.Presenter {

    private ClassRecentRecordContract.Model model;
    private String jobNumber;

    public ClassRecentRecordPresenter(ClassRecentRecordFragment view) {
        super(view);
        model = new ClassRecentRecordModel();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        super.subscribe();

        loadJobNumber();
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
    public void loadClassRecentRecord() {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.loadClassRecentRecord(jobNumber)
                .compose(RxScheduler.<BaseArrayBean<ClassRecentRecordBean>>Flo_io_main())
                .as(mView.<BaseArrayBean<ClassRecentRecordBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseArrayBean<ClassRecentRecordBean>>() {
                    @Override
                    public void accept(BaseArrayBean<ClassRecentRecordBean> bean) throws Exception {

                        mView.showClassRecentRecord(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void loadClassRecentRecordDetails(final String classId, final int groupPosition) {

        model.loadClassRecentRecordDetails(jobNumber, classId)
                .compose(RxScheduler.<AttendanceAndStatusBean>Flo_io_main())
                .as(mView.<AttendanceAndStatusBean>bindAutoDispose())
                .subscribe(new Consumer<AttendanceAndStatusBean>() {
                    @Override
                    public void accept(AttendanceAndStatusBean bean) throws Exception {
                        loadProblemStudentStatistics(classId, groupPosition, bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });

    }

    @Override
    public void loadProblemStudentStatistics(String classId, final int groupPosition, final AttendanceAndStatusBean bean) {

        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }

        model.loadProblemStudentStatistics(jobNumber, classId)
                .compose(RxScheduler.<StudentsWithAttendanceProblemsBean>Flo_io_main())
                .as(mView.<StudentsWithAttendanceProblemsBean>bindAutoDispose())
                .subscribe(new Consumer<StudentsWithAttendanceProblemsBean>() {
                    @Override
                    public void accept(StudentsWithAttendanceProblemsBean biBean) throws Exception {
                        mView.showClassRecentRecordDetails(bean, biBean, groupPosition);
                        //mView.showClassRecentRecordDetails(bean, biBean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

}
