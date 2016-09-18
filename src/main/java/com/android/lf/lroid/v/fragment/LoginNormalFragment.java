package com.android.lf.lroid.v.fragment;

import android.view.View;
import android.widget.Button;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/18.
 */

public class LoginNormalFragment extends LroidBaseFragment {

    @BindView(R.id.id_bt_login_normal_login)
    Button mLogin;

    public static LoginNormalFragment newInstance() {
        return  new LoginNormalFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_normal_layout;
    }

    @Override
    protected void initView(View view) {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }
}
