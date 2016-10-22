package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by feng on 2016/10/22.
 */

public class HealthSearchFragment extends LroidBaseFragment {

    public static HealthSearchFragment newInstance() {
        return new HealthSearchFragment();
    }

    @BindView(R.id.id_fl_fragment_health_search_container)
    TagFlowLayout mTagContainer;


    private ArrayList<String> tags = new ArrayList<String>(){
        {
            add("苹果");
            add("苹果苹果");
            add("苹果苹果");
            add("苹果苹果苹果");
            add("苹果");
            add("苹果");
            add("苹果");
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_health_search_layout;
    }

    @Override
    protected void initView(View view) {
        mTagContainer.setMaxSelectCount(1);
        mTagContainer.setAdapter(new TagAdapter<String>(tags)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) View.inflate(mContext,R.layout.inner_fragment_search_tag_layout,null);
                tv.setText(s);
                return tv;
            }
        });

        mTagContainer.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), tags.get(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    protected void setComponent() {

    }
}
