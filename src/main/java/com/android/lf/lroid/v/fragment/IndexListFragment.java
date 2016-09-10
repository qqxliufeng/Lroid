package com.android.lf.lroid.v.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.component.DaggerInjectPresentComponent;
import com.android.lf.lroid.component.PresentModule;
import com.android.lf.lroid.p.common.CommonPresenter;
import com.android.lf.lroid.p.common.DataProvidePresenter;
import com.android.lf.lroid.v.views.LroidListView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/9.
 */

public class IndexListFragment extends BaseFragment {

    @Inject
    DataProvidePresenter dataProvidePresenter;

    @BindView(R.id.id_llv_fragment_index_content)
    LroidListView mListView;

    @BindView(R.id.id_pb_fragment_index_progress)
    ProgressBar mProgressBar;

    private ArrayList<String> arrayList = new ArrayList<String>(){
        {
            for (int i = 0; i < 50; i++) {
                add("item + "+i);
            }
        }
    };

    public static IndexListFragment newInstance() {
        return new IndexListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index_content_layout;
    }

    @Override
    protected void initView(View view) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        mListView.setAdapter(new MyLroidListViewAdapter());
        dataProvidePresenter.setBaseFragment(this);
        dataProvidePresenter.getDataFromDB();
    }

    @Override
    protected void setComponent() {

        DaggerInjectPresentComponent.builder().presentModule(new PresentModule()).build().inject(this);
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
                    //**
                    ViewHolderTypeOne viewHolderTypeOne;
                    if (view == null){
                        viewHolderTypeOne = new ViewHolderTypeOne();
                        view = View.inflate(mContext,android.R.layout.simple_list_item_1,null);
                        viewHolderTypeOne.tv_title = (TextView) view.findViewById(android.R.id.text1);
                        view.setTag(viewHolderTypeOne);
                    }
                    viewHolderTypeOne = (ViewHolderTypeOne) view.getTag();
                    viewHolderTypeOne.tv_title.setText(arrayList.get(i));
                    break;
                case 1:
                    ViewHolderTypeTwo viewHolderTypeTwo ;
                    if (view == null){
                        viewHolderTypeTwo = new ViewHolderTypeTwo();
                        view = View.inflate(mContext,android.R.layout.activity_list_item,null);
                        viewHolderTypeTwo.iv_pic = (ImageView) view.findViewById(android.R.id.icon);
                        viewHolderTypeTwo.tv_title = (TextView) view.findViewById(android.R.id.text1);
                        view.setTag(viewHolderTypeTwo);
                    }
                    viewHolderTypeTwo = (ViewHolderTypeTwo) view.getTag();
                    viewHolderTypeTwo.tv_title.setText(arrayList.get(i));
                    viewHolderTypeTwo.iv_pic.setImageResource(R.mipmap.ic_launcher);
                    break;
            }
            return view;
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0){
                return 0;
            }else {
                return 1;
            }
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
        ImageView iv_pic;
    }


}
