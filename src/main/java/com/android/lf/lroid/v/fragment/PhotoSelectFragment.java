package com.android.lf.lroid.v.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.PhotoBean;
import com.android.lf.lroid.m.bean.PhotoFolderBean;
import com.android.lf.lroid.p.LoadPhotoPresenter;
import com.android.lf.lroid.utils.SDCardUtils;
import com.android.lf.lroid.utils.ScreenUtils;
import com.android.lf.lroid.v.adapter.PhotoFolderAdapter;
import com.android.lf.lroid.v.adapter.PhotoSelectAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by feng on 2016/9/27.
 */

public class PhotoSelectFragment extends LroidBaseFragment implements AdapterView.OnItemClickListener {

    public static PhotoSelectFragment newInstance() {
        return new PhotoSelectFragment();
    }

    @BindView(R.id.id_gv_fragment_photo_container)
    GridView mPhotoContainer;
    @BindView(R.id.id_tv_fragment_photo_item_name)
    TextView mItemName;
    @BindView(R.id.id_tv_fragment_photo_item_count)
    TextView mItemCount;
    @BindView(R.id.id_rl_fragment_photo_select_bottom_bar)
    RelativeLayout mPhotoSelectBottomBar;
    @BindView(R.id.id_lv_fragment_photo_select_item)
    ListView mFolderList;
    @BindView(R.id.id_fl_fragment_photo_select_folder_list_bg)
    View mViewBg;
    @BindView(R.id.id_fl_fragment_photo_select_folder_list_container)
    FrameLayout mFolderListContainer;

    @Inject
    LoadPhotoPresenter mLoadPhotoPresenter;

    private PhotoSelectAdapter selectAdapter;
    private PhotoFolderAdapter folderAdapter;

    private ArrayList<PhotoBean> mPhotoList = new ArrayList<>();
    private ArrayList<PhotoFolderBean> mPhotoFolderList = new ArrayList<>();

    private int actionBarHeight = 0;
    private int screenHeight = 0;

    private boolean isShowFolder = true;

    private AnimatorSet animatorSetExit = new AnimatorSet();
    private AnimatorSet animatorSetEnter = new AnimatorSet();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photo_select_layout;
    }

    @Override
    protected void initView(View view) {
        if (!SDCardUtils.isSDCardEnable()) {
            Toast.makeText(mContext, "请放入SDCard", Toast.LENGTH_SHORT).show();
            return;
        }
        initActionBarSize();
        initAnimation();
        mFolderList.setOnItemClickListener(this);
        mFolderListContainer.setVisibility(View.GONE);
        selectAdapter = new PhotoSelectAdapter(mContext, mPhotoList, R.layout.adapter_photo_select_item_layout);
        mPhotoContainer.setAdapter(selectAdapter);
        folderAdapter = new PhotoFolderAdapter(mContext,mPhotoFolderList,R.layout.adapter_photo_folder_item_layout);
        mFolderList.setAdapter(folderAdapter);
        mPhotoSelectBottomBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mLoadPhotoPresenter.setFragment(this);
        mLoadPhotoPresenter.loadingPhoto();
    }

    private void initActionBarSize() {
        screenHeight = ScreenUtils.getScreenHeight(mContext);
        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
    }

    private void initAnimation() {
        ObjectAnimator transExitAnimation = ObjectAnimator.ofFloat(mFolderList,"translationY", 0,screenHeight - 3 * actionBarHeight);
        ObjectAnimator alphaExitAnimation = ObjectAnimator.ofFloat(mViewBg,"alpha",0.7f,0.0f);
        ObjectAnimator transEnterAnimation = ObjectAnimator.ofFloat(mFolderList,"translationY", screenHeight - 3 * actionBarHeight,0);
        ObjectAnimator alphaEnterAnimation = ObjectAnimator.ofFloat(mViewBg,"alpha",0.0f,0.7f);
        LinearInterpolator interpolator = new LinearInterpolator();
        animatorSetExit.setDuration(300);
        animatorSetExit.setInterpolator(interpolator);
        animatorSetExit.play(transExitAnimation).with(alphaExitAnimation);
        animatorSetEnter.setDuration(300);
        animatorSetEnter.setInterpolator(interpolator);
        animatorSetEnter.play(transEnterAnimation).with(alphaEnterAnimation);
        mViewBg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isShowFolder) {
                    toggle();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onRequestStart(int requestID) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(mContext, "", "正在加载图片……");
        } else {
            mProgressDialog.show();
        }
    }

    @Override
    public void onRequestEnd(int requestID) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        if (result != null) {
            Map<String, PhotoFolderBean> folderBeanMap = (Map<String, PhotoFolderBean>) result;
            Set<String> keys = folderBeanMap.keySet();
            for (String key : keys){
                mPhotoFolderList.add(folderBeanMap.get(key));
            }
            Collections.reverse(mPhotoFolderList);
            mPhotoFolderList.get(0).setIsSelected(true);
            folderAdapter.notifyDataSetChanged();
            toggle();
            ArrayList<PhotoBean> list = (ArrayList<PhotoBean>) folderBeanMap.get(LoadPhotoPresenter.ALL_PICTURE).getPhotoList();
            if (list!=null && list.size() > 0){
                mItemCount.setText("共 " + list.size() + " 张");
                mPhotoList.addAll(list);
                selectAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 动画开关
     */
    private void toggle() {
        if (isShowFolder){
            isShowFolder = false;
            animatorSetExit.start();
        }else {
            animatorSetEnter.start();
            isShowFolder = true;
        }
    }

    @OnClick(R.id.id_tv_fragment_photo_item_name)
    public void onSelectItem(View view){
        mFolderListContainer.setVisibility(View.VISIBLE);
        toggle();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        for (PhotoFolderBean photoFolderBean : mPhotoFolderList){
            photoFolderBean.setIsSelected(false);
        }
        mItemName.setText(mPhotoFolderList.get(position).getName());
        mItemCount.setText("共 " + mPhotoFolderList.get(position).getPhotoList().size()+" 张");
        mPhotoFolderList.get(position).setIsSelected(true);
        folderAdapter.notifyDataSetChanged();
        mPhotoList.clear();
        mPhotoList.addAll(mPhotoFolderList.get(position).getPhotoList());
        selectAdapter.notifyDataSetChanged();
        toggle();

    }

    @OnItemClick(R.id.id_gv_fragment_photo_container)
    public void onPhotoSelect(AdapterView<?> parent, View view, int position, long id){
        if (position == 0){
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 0x0);
            Toast.makeText(mContext, "开启相机", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, mPhotoList.get(position - 1).getPath(), Toast.LENGTH_SHORT).show();
        }
    }

}