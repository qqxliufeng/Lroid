package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.p.common.CommonPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/2.
 */

public class IndexFragment extends BaseFragment {

    @Inject
    CommonPresenter commonPresenter;

    private ProgressDialog progressDialog;

    @BindView(R.id.id_tv_fragment_content)
    TextView tv_content;
    @BindView(R.id.id_tl_app_top_bar)
    Toolbar tl_bar;


    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index_layout;
    }

    @Override
    protected void initView(View view) {
        tl_bar.setTitle("首页");

        commonPresenter.setPresentListener(this);
        view.findViewById(R.id.id_bt_fragment_index_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonPresenter.requestData(0x0,0,10);
            }
        });
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestStart(int requestID) {
        progressDialog = ProgressDialog.show(mContext,null,"正在加载……");
    }

    @Override
    public void onRequestEnd(int requestID) {
        progressDialog.dismiss();
    }

    @Override
    public void onRequestSuccess(int requestID, String result) {
        tv_content.setText(result);
    }
}
