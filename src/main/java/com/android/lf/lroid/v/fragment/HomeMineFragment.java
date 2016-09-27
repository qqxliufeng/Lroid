package com.android.lf.lroid.v.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.views.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by feng on 2016/9/2.
 */

public class HomeMineFragment extends LroidBaseFragment {

    @BindView(R.id.id_niv_fragment_mine_face)
    RoundedImageView mFace;
    @BindView(R.id.id_tv_fragment_mine_nick_name)
    TextView mNickName;
    @BindView(R.id.id_ll_fragment_mine_face_container)
    LinearLayout mFaceContainer;
    @BindView(R.id.id_rl_fragment_mine_personal_info)
    RelativeLayout mPersonalInfoContainer;
    @BindView(R.id.id_rl_fragment_mine_reset_password)
    RelativeLayout mResetPasswordContainer;
    @BindView(R.id.id_bt_fragment_mine_logout)
    Button mLogout;

    private static int LOGIN_SUCCESS_FLAG = 0;

    public static HomeMineFragment newInstance() {
        return new HomeMineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_layout;
    }

    @Override
    protected void initView(View view) {
        mFace.setImageResource(R.drawable.img_home_mine_default_face_icon);
    }

    @Override
    protected void setComponent() {
    }

    @OnClick(R.id.id_ll_fragment_mine_face_container)
    public void onFaceClick(View view) {
        if (TextUtils.isEmpty(UserBean.getInstance().getName())) {
            UserBean.getInstance().setOnUserLoginSuccessListener(this);
            MethodUtils.startFragmentsActivity(mContext, "登录", FragmentContainerActivity.LOGIN_FRAGMENT);
        }
    }

    @OnClick(R.id.id_rl_fragment_mine_personal_info)
    public void onPersonalInfo(View view) {
        if (TextUtils.isEmpty(UserBean.getInstance().getName())) {
            LOGIN_SUCCESS_FLAG = 1;
            UserBean.getInstance().setOnUserLoginSuccessListener(this);
            MethodUtils.startFragmentsActivity(mContext, "登录", FragmentContainerActivity.LOGIN_FRAGMENT);
        } else {
            MethodUtils.startFragmentsActivity(mContext, "个人信息", FragmentContainerActivity.PERSONAL_INFO_FRAGMENT_FLAG);
        }
    }

    @Override
    public void onUserLoginSuccess() {
        mLogout.setEnabled(true);
        mNickName.setText(UserBean.getInstance().getNickName());
        if (!TextUtils.isEmpty(UserBean.getInstance().getFace())) {
            mFace.setImageResource(R.drawable.img_home_mine_default_face_icon);
        } else {
            mFace.setImageResource(R.drawable.img_home_mine_default_face_icon);
        }
        switch (LOGIN_SUCCESS_FLAG) {
            case 1:
                MethodUtils.startFragmentsActivity(mContext, "个人信息", FragmentContainerActivity.PERSONAL_INFO_FRAGMENT_FLAG);
                break;
        }
    }



}
