package com.example.smartclass.contract;

import android.content.Context;

import com.example.smartclass.base.BaseView;
import com.example.smartclass.bean.AttendanceProfileBean;

import io.reactivex.Flowable;

/**
 * Created by YangFan
 * On 2019/2/23
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public interface CurrentClassContract {

    interface Model {

        String loadJobNumber(Context context);

        Flowable<AttendanceProfileBean> loadAttendanceProfile(String jobNumber);
    }

    interface View extends BaseView {

        void showAttendanceProfile(AttendanceProfileBean bean);
    }

    interface Presenter {

        void loadJobNumber();

        void loadAttendanceProfile();
    }
}
