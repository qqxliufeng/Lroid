package com.android.lf.lroid.component;

import com.android.lf.lroid.v.activity.HomeActivity;
import com.android.lf.lroid.v.fragment.EntertainmentMoreListFragment;
import com.android.lf.lroid.v.fragment.FoodListFragment;
import com.android.lf.lroid.v.fragment.FoodFragment;
import com.android.lf.lroid.v.fragment.HealthFragment;
import com.android.lf.lroid.v.fragment.HealthSearchResultFragment;
import com.android.lf.lroid.v.fragment.HistoryTodayFragment;
import com.android.lf.lroid.v.fragment.HomeMoreFragment;
import com.android.lf.lroid.v.fragment.JieRiFragment;
import com.android.lf.lroid.v.fragment.JieRiListFragment;
import com.android.lf.lroid.v.fragment.MoreContentTwoFragment;
import com.android.lf.lroid.v.fragment.IndexListFragment;
import com.android.lf.lroid.v.fragment.IndexTopFragment;
import com.android.lf.lroid.v.fragment.LoginFastFragment;
import com.android.lf.lroid.v.fragment.LoginNormalFragment;
import com.android.lf.lroid.v.fragment.PersonalInfoFragment;
import com.android.lf.lroid.v.fragment.PhotoSelectFragment;
import com.android.lf.lroid.v.fragment.ResetPasswordFragment;
import com.android.lf.lroid.v.fragment.SetPersonalInfoFragment;
import com.android.lf.lroid.v.fragment.SplashFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by feng on 2016/8/17.
 */
@Singleton
@Component(modules = { PresentModule.class })
public interface InjectPresentComponent {

    void inject(HomeActivity homeActivity);

    void inject(IndexTopFragment indexFragment);

    void inject(IndexListFragment indexListFragment);

    void inject(LoginNormalFragment loginNormalFragment);

    void inject(LoginFastFragment loginFastFragment);

    void inject(JieRiListFragment jieRiListFragment);

    void inject(HomeMoreFragment homeMoreFragment);

    void inject(PhotoSelectFragment photoSelectFragment);

    void inject(PersonalInfoFragment personalInfoFragment);

    void inject(SplashFragment splashFragment);

    void inject(SetPersonalInfoFragment setPersonalInfoFragment);

    void inject(ResetPasswordFragment resetPasswordFragment);

    void inject(HistoryTodayFragment historyTodayFragment);

    void inject(MoreContentTwoFragment moreContentTwoFragment);

    void inject(EntertainmentMoreListFragment entertainmentMoreListFragment);

    void inject(HealthFragment healthFragment);

    void inject(HealthSearchResultFragment healthSearchResultFragment);

    void inject(FoodFragment foodFragment);

    void inject(FoodListFragment foodListFragment);

    void inject(JieRiFragment jieRiFragment);

}
