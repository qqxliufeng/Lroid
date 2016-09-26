package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.v.views.RoundedImageView;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/26.
 */

public class PersonalInfoFragment extends LroidBaseFragment {

    @BindView(R.id.id_riv_fragment_personal_info_face)
    RoundedImageView mFace;
    @BindView(R.id.id_tv_fragment_personal_info_phone)
    TextView mPhone;
    @BindView(R.id.id_tv_fragment_personal_info_name)
    TextView mName;
    @BindView(R.id.id_tv_fragment_personal_info_nick_name)
    TextView mNickName;
    @BindView(R.id.id_tv_fragment_personal_info_sex)
    TextView mSex;
    @BindView(R.id.id_tv_fragment_personal_info_personal_signature)
    TextView mPersonalSignature;

    public static PersonalInfoFragment newInstance() {
        return new PersonalInfoFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_info_layout;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    public void onResume() {
        super.onResume();
        mPhone.setText(UserBean.getInstance().getPhone());
        mName.setText(UserBean.getInstance().getName());
        mNickName.setText(TextUtils.isEmpty(UserBean.getInstance().getNickName()) ? "未设置": UserBean.getInstance().getNickName());
        mSex.setText(UserBean.getInstance().getSex() == 0?"男":"女");
        mPersonalSignature.setText(TextUtils.isEmpty(UserBean.getInstance().getPersonalizedSignature()) ? "未设置": UserBean.getInstance().getPersonalizedSignature());
    }

    @Override
    protected void setComponent() {
    }
}
