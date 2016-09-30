package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.m.tables.UserTable;
import com.android.lf.lroid.p.UserHelperPresenter;
import com.android.lf.lroid.utils.Constants;
import com.android.lf.lroid.utils.PreferenceUtils;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liufeng on 16/9/29.
 */

public class ResetPasswordFragment extends LroidBaseFragment {

    public static ResetPasswordFragment newInstance() {
        return new ResetPasswordFragment();
    }

    @BindView(R.id.id_et_fragment_reset_password_old_password)
    EditText mOldPassword;
    @BindView(R.id.id_et_fragment_reset_password_new_password)
    EditText mNewPassword;
    @BindView(R.id.id_et_fragment_reset_password_confirm_new_password)
    EditText mConfirmPassword;

    @Inject
    UserHelperPresenter mUserHelperPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reset_password_layout;
    }

    @Override
    protected void initView(View view) {
        mUserHelperPresenter.setFragment(this);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @OnClick(R.id.id_bt_fragment_reset_password_submit)
    public void onSubmitPassword() {
        if (TextUtils.isEmpty(mOldPassword.getText().toString())) {
            Toast.makeText(mContext, "请输入原始密码", Toast.LENGTH_SHORT).show();
            mOldPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mNewPassword.getText().toString())) {
            Toast.makeText(mContext, "请输入新密码", Toast.LENGTH_SHORT).show();
            mNewPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mConfirmPassword.getText().toString())) {
            Toast.makeText(mContext, "请输入确入密码", Toast.LENGTH_SHORT).show();
            mConfirmPassword.requestFocus();
            return;
        }
        if (!mNewPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
            Toast.makeText(mContext, "两次密码不一致", Toast.LENGTH_SHORT).show();
            mNewPassword.setText("");
            mConfirmPassword.setText("");
            return;
        }
        if (!UserBean.getInstance().getPassword().equals(mOldPassword.getText().toString())) {
            Toast.makeText(mContext, "原始密码不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTable.PASSWORD, mNewPassword.getText().toString());
        mUserHelperPresenter.doSomethingWithRxJavaMap(UserHelperPresenter.REQUEST_CODE_MODIFY, contentValues, UserTable.PHONE, UserBean.getInstance().getPhone());
    }

    @Override
    public void onRequestStart(int requestID) {
        mProgressDialog = ProgressDialog.show(mContext, "", "正在提交,请稍后……");
    }

    @Override
    public void onRequestEnd(int requestID) {
        mProgressDialog.dismiss();
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if ((Integer) result > 0) {
            Toast.makeText(mContext, "修改成功,请牢记", Toast.LENGTH_SHORT).show();
            UserBean.getInstance().setPassword(mNewPassword.getText().toString());
            PreferenceUtils.setPrefString(mContext, Constants.USER_PASSWORD_FLAG,mNewPassword.getText().toString());
            finishActivity();
        } else {
            Toast.makeText(mContext, "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestFail(int requestID, Throwable e) {
        Toast.makeText(mContext, "修改失败", Toast.LENGTH_SHORT).show();
    }
}
