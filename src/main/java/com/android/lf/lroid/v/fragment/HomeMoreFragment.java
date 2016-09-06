package com.android.lf.lroid.v.fragment;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.lf.lroid.R;

/**
 * Created by feng on 2016/9/2.
 */

public class HomeMoreFragment extends BaseFragment {


    public static HomeMoreFragment newInstance() {
        return new HomeMoreFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_layout;
    }

    @Override
    protected void initView(final View view) {
        view.findViewById(R.id.id_fb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"点击",Snackbar.LENGTH_LONG).setAction("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
            }
        });
    }

    @Override
    protected void setComponent() {
    }
}
