package com.android.lf.lroid.v.fragment;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.m.tables.UserTable;
import com.android.lf.lroid.p.UserHelperPresenter;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.views.RoundedImageView;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by feng on 2016/9/26.
 */

public class PersonalInfoFragment extends LroidBaseFragment {

    public static boolean IS_CHANGE_FACE = false;

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

    private int sexWhich;

    @Inject
    UserHelperPresenter mUserHelperPresenter;

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
        mUserHelperPresenter.setFragment(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPhone.setText(UserBean.getInstance().getPhone());
        mName.setText(UserBean.getInstance().getName());
        mNickName.setText(TextUtils.isEmpty(UserBean.getInstance().getNickName()) ? "未设置" : UserBean.getInstance().getNickName());
        mSex.setText(UserBean.getInstance().getSex() == 0 ? "男" : "女");
        mPersonalSignature.setText(TextUtils.isEmpty(UserBean.getInstance().getPersonalizedSignature()) ? "未设置" : UserBean.getInstance().getPersonalizedSignature());
        mFace.setImageResource(R.drawable.img_home_mine_default_face_icon);
        if (!TextUtils.isEmpty(UserBean.getInstance().getFace())) {
            mFace.setImageURI(Uri.fromFile(new File(UserBean.getInstance().getFace())));
        }
        if (IS_CHANGE_FACE) {
            IS_CHANGE_FACE = false;
            mFace.setImageURI(Uri.fromFile(new File(UserBean.getInstance().getFace())));
            ContentValues contentValues = new ContentValues();
            contentValues.put(UserTable.FACE, UserBean.getInstance().getFace());
            mUserHelperPresenter.modifyUserInfo(contentValues, UserTable.PHONE, UserBean.getInstance().getPhone());
        }
    }

    @OnClick({R.id.id_rl_fragment_personal_info_face_container, R.id.id_rl_fragment_personal_info_personal_signature_container, R.id.id_rl_fragment_personal_info_nick_name_container, R.id.id_rl_fragment_personal_info_sex_container})
    public void onRelativeLayoutClick(View view) {
        switch (view.getId()) {
            case R.id.id_rl_fragment_personal_info_face_container:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x0);
                    } else {
                        openSelectImage();
                    }
                } else {
                    openSelectImage();
                }
                break;
            case R.id.id_rl_fragment_personal_info_personal_signature_container:
                Bundle personalSignatureBundle = new Bundle();
                personalSignatureBundle.putString(SetPersonalInfoFragment.MODIFY_COLUMN_FLAG, UserTable.PERSONALIZED_SIGNATURE);
                personalSignatureBundle.putString(SetPersonalInfoFragment.MODIFY_EDIT_TEXT_VIEW_HINT, "请输入个性签名");
                personalSignatureBundle.putInt(SetPersonalInfoFragment.MODIFY_TYPE_FLAG, 1);
                MethodUtils.startFragmentsActivity(mContext, "个性签名", FragmentContainerActivity.SET_PERSONAL_INFO_FRAGMENT_FLAG, personalSignatureBundle);
                break;
            case R.id.id_rl_fragment_personal_info_nick_name_container:
                Bundle nickNameBundle = new Bundle();
                nickNameBundle.putString(SetPersonalInfoFragment.MODIFY_COLUMN_FLAG, UserTable.NICK_NAME);
                nickNameBundle.putString(SetPersonalInfoFragment.MODIFY_EDIT_TEXT_VIEW_HINT, "请输入昵称");
                nickNameBundle.putInt(SetPersonalInfoFragment.MODIFY_TYPE_FLAG, 0);
                MethodUtils.startFragmentsActivity(mContext, "昵称", FragmentContainerActivity.SET_PERSONAL_INFO_FRAGMENT_FLAG, nickNameBundle);
                break;
            case R.id.id_rl_fragment_personal_info_sex_container:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                String[] items = new String[]{"男", "女"};
                builder.setSingleChoiceItems(items, UserBean.getInstance().getSex(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        sexWhich = which;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(UserTable.SEX, which);
                        mUserHelperPresenter.modifyUserInfo(contentValues, UserTable.PHONE, UserBean.getInstance().getPhone());
                    }
                });
                builder.setTitle("请选择性别");
                builder.create().show();
                break;
        }
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if ((Integer) result != -1) {
            UserBean.getInstance().setSex(sexWhich);
            mSex.setText(sexWhich == 0 ? "男" : "女");
        }
    }

    private void openSelectImage() {
        Intent intent = new Intent(mContext, FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.FRAGMENT_FLAG, FragmentContainerActivity.PHOTO_SELECT_FRAGMENT_FLAG);
        intent.putExtra(FragmentContainerActivity.TITLE_FLAG, "选取照片");
        startActivity(intent);
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
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }


}
