package com.android.lf.lroid.v.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    @BindView(R.id.id_et_fragment_login_normal_user_name)
    EditText mUserName;
    @BindView(R.id.id_et_fragment_login_normal_password)
    EditText mPassword;

    public static LoginNormalFragment newInstance() {
        return  new LoginNormalFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_normal_layout;
    }

    @Override
    protected void initView(View view) {
        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mUserName.getText().toString()) && !TextUtils.isEmpty(mPassword.getText().toString())){
                    mLogin.setEnabled(true);
                }else {
                    mLogin.setEnabled(false);
                }
            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mUserName.getText().toString()) && !TextUtils.isEmpty(mPassword.getText().toString())){
                    mLogin.setEnabled(true);
                }else {
                    mLogin.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void setComponent() {
//        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }
}
