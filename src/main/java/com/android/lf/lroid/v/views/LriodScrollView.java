package com.android.lf.lroid.v.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 带滚动监听的scrollview
 */
public class LriodScrollView extends ScrollView {

    public interface ScrollViewListener {

        void onScrollChanged(LriodScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    private ScrollViewListener scrollViewListener = null;

    public LriodScrollView(Context context) {
        super(context);
    }

    public LriodScrollView(Context context, AttributeSet attrs,
                           int defStyle) {
        super(context, attrs, defStyle);
    }

    public LriodScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

}