package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.p.common.UserHelperPresenter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by feng on 2016/9/18.
 */

public class LoginNormalFragment extends LroidBaseFragment {

    @Inject
    UserHelperPresenter mUserHelperPresenter;

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
        mUserHelperPresenter.setFragment(this);
        /*UserBean.getInstance().setSex(1);
        UserBean.getInstance().setPhone("123");
        UserBean.getInstance().setPersonalizedSignature("11111111111");
        UserBean.getInstance().setFace("face url ");
        UserBean.getInstance().setName("哈哈");
        UserBean.getInstance().setPassword("456");
        UserBean.getInstance().setNickName("nick");
        mUserHelperPresenter.register();*/
    }

    @OnClick(R.id.id_bt_login_normal_login)
    public void onLogin(View view) {
        if (!TextUtils.isEmpty(mUserName.getText().toString()) && !TextUtils.isEmpty(mPassword.getText().toString())) {
            mUserHelperPresenter.normalLogin(mUserName.getText().toString(), mPassword.getText().toString());
        }else {
            Toast.makeText(mContext, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestStart(int requestID) {
        if(requestID == UserHelperPresenter.REQUEST_CODE_LOGIN){
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(mContext, "", "正在登录，请稍后……");
            }else {
                mProgressDialog.show();
            }
        }
    }

    @Override
    public void onRequestEnd(int requestID) {
        if (mProgressDialog!=null){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (!TextUtils.isEmpty(((UserBean)result).getName())){
            Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
            if(UserBean.getInstance().getOnUserLoginSuccessListener()!=null){
                finishActivity();
                UserBean.getInstance().getOnUserLoginSuccessListener().onUserLoginSuccess();
                UserBean.getInstance().setOnUserLoginSuccessListener(null);
            }
        }else {
            Toast.makeText(mContext, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestFail(int requestID, Throwable e) {
        Logger.e(e," ---> error ");
        Toast.makeText(mContext, "登录异常", Toast.LENGTH_SHORT).show();
    }


}
