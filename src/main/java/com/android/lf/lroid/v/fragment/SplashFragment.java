package com.android.lf.lroid.v.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.p.UserHelperPresenter;
import com.android.lf.lroid.utils.Constants;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.android.lf.lroid.utils.ScreenUtils;
import com.android.lf.lroid.v.activity.HomeActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/14.
 */

public class SplashFragment extends LroidBaseFragment {

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Inject
    UserHelperPresenter mUserHelperPresenter;

    @BindView(R.id.id_tv_fragment_splash_lroid)
    TextView mLroidText;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash_layout;
    }

    @Override
    protected void initView(View view) {
        mUserHelperPresenter.setFragment(this);
        mLroidText.post(new Runnable() {
            @Override
            public void run() {
                Typeface typeFace = Typeface.createFromAsset(mContext.getAssets(), "heather.ttf");
                mLroidText.setTypeface(typeFace);
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator translationY = ObjectAnimator.ofFloat(mLroidText,"translationY",0,(int)(ScreenUtils.getScreenHeight(mContext)/2.5));
                ObjectAnimator alpha = ObjectAnimator.ofFloat(mLroidText,"alpha",0.3f,1.0f);
                animatorSet.setDuration(2500);
                animatorSet.setInterpolator(new LinearInterpolator());
                animatorSet.play(alpha).with(translationY);
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (!TextUtils.isEmpty(PreferenceUtils.getPrefString(mContext, Constants.USER_NAME_FLAG,""))) {
                            mUserHelperPresenter.doSomethingWithRxJavaMap(UserHelperPresenter.REQUEST_CODE_NORMAL_LOGIN,PreferenceUtils.getPrefString(mContext,Constants.USER_NAME_FLAG,""),PreferenceUtils.getPrefString(mContext,Constants.USER_PASSWORD_FLAG,""),false);
                        }else {
                            startActivity(new Intent(mContext, HomeActivity.class));
                            finishActivity();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorSet.start();
            }
        });
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestEnd(int requestID) {
        startActivity(new Intent(mContext, HomeActivity.class));
        finishActivity();
    }

}
