package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.p.common.CommonPresenter;
import com.android.lf.lroid.v.activity.HomeActivity;
import com.android.lf.lroid.v.views.AutoScrollViewPager;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/9.
 */

public class IndexTopFragment extends BaseFragment {

    public static IndexTopFragment newInstance() {
        IndexTopFragment fragment = new IndexTopFragment();
        return fragment;
    }

    @Inject
    CommonPresenter commonPresenter;
    private ProgressDialog progressDialog;

    @BindView(R.id.id_vp_fragment_index_banner)
    AutoScrollViewPager vp_banner;

    private ArrayList<String> imageUrls = new ArrayList<String>(){
        {
            add("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
            add("http://m2.quanjing.com/2m/alamyrf005/b1fw89.jpg");
            add("http://image.tianjimedia.com/uploadImages/2015/129/56/J63MI042Z4P8.jpg");
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index_top_layout;
    }

    @Override
    protected void initView(View view) {
        commonPresenter.setPresentListener(this);
        vp_banner.setAdapter(new BannerViewPagerAdapter(getChildFragmentManager()));
        vp_banner.setCycle(true);
        vp_banner.setDirection(AutoScrollViewPager.RIGHT);
        vp_banner.setInterval(3000);
        vp_banner.setBorderAnimation(true);
        vp_banner.setScrollDurationFactor(4.0);
        vp_banner.setOffscreenPageLimit(imageUrls.size());//设置缓存数量
        vp_banner.startAutoScroll();
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestStart(int requestID) {
        progressDialog = ProgressDialog.show(mContext,null,"正在加载……");
    }

    @Override
    public void onRequestEnd(int requestID) {
        progressDialog.dismiss();
    }

    @Override
    public void onRequestSuccess(int requestID, String result) {
    }

    private class BannerViewPagerAdapter extends FragmentStatePagerAdapter {


        public BannerViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageRequestFragment.newInstance(imageUrls.get(position));
        }

        @Override
        public int getCount() {
            return imageUrls.size();
        }
    }
}
