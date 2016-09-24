package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.JieQiBean;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.m.data.JieQiData;
import com.android.lf.lroid.p.common.CommonPresenter;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.views.AutoScrollViewPager;
import com.android.lf.lroid.v.views.IndicatorView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/9.
 */

public class IndexTopFragment extends LroidBaseFragment implements ViewPager.OnPageChangeListener {

    private int currentItem;

    public static IndexTopFragment newInstance() {
        IndexTopFragment fragment = new IndexTopFragment();
        return fragment;
    }

    @Inject
    CommonPresenter commonPresenter;

    private ProgressDialog progressDialog;

    @BindView(R.id.id_vp_fragment_index_banner)
    AutoScrollViewPager vp_banner;

    @BindView(R.id.id_ll_fragment_index_indicator)
    LinearLayout mIndicatorContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index_top_layout;
    }

    @Override
    protected void initView(View view) {
        commonPresenter.setPresentListener(this);
        initIndicator();
        vp_banner.setAdapter(new BannerViewPagerAdapter(getChildFragmentManager()));
        vp_banner.setCycle(true);
        vp_banner.setDirection(AutoScrollViewPager.RIGHT);
        vp_banner.setInterval(3000);
        vp_banner.setBorderAnimation(false);
        vp_banner.setBorderAnimation(true);
        vp_banner.setScrollDurationFactor(4.0);
        vp_banner.setOffscreenPageLimit(JieQiData.getInstance().getJieQiBanners().length);//设置缓存数量
        vp_banner.startAutoScroll();
        vp_banner.addOnPageChangeListener(this);
    }

    private void initIndicator() {
        for (int i = 0;i<JieQiData.getInstance().getJieQiBanners().length;i++){
            IndicatorView indicatorView = new IndicatorView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,6,getResources().getDisplayMetrics()),(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,6,getResources().getDisplayMetrics()));
            params.leftMargin = 5;
            params.rightMargin = 5;
            indicatorView.setLayoutParams(params);
            indicatorView.setIndicatorColor(Color.RED);
            mIndicatorContainer.addView(indicatorView,i);
            if (i == 0){
                indicatorView.setSelect(true);
            }
        }
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
    public <T> void onRequestSuccess(int requestID, T result) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        int count = mIndicatorContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            if (position % count == i) {
                ((IndicatorView) mIndicatorContainer.getChildAt(i)).setSelect(true);
            }else {
                ((IndicatorView) mIndicatorContainer.getChildAt(i)).setSelect(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private class BannerViewPagerAdapter extends FragmentStatePagerAdapter {


        public BannerViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageRequestFragment.newInstance(JieQiData.getInstance().getJieQiBanners()[position%JieQiData.getInstance().getJieQiBanners().length]);
        }

        @Override
        public int getCount() {
//            return JieQiData.getInstance().getJieQiBanners().length;
            return Integer.MAX_VALUE;
        }
    }

    public void onChildViewClick(){
        if (TextUtils.isEmpty(UserBean.getInstance().getName())){
            UserBean.getInstance().setOnUserLoginSuccessListener(this);
            MethodUtils.startFragmentsActivity(mContext, "登录", FragmentContainerActivity.LOGIN_FRAGMENT);
        }else {
            startDetail();
        }
    }

    private void startDetail() {
        currentItem = vp_banner.getCurrentItem()% JieQiData.getInstance().getJieQiBanners().length;
        JieQiBean jieQiBean = JieQiData.getInstance().getJieQiBeanArrayList().get(currentItem);
        Bundle bundle = new Bundle();
        bundle.putString(WebContentFragment.WEB_LOAD_URL,jieQiBean.getDetail_info_url());
        MethodUtils.startFragmentsActivity(mContext,jieQiBean.getName(), FragmentContainerActivity.WEB_CONTENT_CONTAINER_FLAG,bundle);
    }


    @Override
    public void onUserLoginSuccess() {
        startDetail();
    }
}
