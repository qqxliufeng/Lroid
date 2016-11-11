package com.android.lf.lroid.v.fragment;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.utils.Constants;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.utils.PreferenceUtils;
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

    public static boolean IS_CHANGE_FACE = false;


    public static HomeMineFragment newInstance() {
        return new HomeMineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_layout;
    }

    @Override
    protected void initView(View view) {
        mFace.setOval(true);
        mFace.setImageResource(R.drawable.img_home_mine_default_face_icon);
        if (UserBean.getInstance() != null && !TextUtils.isEmpty(UserBean.getInstance().getPhone())) {
            setFace();
            mLogout.setEnabled(true);
        }
    }

    private void setFace() {
        if (!TextUtils.isEmpty(UserBean.getInstance().getFace())) {
            Bitmap bitmap = BitmapFactory.decodeFile(UserBean.getInstance().getFace());
            mFace.setImageBitmap(bitmap);
        } else {
            mFace.setImageResource(R.drawable.img_home_mine_default_face_icon);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mNickName.setText(TextUtils.isEmpty(UserBean.getInstance().getNickName()) ? "登录/注册" : UserBean.getInstance().getNickName());
        if (IS_CHANGE_FACE) {
            IS_CHANGE_FACE = false;
            setFace();
        }
    }

    @Override
    protected void setComponent() {
    }

    @OnClick({R.id.id_rl_fragment_mine_personal_info,R.id.id_ll_fragment_mine_face_container})
    public void onPersonalInfo() {
        if (TextUtils.isEmpty(UserBean.getInstance().getName())) {
            LOGIN_SUCCESS_FLAG = 1;
            UserBean.getInstance().setOnUserLoginSuccessListener(this);
            MethodUtils.startFragmentsActivity(mContext, "登录", FragmentContainerActivity.LOGIN_FRAGMENT);
        } else {
            MethodUtils.startFragmentsActivity(mContext, "个人信息", FragmentContainerActivity.PERSONAL_INFO_FRAGMENT_FLAG);
        }
    }

    @OnClick(R.id.id_rl_fragment_mine_reset_password)
    public void onRestPasswordClick() {
        if (!TextUtils.isEmpty(UserBean.getInstance().getPhone())) {
            MethodUtils.startFragmentsActivity(mContext, "修改密码", FragmentContainerActivity.RESET_PASSWORD_FLAG);
        }else {
            LOGIN_SUCCESS_FLAG = 2;
            UserBean.getInstance().setOnUserLoginSuccessListener(this);
            MethodUtils.startFragmentsActivity(mContext, "登录", FragmentContainerActivity.LOGIN_FRAGMENT);
        }
    }


    @OnClick(R.id.id_rl_fragment_mine_about)
    public void onAbout(){
        Bundle bundle = new Bundle();
        bundle.putString(WebContentFragment.WEB_LOAD_URL, Constants.FILE_ANDROID_ASSET_NOTIFY_HTML_HTML);
        MethodUtils.startFragmentsActivity(mContext,"关于",FragmentContainerActivity.WEB_CONTENT_CONTAINER_FLAG,bundle);
    }


    @Override
    public void onUserLoginSuccess() {
        mLogout.setEnabled(true);
        mNickName.setText(UserBean.getInstance().getNickName());
        setFace();
        switch (LOGIN_SUCCESS_FLAG) {
            case 1:
                MethodUtils.startFragmentsActivity(mContext, "个人信息", FragmentContainerActivity.PERSONAL_INFO_FRAGMENT_FLAG);
                break;
            case 2:
                MethodUtils.startFragmentsActivity(mContext, "修改密码", FragmentContainerActivity.RESET_PASSWORD_FLAG);
                break;
        }
    }

    @OnClick(R.id.id_bt_fragment_mine_logout)
    public void onLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserBean.getInstance().clearInfo();
                setFace();
                mLogout.setEnabled(false);
                mNickName.setText("登录/注册");
                PreferenceUtils.setPrefString(mContext, Constants.USER_NAME_FLAG, "");
                PreferenceUtils.setPrefString(mContext, Constants.USER_PASSWORD_FLAG, "");
            }
        });
        builder.setNegativeButton("取消", null);
        builder.setMessage("是否要退出?");
        builder.create().show();
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        Log.e("TAG",(String) result);
    }
}
