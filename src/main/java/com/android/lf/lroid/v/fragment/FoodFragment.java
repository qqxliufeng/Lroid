package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.FoodMenuBean;
import com.android.lf.lroid.p.MobApiPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by liufeng on 16/10/23.
 */

public class FoodFragment extends LroidBaseFragment {

    public static FoodFragment newInstance() {
        return new FoodFragment();
    }

    @Inject
    MobApiPresenter mMobApiPresenter;
    @BindView(R.id.id_tl_fragment_food_tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.id_vp_fragment_food_container)
    ViewPager mContentContainer;

    private ArrayList<FoodMenuBean> titles = new ArrayList<>();

    private int menuIndex = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_food_layout;
    }

    @Override
    protected void initView(View view) {
        mTabTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabTitle.setTabTextColors(Color.BLACK, ContextCompat.getColor(mContext, R.color.colorPrimary));
        mMobApiPresenter.setFragment(this);
        mMobApiPresenter.getData(MobApiPresenter.REQUEST_CODE_FOOD_FOR_MENU);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestStart(int requestID) {
        mProgressDialog = ProgressDialog.show(mContext, null, "正在加载菜单……");
    }

    @Override
    public void onRequestEnd(int requestID) {
        mProgressDialog.dismiss();
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (result != null) {
            switch (requestID) {
                case 0x1:
                    mMobApiPresenter.doSomethingWithRxJavaMap(MobApiPresenter.PARSE_FOOD_MENU_FLAG, result);
                    break;
                case MobApiPresenter.PARSE_FOOD_MENU_FLAG:
                    titles.addAll((ArrayList<FoodMenuBean>) result);
                    setAdapter();
                    break;
            }
        }
    }

    private void setAdapter() {
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
        mContentContainer.setAdapter(myViewPagerAdapter);
        mContentContainer.setOffscreenPageLimit(myViewPagerAdapter.getCount());
        mTabTitle.setupWithViewPager(mContentContainer);
    }

    class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FoodListFragment.newInstance(titles.get(menuIndex).getChilds().get(position).getCtgId());
        }

        @Override
        public int getCount() {
            return titles.get(menuIndex).getChilds().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(menuIndex).getChilds().get(position).getName();
        }

    }
}
