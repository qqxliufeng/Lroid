package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.lf.lroid.R;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/18.
 */

public class LoginFastFragment extends LroidBaseFragment {

    private long countTime = 60000;
    private long countDownInterval = 1000;

    @BindView(R.id.id_et_fragment_login_fast_user_name)
    EditText mPhone;
    @BindView(R.id.id_et_fragment_login_fast_password)
    EditText mPassword;
    @BindView(R.id.id_bt_fragment_login_fast_get_code)
    Button mCode;

    private CountDownTimer mCountDownTimer;

    public static LoginFastFragment newInstance() {
        return new LoginFastFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_fast_layout;
    }

    @Override
    protected void initView(View view) {
        mPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mPhone.getText().toString())&&mPhone.getText().toString().length()==11){
                    mCode.setEnabled(true);
                }else {
                    mCode.setEnabled(false);
                }
            }
        });
        mCode.setOnClickListener(this);
        mCountDownTimer = new CountDownTimer(countTime,countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                mCode.setText(millisUntilFinished/1000+" s");
            }

            @Override
            public void onFinish() {
                mCode.setText("重新发送？");
                mCode.setEnabled(true);
            }
        };
    }

    @Override
    protected void setComponent() {
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.id_bt_fragment_login_fast_get_code){
            mCountDownTimer.start();
            mCode.setEnabled(false);
        }
    }

    @Override
    public void onDestroy() {
        if (mCountDownTimer!=null){
            mCountDownTimer.cancel();
        }
        super.onDestroy();
    }
}
