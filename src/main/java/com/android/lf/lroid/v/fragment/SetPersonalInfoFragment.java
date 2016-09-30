package com.android.lf.lroid.v.fragment;

import android.content.ContentValues;
import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.UserBean;
import com.android.lf.lroid.m.tables.UserTable;
import com.android.lf.lroid.p.UserHelperPresenter;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/29.
 */

public class SetPersonalInfoFragment extends LroidBaseFragment {

    private boolean mShowLoader = true;

    public static SetPersonalInfoFragment newInstance(Bundle args) {
        SetPersonalInfoFragment fragment = new SetPersonalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String MODIFY_TYPE_FLAG = "modify_type_flag";
    public static final String MODIFY_COLUMN_FLAG = "modify_column_flag";
    public static final String MODIFY_EDIT_TEXT_VIEW_HINT = "modify_hint";

    private int modifyType = 0;

    @BindView(R.id.id_et_fragment_set_user_info_text)
    EditText mTextContent;

    @Inject
    UserHelperPresenter mUserHelperPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_set_personal_info_layout;
    }

    @Override
    protected void initView(View view) {
        mUserHelperPresenter.setFragment(this);
        mTextContent.setHint(getArguments().getString(MODIFY_EDIT_TEXT_VIEW_HINT));
        modifyType = getArguments().getInt(MODIFY_TYPE_FLAG);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(com.yalantis.ucrop.R.menu.ucrop_menu_activity, menu);
        MenuItem menuItemLoader = menu.findItem(com.yalantis.ucrop.R.id.menu_loader);
        Drawable menuItemLoaderIcon = menuItemLoader.getIcon();
        if (menuItemLoaderIcon != null) {
            try {
                menuItemLoaderIcon.mutate();
                menuItemLoaderIcon.setColorFilter(ContextCompat.getColor(mContext, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
                menuItemLoader.setIcon(menuItemLoaderIcon);
            } catch (IllegalStateException e) {
            }
            ((Animatable) menuItemLoader.getIcon()).start();
        }
        MenuItem menuItemCrop = menu.findItem(com.yalantis.ucrop.R.id.menu_crop);
        Drawable menuItemCropIcon = menuItemCrop.getIcon();
        if (menuItemCropIcon != null) {
            menuItemCropIcon.mutate();
            menuItemCropIcon.setColorFilter(ContextCompat.getColor(mContext, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
            menuItemCrop.setIcon(menuItemCropIcon);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(com.yalantis.ucrop.R.id.menu_crop).setVisible(mShowLoader);
        menu.findItem(com.yalantis.ucrop.R.id.menu_loader).setVisible(!mShowLoader);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == com.yalantis.ucrop.R.id.menu_crop) {
            mShowLoader = false;
            ((FragmentContainerActivity) mContext).supportInvalidateOptionsMenu();
            saveUserInfo();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveUserInfo() {
        if (!TextUtils.isEmpty(mTextContent.getText().toString())) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(getArguments().getString(MODIFY_COLUMN_FLAG), mTextContent.getText().toString());
            mUserHelperPresenter.doSomethingWithRxJavaMap(UserHelperPresenter.REQUEST_CODE_MODIFY,contentValues, UserTable.PHONE, UserBean.getInstance().getPhone());
        } else {
            Toast.makeText(mContext, mTextContent.getHint().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if ((Integer) result > 0) {
            Toast.makeText(mContext, "修改成功！", Toast.LENGTH_SHORT).show();
            switch (modifyType) {
                case 0: // 0 代表修改昵称
                    UserBean.getInstance().setNickName(mTextContent.getText().toString());
                    break;
                case 1:// 1 代表修改个性签名
                    UserBean.getInstance().setPersonalizedSignature(mTextContent.getText().toString());
                    break;
            }
            finishActivity();
        }else {
            Toast.makeText(mContext, "修改失败！", Toast.LENGTH_SHORT).show();
        }
    }
}
