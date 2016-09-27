package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
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
import com.android.lf.lroid.v.adapter.PhotoSelectAdapter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by feng on 2016/9/27.
 */

public class PhotoSelectFragment extends LroidBaseFragment {

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

    @Inject
    LoadPhotoPresenter mLoadPhotoPresenter;

    private PhotoSelectAdapter selectAdapter;

    private ArrayList<PhotoBean> mPhotoList = new ArrayList<>();
    private ArrayList<PhotoFolderBean> mPhotoFolderList = new ArrayList<>();


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
        selectAdapter = new PhotoSelectAdapter(mContext, mPhotoList, R.layout.adapter_photo_select_item_layout);
        mPhotoContainer.setAdapter(selectAdapter);
        mPhotoSelectBottomBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mLoadPhotoPresenter.setFragment(this);
        mLoadPhotoPresenter.loadingPhoto();
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
            ArrayList<PhotoBean> list = (ArrayList<PhotoBean>) folderBeanMap.get(LoadPhotoPresenter.ALL_PICTURE).getPhotoList();
            if (list!=null && list.size() > 0){
                mItemCount.setText("共 " + list.size() + " 张");
                mPhotoList.addAll(list);
                selectAdapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick(R.id.id_tv_fragment_photo_item_name)
    public void onSelectItem(View view){
    }

}
