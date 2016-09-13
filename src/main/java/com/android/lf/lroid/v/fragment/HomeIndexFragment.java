package com.android.lf.lroid.v.fragment;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ScrollView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.v.activity.HomeActivity;
import com.android.lf.lroid.v.views.LroidScrollView;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/2.
 */

public class HomeIndexFragment extends BaseFragment implements NestedScrollView.OnScrollChangeListener {

    @BindView(R.id.id_nsv_fragment_index_container)
    NestedScrollView mNestedScrollView;

    @BindView(R.id.id_tl_app_top_bar)
    Toolbar tl_bar;

    @BindView(R.id.id_fb_fragment_index_indicator)
    FloatingActionButton mFloatingActionButton;

    private float height;

    public static HomeIndexFragment newInstance() {
        return new HomeIndexFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index_layout;
    }

    @Override
    protected void initView(View view) {
        setView();
        initFragment();
        scrollTo();
    }

    public void scrollTo() {
        mNestedScrollView.post(new Runnable() {
            @Override
            public void run() {
                mNestedScrollView.smoothScrollTo(0, 0);
                mFloatingActionButton.setVisibility(View.GONE);
                tl_bar.setBackgroundColor(Color.argb((int) 0, 48, 63, 159));
                height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,180,getResources().getDisplayMetrics());
            }
        });
    }

    private void setView() {
        tl_bar.setTitle("首页");
        tl_bar.setTitleTextColor(Color.WHITE);
        ((HomeActivity) mContext).setSupportActionBar(tl_bar);
        mNestedScrollView.setOnScrollChangeListener(this);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNestedScrollView.smoothScrollTo(0, 0);
            }
        });
    }

    private void initFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_fl_fragment_index_top_container, IndexTopFragment.newInstance());
        fragmentTransaction.replace(R.id.id_fl_fragment_index_content_container, IndexListFragment.newInstance());
        fragmentTransaction.commit();
    }

    @Override
    protected void setComponent() {
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (oldScrollY > scrollY && oldScrollY - scrollY > 10){//向下
            mFloatingActionButton.setVisibility(View.GONE);
        }else if (oldScrollY < scrollY && scrollY - oldScrollY > 10){//向上
            mFloatingActionButton.setVisibility(View.VISIBLE);
        }
        if (scrollY <= 0) {
            tl_bar.setBackgroundColor(Color.argb((int) 0, 48, 63, 159));
        } else if (scrollY > 0 && scrollY <= height) {
            float scale = (float) scrollY / height;
            float alpha = (255 * scale);
            tl_bar.setBackgroundColor(Color.argb((int) alpha, 48, 63, 159));
        } else {
            tl_bar.setBackgroundColor(Color.argb((int) 255, 48, 63, 159));
        }
    }
}
