package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.lf.lroid.R;

import butterknife.BindView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by feng on 2016/9/18.
 */

public class LoginFastFragment extends LroidBaseFragment {

    private static final long countTime = 60000;
    private static final long countDownInterval = 1000;

    @BindView(R.id.id_et_fragment_login_fast_user_name)
    EditText mPhone;
    @BindView(R.id.id_et_fragment_login_fast_password)
    EditText mPassword;
    @BindView(R.id.id_bt_fragment_login_fast_get_code)
    Button mCode;
    @BindView(R.id.id_bt_login_fast_login)
    Button mLogin;

    private CountDownTimer mCountDownTimer;

    private Handler mHandler = new Handler();

    public static LoginFastFragment newInstance() {
        return new LoginFastFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_fast_layout;
    }

    @Override
    protected void initView(View view) {
        initSMS();
        mPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mPhone.getText().toString()) && mPhone.getText().toString().length() == 11) {
                    mCode.setEnabled(true);
                } else {
                    mCode.setEnabled(false);
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
                if (!TextUtils.isEmpty(mPassword.getText().toString()) && mPassword.getText().toString().length() == 4){
                    SMSSDK.submitVerificationCode("+86",mPhone.getText().toString(),mPassword.getText().toString());
                }
            }
        });
        mCode.setOnClickListener(this);
        mCountDownTimer = new CountDownTimer(countTime, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                mCode.setText(millisUntilFinished / 1000 + " s");
            }

            @Override
            public void onFinish() {
                mCode.setText("重新发送？");
                mCode.setEnabled(true);
            }
        };
    }

    private void initSMS() {
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mLogin.setEnabled(true);
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "短信已经发送，请注意查收", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    protected void setComponent() {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_bt_fragment_login_fast_get_code) {
            mCountDownTimer.start();
            mCode.setEnabled(false);
            SMSSDK.getVerificationCode("+86",mPhone.getText().toString());
            mPassword.requestFocus();
        }
    }

    @Override
    public void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        super.onDestroy();
    }
}
