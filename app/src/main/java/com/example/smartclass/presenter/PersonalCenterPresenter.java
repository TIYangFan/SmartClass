package com.example.smartclass.presenter;

import com.example.smartclass.base.BaseMvpPresenter;
import com.example.smartclass.contract.PersonalCenterContract;
import com.example.smartclass.model.PersonalCenterModel;
import com.example.smartclass.view.PersonalCenterFragment;

/**
 * Created by YangFan
 * On 2019/2/25
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class PersonalCenterPresenter extends BaseMvpPresenter<PersonalCenterFragment> implements PersonalCenterContract.Presenter {

    PersonalCenterContract.Model model;

    public PersonalCenterPresenter(PersonalCenterFragment view) {
        super(view);
        model = new PersonalCenterModel();
        view.setPresenter(this);
    }

    @Override
    public void loadUserInformation() {

    }
}
