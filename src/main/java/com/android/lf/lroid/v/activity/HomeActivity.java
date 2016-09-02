package com.android.lf.lroid.v.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.p.common.HomePresenter;
import com.android.lf.lroid.v.fragment.BaseFragment;
import com.android.lf.lroid.v.fragment.IndexFragment;
import com.android.lf.lroid.v.fragment.MineFragment;
import com.android.lf.lroid.v.fragment.MoreFragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by feng on 2016/9/1.
 */

public class HomeActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_layout;
    }

    @Override
    public void setPresentComponent() {
    }

    @Override
    public void initView() {
        initFragmentList();
        setDefaultFragment();
        initBottomNavigationBar();
    }

    private void initBottomNavigationBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.id_bnb_bottom_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Nearby").setActiveColorResource(android.R.color.holo_orange_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "Find").setActiveColorResource(android.R.color.holo_blue_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "Categories").setActiveColorResource(android.R.color.holo_green_light))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i == 0) {
                transaction.show(fragmentList.get(i));
            }else {
                transaction.hide(fragmentList.get(i));
            }
        }
        transaction.commit();
    }

    private void initFragmentList() {
        fragmentList.add(IndexFragment.newInstance());
        fragmentList.add(MoreFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            transaction.add(R.id.id_fl_activity_home_container, fragmentList.get(i));
        }
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
       /* FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.id_fl_activity_home_container, fragmentList.get(position));
        transaction.commit();*/
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i == position) {
                transaction.show(fragmentList.get(i));
            }else {
                transaction.hide(fragmentList.get(i));
            }
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
