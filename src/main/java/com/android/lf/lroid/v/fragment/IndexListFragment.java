package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.InjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.p.common.CommonPresenter;
import com.android.lf.lroid.v.views.LroidListView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/9.
 */

public class IndexListFragment extends BaseFragment {

    @Inject
    CommonPresenter commonPresenter;

    @BindView(R.id.id_llv_fragment_index_content)
    LroidListView mListView;

    @BindView(R.id.id_pb_fragment_index_progress)
    ProgressBar mProgressBar;

    private ArrayList<String> arrayList = new ArrayList<String>(){
        {
            for (int i = 0; i < 50; i++) {
                add("item + "+i);
            }
        }
    };

    public static IndexListFragment newInstance() {
        return new IndexListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index_content_layout;
    }

    @Override
    protected void initView(View view) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        mListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    private class MyLroidListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}
