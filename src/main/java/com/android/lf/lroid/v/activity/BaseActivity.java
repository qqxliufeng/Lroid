package com.android.lf.lroid.v.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.lf.lroid.interfaces.IPresentListener;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;


/**
 * Created by feng on 2016/8/1.
 */

public abstract class BaseActivity  extends AppCompatActivity implements IPresentListener,SwipeBackActivityBase {

    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //init butterKnife
        ButterKnife.bind(this);
        //设置代理
        setPresentComponent();
        initSwipBack();
        initView();
    }

    private void initSwipBack() {
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        setSwipeBackEnable(true);
    }

    public abstract int getLayoutId();

    public abstract void setPresentComponent();

    public abstract void initView();

    @Override
    public void onRequestStart(int requestID) {
    }

    @Override
    public void onRequestFail(int requestID,Throwable e) {

    }


    @Override
    public <T> void onRequestSuccess(int requestID, T result) {

    }

    @Override
    public void onRequestEnd(int requestID) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }


}
