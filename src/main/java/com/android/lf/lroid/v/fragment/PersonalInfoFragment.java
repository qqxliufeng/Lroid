package com.android.lf.lroid.v.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.views.RoundedImageView;
import com.orhanobut.logger.Logger;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

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
        mFace.setOval(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPhone.setText(UserBean.getInstance().getPhone());
        mName.setText(UserBean.getInstance().getName());
        mNickName.setText(TextUtils.isEmpty(UserBean.getInstance().getNickName()) ? "未设置" : UserBean.getInstance().getNickName());
        mSex.setText(UserBean.getInstance().getSex() == 0 ? "男" : "女");
        mPersonalSignature.setText(TextUtils.isEmpty(UserBean.getInstance().getPersonalizedSignature()) ? "未设置" : UserBean.getInstance().getPersonalizedSignature());
    }

    @OnClick(R.id.id_rl_fragment_personal_info_face_container)
    public void onFaceClick(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x0);
            } else {
//                MethodUtils.startFragmentsActivity(mContext, "选取照片", FragmentContainerActivity.PHOTO_SELECT_FRAGMENT_FLAG);
                openSelectImage();
            }
        } else {
            MethodUtils.startFragmentsActivity(mContext, "选取照片", FragmentContainerActivity.PHOTO_SELECT_FRAGMENT_FLAG);
        }
    }

    private void openSelectImage() {
        Intent intent = new Intent(mContext, FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.FRAGMENT_FLAG, FragmentContainerActivity.PHOTO_SELECT_FRAGMENT_FLAG);
        intent.putExtra(FragmentContainerActivity.TITLE_FLAG, "选取照片");
        startActivityForResult(intent, 0x0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0x0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openSelectImage();
            } else {
                Toast.makeText(mContext, "请开启读取照片权限", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String path = data.getStringExtra("data");
            mFace.setImageURI(Uri.fromFile(new File(path)));
        }
    }

    @Override
    protected void setComponent() {
    }
}
