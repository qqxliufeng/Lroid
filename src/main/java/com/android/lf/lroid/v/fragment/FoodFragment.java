package com.android.lf.lroid.v.fragment;

import android.graphics.Color;
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
    public <T> void onRequestSuccess(int requestID, T result) {
//        {result=
//                {categoryInfo=
//                        {name=全部菜谱, ctgId=0010001001},
//                        childs=[
//                        {categoryInfo=
//                                {parentId=0010001001, name=按菜品选择菜谱, ctgId=0010001002},
//                                childs=[
//                                {categoryInfo=
//                                        {parentId=0010001002, name=荤菜, ctgId=0010001007}}, {categoryInfo={parentId=0010001002, name=素菜, ctgId=0010001008}}, {categoryInfo={parentId=0010001002, name=汤粥, ctgId=0010001009}}, {categoryInfo={parentId=0010001002, name=西点, ctgId=0010001010}}, {categoryInfo={parentId=0010001002, name=主食, ctgId=0010001011}}, {categoryInfo={parentId=0010001002, name=饮品, ctgId=0010001012}}, {categoryInfo={parentId=0010001002, name=便当, ctgId=0010001013}}, {categoryInfo={parentId=0010001002, name=小吃, ctgId=0010001014}}]}, {categoryInfo={parentId=0010001001, name=按工艺选择菜谱, ctgId=0010001003}, childs=[{categoryInfo={parentId=0010001003, name=红烧, ctgId=0010001015}}, {categoryInfo={parentId=0010001003, name=炒, ctgId=0010001016}}, {categoryInfo={parentId=0010001003, name=煎, ctgId=0010001017}}, {categoryInfo={parentId=0010001003, name=炸, ctgId=0010001018}}, {categoryInfo={parentId=0010001003, name=焖, ctgId=0010001019}}, {categoryInfo={parentId=0010001003, name=炖, ctgId=0010001020}}, {categoryInfo={parentId=0010001003, name=蒸, ctgId=0010001021}}, {categoryInfo={parentId=0010001003, name=烩, ctgId=0010001022}}, {categoryInfo={parentId=0010001003, name=熏, ctgId=0010001023}}, {categoryInfo={parentId=0010001003, name=腌, ctgId=0010001024}}, {categoryInfo={parentId=0010001003, name=煮, ctgId=0010001025}}, {categoryInfo={parentId=0010001003, name=炝, ctgId=0010001026}}, {categoryInfo={parentId=0010001003, name=卤, ctgId=0010001027}}, {categoryInfo={parentId=0010001003, name=拌, ctgId=0010001028}}, {categoryInfo={parentId=0010001003, name=烤, ctgId=0010001029}}]}, {categoryInfo={parentId=0010001001, name=按菜系选择菜谱, ctgId=0010001004}, childs=[{categoryInfo={parentId=0010001004, name=鲁菜, ctgId=0010001030}}, {categoryInfo={parentId=0010001004, name=川菜, ctgId=0010001031}}, {categoryInfo={parentId=0010001004, name=粤菜, ctgId=0010001032}}, {categoryInfo={parentId=0010001004, name=闽菜, ctgId=0010001033}}, {categoryInfo={parentId=0010001004, name=浙菜, ctgId=0010001034}}, {categoryInfo={parentId=0010001004, name=湘菜, ctgId=0010001035}}, {categoryInfo={parentId=0010001004, name=上海菜, ctgId=0010001036}}, {categoryInfo={parentId=0010001004, name=徽菜, ctgId=0010001037}}, {categoryInfo={parentId=0010001004, name=京菜, ctgId=0010001038}}, {categoryInfo={parentId=0010001004, name=东北菜, ctgId=0010001039}}, {categoryInfo={parentId=0010001004, name=西北菜, ctgId=0010001040}}, {categoryInfo={parentId=0010001004, name=客家菜, ctgId=0010001041}}, {categoryInfo={parentId=0010001004, name=台湾美食, ctgId=0010001042}}, {categoryInfo={parentId=0010001004, name=泰国菜, ctgId=0010001043}}, {categoryInfo={parentId=0010001004, name=日本料理, ctgId=0010001044}}, {categoryInfo={parentId=0010001004, name=韩国料理, ctgId=0010001045}}, {categoryInfo={parentId=0010001004, name=西餐, ctgId=0010001046}}]}, {categoryInfo={parentId=0010001001, name=按人群选择菜谱, ctgId=0010001005}, childs=[{categoryInfo={parentId=0010001005, name=孕妇食谱, ctgId=0010001047}}, {categoryInfo={parentId=0010001005, name=婴幼食谱, ctgId=0010001048}}, {categoryInfo={parentId=0010001005, name=儿童食谱, ctgId=0010001049}}, {categoryInfo={parentId=0010001005, name=懒人食谱, ctgId=0010001050}}, {categoryInfo={parentId=0010001005, name=宵夜, ctgId=0010001051}}, {categoryInfo={parentId=0010001005, name=素食, ctgId=0010001052}}, {categoryInfo={parentId=0010001005, name=产妇食谱, ctgId=0010001053}}, {categoryInfo={parentId=0010001005, name=二人世界, ctgId=0010001054}}, {categoryInfo={parentId=0010001005, name=下午茶, ctgId=0010001055}}]}, {categoryInfo={parentId=0010001001, name=按功能选择菜谱, ctgId=0010001006}, childs=[{categoryInfo={parentId=0010001
//                006, name=减肥, ctgId=0010001056}}, {categoryInfo={parentId=0010001006, name=便秘, ctgId=0010001057}}, {categoryInfo={parentId=0010001006, name=养胃, ctgId=0010001058}}, {categoryInfo={parentId=0010001006, name=滋阴, ctgId=0010001059}}, {categoryInfo={parentId=0010001006, name=补阳, ctgId=0010001060}}, {categoryInfo={parentId=0010001006, name=月经不调, ctgId=0010001061}}, {categoryInfo={parentId=0010001006, name=美容, ctgId=0010001062}}, {categoryInfo={parentId=0010001006, name=养生, ctgId=0010001063}}, {categoryInfo={parentId=0010001006, name=贫血, ctgId=0010001064}}, {categoryInfo={parentId=0010001006, name=润肺, ctgId=0010001065}}]}]}}

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
            return FoodContentFragment.newInstance();
        }

        @Override
        public int getCount() {
            return titles.get(0).getChilds().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(0).getChilds().get(position).getName();
        }

    }

}
