package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.HistoryTodayBean;
import com.android.lf.lroid.p.MobApiPresenter;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.adapter.HistoryTodayAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;
import com.yalantis.ucrop.UCrop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/10/8.
 */

public class HistoryTodayFragment extends BaseRecycleViewFragment<HistoryTodayBean> {

    /**
     *通过Dagger2 DI 注入 presenter，用来请求数据
     */
    @Inject
    MobApiPresenter mMobApiPresenter;

    public static HistoryTodayFragment newInstance() {
        return new HistoryTodayFragment();
    }

    /**
     * @return 返回一个 继承自BaseAdapter 的适配器
     */
    @Override
    protected BaseQuickAdapter<HistoryTodayBean> createAdapter() {
        return new HistoryTodayAdapter(R.layout.adapter_history_today_item_layout, mArrayList);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_history_today_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!mArrayList.isEmpty()) {
            switch (item.getItemId()) {
                case R.id.menu_fragment_history_today_one:
                    Collections.sort(mArrayList, new HistorySort(true));
                    break;
                case R.id.menu_fragment_history_today_two:
                    Collections.sort(mArrayList, new HistorySort(false));
                    break;
            }
            mBaseQuickAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        mMobApiPresenter.setFragment(this);
        mMobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_HISTORY_TODAY, MethodUtils.getCurrentTime("MMdd"));
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        super.onRequestSuccess(requestID, result);
        if (result != null) {
            String code = (String) ((Map) result).get("retCode");
            if ("200".equals(code)) {
                ArrayList<Map<String, String>> results = (ArrayList<Map<String, String>>) ((Map) result).get("result");
                ArrayList<HistoryTodayBean> tempList = new ArrayList<>();
                for (Map<String, String> mapBean : results) {
                    HistoryTodayBean historyTodayBean = new HistoryTodayBean();
                    historyTodayBean.setTime(mapBean.get("date"));
                    historyTodayBean.setContent(mapBean.get("event"));
                    historyTodayBean.setTitle(mapBean.get("title"));
                    historyTodayBean.setAll(false);
                    tempList.add(historyTodayBean);
                }
                Collections.sort(tempList, new HistorySort(true));
                mBaseQuickAdapter.addData(tempList);
            }
        }
    }

    @Override
    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(WebContentFragment.WEB_LOAD_URL, htmlFormat(mArrayList.get(i)));
        MethodUtils.startFragmentsActivity(mContext, mArrayList.get(i).getTitle(), FragmentContainerActivity.WEB_CONTENT_CONTAINER_FLAG, bundle);
    }

    private String htmlFormat(HistoryTodayBean historyTodayBean) {
        return "<h2 align=\"center\">" + historyTodayBean.getTitle() + "</h2>" +
                "<p align=\"center\">" + ((HistoryTodayAdapter) mBaseQuickAdapter).getDateFormat(historyTodayBean.getTime()) + "</p>" +
                "<p align=\"center\">" + historyTodayBean.getContent() + "</p>";
    }

    @Override
    public void onMyItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.id_tv_adapter_history_today_item_all) {
            HistoryTodayBean historyTodayBean = mArrayList.get(position);
            if (historyTodayBean.isAll()) {
                mArrayList.get(position).setAll(false);
            } else {
                mArrayList.get(position).setAll(true);
            }
            mBaseQuickAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onRefresh() {
        mMobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_HISTORY_TODAY, MethodUtils.getCurrentTime("MMdd"));
    }

    @Override
    protected void onLoadMore() {
        mMobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_HISTORY_TODAY, MethodUtils.getCurrentTime("MMdd"));
    }

    class HistorySort implements Comparator<HistoryTodayBean> {

        private boolean isDesc;

        public HistorySort(boolean isDesc) {
            this.isDesc = isDesc;
        }

        @Override
        public int compare(HistoryTodayBean o1, HistoryTodayBean o2) {
            if (isDesc) {
                return o2.getTime().compareTo(o1.getTime());
            } else {
                return o1.getTime().compareTo(o2.getTime());
            }
        }
    }

}
