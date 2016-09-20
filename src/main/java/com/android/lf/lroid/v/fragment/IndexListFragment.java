package com.android.lf.lroid.v.fragment;

import android.app.ProgressDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.m.bean.JieQiBean;
import com.android.lf.lroid.m.database.DataProvider;
import com.android.lf.lroid.p.common.JieQiDataProvidePresenter;
import com.android.lf.lroid.utils.LunarUtils;
import com.android.lf.lroid.utils.MethodUtils;
import com.android.lf.lroid.v.activity.FragmentContainerActivity;
import com.android.lf.lroid.v.views.LroidListView;
import com.android.lf.lroid.volley.RequestManager;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/9.
 */

public class IndexListFragment extends LroidBaseFragment implements AdapterView.OnItemClickListener {

    @Inject
    JieQiDataProvidePresenter dataProvidePresenter;
    @BindView(R.id.id_llv_fragment_index_content)
    LroidListView mListView;

    @BindView(R.id.id_tv_fragment_index_current_time)
    TextView mCurrentTime;

    private ProgressDialog mProgressDialog;

    private ArrayList<JieQiBean> arrayList = new ArrayList<JieQiBean>();

    private MyLroidListViewAdapter adapter;

    public static IndexListFragment newInstance() {
        return new IndexListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index_content_layout;
    }

    @Override
    protected void initView(View view) {
        LunarUtils lunarUtils = new LunarUtils(Calendar.getInstance());
        mCurrentTime.setText("当前时间：公历："+MethodUtils.getCurrentTime(null)+"  农历："+lunarUtils.toString());
        mCurrentTime.setSelected(true);
        adapter = new MyLroidListViewAdapter();
        mListView.addFooterView(View.inflate(mContext,R.layout.fragment_index_list_foot_view_foot_layout,null));
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        //设置代理
        dataProvidePresenter.setFragment(this);
        dataProvidePresenter.getDataFromDB(DataProvider.JIE_QI_URI);
    }

    @Override
    protected void setComponent() {
        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MethodUtils.startFragmentsActivity(mContext,"登录",FragmentContainerActivity.LOGIN_FRAGMENT);
//        Bundle bundle = new Bundle();
//        bundle.putString(WebContentFragment.WEB_LOAD_URL,arrayList.get(position).getDetail_info_url());
//        MethodUtils.startFragmentsActivity(mContext,arrayList.get(position).getName(), FragmentContainerActivity.WEB_CONTENT_CONTAINER_FLAG,bundle);
    }

    private class MyLroidListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            int type = getItemViewType(i);
            switch (type){
                case 0:
                    ViewHolderTypeOne viewHolderTypeOne;
                    if (view == null){
                        viewHolderTypeOne = new ViewHolderTypeOne();
                        view = View.inflate(mContext,android.R.layout.simple_list_item_1,null);
                        viewHolderTypeOne.tv_title = (TextView) view.findViewById(android.R.id.text1);
                        view.setTag(viewHolderTypeOne);
                    }
                    viewHolderTypeOne = (ViewHolderTypeOne) view.getTag();
                    viewHolderTypeOne.tv_title.setText(arrayList.get(i).getTypeName());
                    break;
                case 1:
                    ViewHolderTypeTwo viewHolderTypeTwo;
                    if (view == null){
                        viewHolderTypeTwo = new ViewHolderTypeTwo();
                        view = View.inflate(mContext,R.layout.fragment_index_list_item_layout,null);
                        viewHolderTypeTwo.iv_pic = (NetworkImageView) view.findViewById(R.id.id_niv_fragment_index_list_item_pic);
                        viewHolderTypeTwo.tv_title = (TextView) view.findViewById(R.id.id_tv_fragment_index_list_item_name);
                        viewHolderTypeTwo.tv_time = (TextView) view.findViewById(R.id.id_tv_fragment_index_list_item_time);
                        viewHolderTypeTwo.tv_content = (TextView) view.findViewById(R.id.id_tv_fragment_index_list_item_content);
                        view.setTag(viewHolderTypeTwo);
                    }
                    JieQiBean jieQiBean = arrayList.get(i);
                    viewHolderTypeTwo = (ViewHolderTypeTwo) view.getTag();
                    viewHolderTypeTwo.tv_title.setText(jieQiBean.getName());
                    viewHolderTypeTwo.iv_pic.setImageUrl(jieQiBean.getImage_url(), RequestManager.getImageLoader());
                    viewHolderTypeTwo.tv_content.setText(jieQiBean.getContent());
                    viewHolderTypeTwo.tv_time.setText(jieQiBean.getTime());
                    break;
            }
            return view;
        }

        @Override
        public int getItemViewType(int position) {
            /*if (position % 7 == 0){
                return 0;
            }else {
                return 1;
            }*/
            return 1;
        }
        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }

    static class ViewHolderTypeOne{
        TextView tv_title;
    }

    static class ViewHolderTypeTwo{
        TextView tv_title;
        TextView tv_time;
        TextView tv_content;
        NetworkImageView iv_pic;
    }

    @Override
    public <T> void onRequestSuccess(int requestID, T result) {
        arrayList.addAll((ArrayList<JieQiBean>) result);
        if (adapter!=null) {
            adapter.notifyDataSetChanged();
            ((HomeIndexFragment)getParentFragment()).scrollTo();
        }
    }

    @Override
    public void onRequestEnd(int requestID) {
        if (requestID == 0x0){
            if (mProgressDialog!=null){
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }
    }

    @Override
    public void onRequestStart(int requestID) {
        if (requestID == 0x0){
            if (mProgressDialog == null){
                mProgressDialog = ProgressDialog.show(mContext,null,"正在加载……");
            }
        }
    }
}
