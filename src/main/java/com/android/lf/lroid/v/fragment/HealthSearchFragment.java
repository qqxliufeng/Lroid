package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by feng on 2016/10/22.
 */

public class HealthSearchFragment extends LroidBaseFragment {

    public static HealthSearchFragment newInstance() {
        return new HealthSearchFragment();
    }

    @BindView(R.id.id_fl_fragment_health_search_container)
    TagFlowLayout mTagContainer;
    @BindView(R.id.id_et_fragment_health_search_content)
    EditText mContent;
    @BindView(R.id.id_ll_fragment_health_search_root_view)
    LinearLayout mRootView;

    private ArrayList<String> tags = new ArrayList<String>() {
        {
            add("大米");
            add("小麦");
            add("小米");
            add("大豆");
            add("芹菜");
            add("胡萝卜");
            add("白菜");
            add("茄子");
            add("西红柿");
            add("黄瓜");
            add("辣椒");
            add("鸡肉");
            add("猪肉");
            add("鸡蛋");
            add("牛奶");
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_health_search_layout;
    }

    @Override
    protected void initView(View view) {
        mTagContainer.setMaxSelectCount(1);
        mTagContainer.setAdapter(new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) View.inflate(mContext, R.layout.inner_fragment_search_tag_layout, null);
                tv.setText(s);
                return tv;
            }
        });

        mTagContainer.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                openStartResult(tags.get(position).trim());
                return true;
            }
        });
    }

    @Override
    protected void setComponent() {
    }

    @OnClick(R.id.id_bt_fragment_health_search_on_search)
    public void onSearch(){
        String value = mContent.getText().toString().trim();
        if (TextUtils.isEmpty(value)) {
            Snackbar.make(mRootView,mContent.getHint(),Snackbar.LENGTH_SHORT).show();
            return;
        }
        openStartResult(value);
    }

    private void openStartResult(String value) {
        Bundle bundle = new Bundle();
        bundle.putString(HealthSearchResultFragment.KEYWORD, value);
        MethodUtils.startFragmentsActivity(mContext, value, FragmentContainerActivity.HEALTH_SEARCH_RESULT_FLAG,bundle);
    }

}
