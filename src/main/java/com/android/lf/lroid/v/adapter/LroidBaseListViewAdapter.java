package com.android.lf.lroid.v.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lf.lroid.interfaces.OnAdapterCommunityListViewListener;

import java.util.List;

public abstract class LroidBaseListViewAdapter<T> extends android.widget.BaseAdapter
{

	protected OnAdapterCommunityListViewListener<T> onAdapterCommunityListViewListener;

	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	public LroidBaseListViewAdapter(Context context, List<T> mDatas, int itemLayoutId)
	{
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	public void setOnAdapterCommunityListViewListener(OnAdapterCommunityListViewListener<T> onAdapterCommunityListViewListener) {
		this.onAdapterCommunityListViewListener = onAdapterCommunityListViewListener;
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder viewHolder = getViewHolder(position, convertView,parent);
		convert(viewHolder, getItem(position),position);
		return viewHolder.getConvertView();

	}

	public abstract void convert(ViewHolder helper, T item,int position);

	private ViewHolder getViewHolder(int position, View convertView,
			ViewGroup parent)
	{
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
				position);
	}

}

