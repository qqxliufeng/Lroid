package com.android.lf.lroid.v.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by feng on 2016/9/9.
 */

public class LroidListView extends ListView {
    public LroidListView(Context context) {
        super(context);
    }

    public LroidListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LroidListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }
}
