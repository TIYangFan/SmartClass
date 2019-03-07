package com.example.smartclass.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import androidx.annotation.RequiresApi;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by YangFan
 * On 2019/2/24
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public abstract class BaseActivity extends AppCompatActivity {


    private Unbinder unbinder;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化视图
     */
    public abstract void initView();
}
