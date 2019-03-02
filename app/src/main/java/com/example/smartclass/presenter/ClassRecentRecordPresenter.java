package com.example.smartclass.presenter;

import android.util.Log;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.bean.AttendanceAndStatusBean;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.BaseArrayBean;
import com.example.smartclass.bean.ClassRecentRecordBean;
import com.example.smartclass.contract.ClassRecentRecordContract;
import com.example.smartclass.model.ClassRecentRecordModel;
import com.example.smartclass.net.RxScheduler;
import com.example.smartclass.view.ClassRecentRecordFragment;

import org.reactivestreams.Publisher;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

        mView.showLoading();
        loadJobNumber();
        loadClassRecentRecord();
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
                .doOnNext(new Consumer<BaseArrayBean<ClassRecentRecordBean>>() {
                    @Override
                    public void accept(BaseArrayBean<ClassRecentRecordBean> classRecentRecordBeanBaseArrayBean) throws Exception {
                        Log.e("FIRST", "first");
                        mView.showClassRecentRecord(classRecentRecordBeanBaseArrayBean);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<BaseArrayBean<ClassRecentRecordBean>, Flowable<AttendanceAndStatusBean>>() {
                    @Override
                    public Flowable<AttendanceAndStatusBean> apply(BaseArrayBean<ClassRecentRecordBean> classRecentRecordBeanBaseArrayBean) throws Exception {

                        Log.e("FIRST", "second");
                        if (classRecentRecordBeanBaseArrayBean != null){
                            ArrayList<ClassRecentRecordBean> arrayList = classRecentRecordBeanBaseArrayBean.getArrayList();
                            for(int i = 0; i < arrayList.size(); i++){
                                return model.loadClassRecentRecordDetails(jobNumber, String.valueOf(arrayList.get(i).getClass_id()));
                            }
                        }
                        return null;
                    }
                })
                .compose(RxScheduler.<AttendanceAndStatusBean>Flo_io_main())
                .as(mView.<AttendanceAndStatusBean>bindAutoDispose())
                .subscribe(new Consumer<AttendanceAndStatusBean>() {
                    @Override
                    public void accept(AttendanceAndStatusBean bean) throws Exception {

                        Log.e("FIRST", "third");
                        mView.showClassRecentRecordDetails(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }
}
