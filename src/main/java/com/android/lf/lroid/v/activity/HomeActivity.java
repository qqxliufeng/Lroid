package com.android.lf.lroid.v.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.lf.lroid.R;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.android.lf.lroid.v.fragment.LroidBaseFragment;
import com.android.lf.lroid.v.fragment.HomeIndexFragment;
import com.android.lf.lroid.v.fragment.HomeMineFragment;
import com.android.lf.lroid.v.fragment.HomeMoreFragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

/**
 * Created by feng on 2016/9/1.
 */

public class HomeActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<LroidBaseFragment> fragmentList = new ArrayList<>();

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
                .addItem(new BottomNavigationItem(R.drawable.ic_jie_qi_white_24dp, "节气").setActiveColorResource(android.R.color.holo_orange_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_jie_ri_white_24dp, "节日").setActiveColorResource(android.R.color.holo_blue_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_mine_white_24dp, "我的").setActiveColorResource(android.R.color.holo_green_light))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setTabSelectedListener(this);
        setSwipeBackEnable(false);
        PreferenceUtils.setPrefInt(this,"currentCode", MethodUtils.getCurrentCode(this));
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
        fragmentList.add(HomeIndexFragment.newInstance());
        fragmentList.add(HomeMoreFragment.newInstance());
        fragmentList.add(HomeMineFragment.newInstance());
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
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
