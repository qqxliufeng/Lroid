package com.android.lf.lroid.v.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.p.common.CommonPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Inject
    CommonPresenter commonPresenter;

    @BindView(R.id.id_bt_click)
    Button idBtClick;

    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @BindView(R.id.id_list_view)
    RecyclerView mRecyclerView;

    private ProgressDialog progressDialog;

    ArrayList<ItemBean> arrayList;

    MyAdapter adapter;

    private boolean isTest = false;

    @OnClick(R.id.id_bt_click)
    public void onClick(View view) {
        if (isTest) {
            isTest = false;
            commonPresenter.requestData(0x0, 0, 10);
        }else {
            isTest = true;
            commonPresenter.requestData(0x1, 0, 10);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setPresentComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void initView() {
        commonPresenter.setPresentListener(this);
        arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrayList.add( new ItemBean("滑动删除" + i) );
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(R.layout.simple_list_item_multiple_choice,arrayList);
        adapter.openLoadMore(20);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener(){

            @Override
            public void onLoadMoreRequested() {
                new Handler(){}.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<ItemBean> tempList = new ArrayList<ItemBean>();
                        for (int i = 0; i < 10; i++) {
                            tempList.add( new ItemBean("滑动删除" + i) );
                        }
                        adapter.addData(tempList);
                    }
                },3000);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestStart(int requestID) {
        progressDialog = ProgressDialog.show(this, null, "正在加载……"+requestID);
    }

    @Override
    public <T> void onRequestSuccess(int requestID,T result) {
        if (requestID == 0x0){
            Toast.makeText(this, "哈哈" + requestID, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "嘿嘿" + requestID, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestEnd(int requestID) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    class MyAdapter extends BaseQuickAdapter<ItemBean>{

        public MyAdapter(int layoutResId, List<ItemBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, final ItemBean s) {
            baseViewHolder.setText(R.id.id_cb_item,s.title);
            baseViewHolder.setOnCheckedChangeListener(R.id.id_cb_item, new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    s.isChecked = b;
                    Log.e("TAG",s.title +  "   checked is ---->  "+s.isChecked);
                }
            });
            baseViewHolder.setChecked(R.id.id_cb_item,s.isChecked);
        }
    }

    class ItemBean{
        ItemBean(String title) {
            this.title = title;
        }

        String title;
        boolean isChecked;
    }

}
