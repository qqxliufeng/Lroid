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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.FoodMenuBean;
import com.android.lf.lroid.p.MobApiPresenter;

import java.util.ArrayList;
import java.util.HashMap;

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

    private MyViewPagerAdapter myViewPagerAdapter;

    private int menuIndex = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_food_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int tempIndex=0;
        switch (item.getItemId()){
            case R.id.id_menu_food_one:
                tempIndex = 0;
                break;
            case R.id.id_menu_food_two:
                tempIndex = 1;
                break;
            case R.id.id_menu_food_three:
                tempIndex = 2;
                break;
            case R.id.id_menu_food_four:
                tempIndex = 3;
                break;
            case R.id.id_menu_food_five:
                tempIndex = 4;
                break;
        }
        if (tempIndex == menuIndex){
            return super.onOptionsItemSelected(item);
        }
        menuIndex = tempIndex;
        if (myViewPagerAdapter!=null && !titles.isEmpty()) {
            myViewPagerAdapter.notifyDataSetChanged();
            mTabTitle.post(new Runnable() {
                @Override
                public void run() {
                    mContentContainer.setCurrentItem(0);
                    mTabTitle.setScrollPosition(0,0.0f,false);
                }
            });
        }
        return super.onOptionsItemSelected(item);
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
        mProgressDialog = ProgressDialog.show(mContext,null,"正在加载菜单……");
    }

    @Override
    public void onRequestEnd(int requestID) {
        mProgressDialog.dismiss();
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (result!=null) {
            HashMap<String,Object> resultMap = (HashMap<String, Object>) ((HashMap<String,Object>)result).get("result");
            ArrayList<HashMap<String,Object>> childs = (ArrayList<HashMap<String, Object>>) resultMap.get("childs");
            if ((childs!=null && !childs.isEmpty())) {
                for (HashMap<String,Object> map :childs) {
                    HashMap<String,Object> childMap = (HashMap<String, Object>) map.get("categoryInfo");
                    FoodMenuBean foodMenuBean = new FoodMenuBean();
                    foodMenuBean.setParentId((String) childMap.get("parentId"));
                    foodMenuBean.setCtgId((String) childMap.get("ctgId"));
                    foodMenuBean.setName((String) childMap.get("name"));
                    ArrayList<FoodMenuBean> childMenuTempList = new ArrayList<>();
                    ArrayList<HashMap<String,Object>> childMenuList = (ArrayList<HashMap<String, Object>>) map.get("childs");
                    if (childMenuList!=null && !childMenuList.isEmpty()){
                        for (HashMap<String,Object> childMenuMap : childMenuList){
                            HashMap<String,Object> categoryInfo = (HashMap<String, Object>) childMenuMap.get("categoryInfo");
                            FoodMenuBean childFoodMenuBean = new FoodMenuBean();
                            childFoodMenuBean.setName((String) categoryInfo.get("name"));
                            childFoodMenuBean.setCtgId((String) categoryInfo.get("ctgId"));
                            childFoodMenuBean.setParentId((String) categoryInfo.get("parentId"));
                            childMenuTempList.add(childFoodMenuBean);
                        }
                    }
                    foodMenuBean.setChilds(childMenuTempList);
                    titles.add(foodMenuBean);
                }
                myViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
                mContentContainer.setAdapter(myViewPagerAdapter);
                mTabTitle.setupWithViewPager(mContentContainer);
            }
        }
    }

    class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FoodContentFragment.newInstance(titles.get(menuIndex).getChilds().get(position).getCtgId());
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
