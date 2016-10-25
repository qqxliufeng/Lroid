package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by feng on 2016/10/25.
 */

public class TestFoodFragment extends LroidBaseFragment {

    public static final String INDEX = "index";

    public static TestFoodFragment newInstance(String index) {
        Bundle args = new Bundle();
        args.putString(INDEX,index);
        TestFoodFragment fragment = new TestFoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(android.R.id.text1)
    TextView textView;

    @Override
    protected int getLayoutId() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    protected void initView(View view) {
        textView.setText(getArguments().getString(INDEX)+"  " + this.toString());
    }

    @Override
    protected void setComponent() {

    }
}
