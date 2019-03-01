package com.example.smartclass.presenter;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.contract.ClassRecentRecordContract;
import com.example.smartclass.model.ClassRecentRecordModel;
import com.example.smartclass.view.ClassRecentRecordFragment;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ClassRecentRecordPresenter extends BaseMvpPresenter<ClassRecentRecordFragment> implements ClassRecentRecordContract.Presenter {

    private ClassRecentRecordContract.Model model;

    public ClassRecentRecordPresenter(ClassRecentRecordFragment view) {
        super(view);
        model = new ClassRecentRecordModel();
        view.setPresenter(this);
    }

    @Override
    public void loadClassRecentRecord() {

    }
}
