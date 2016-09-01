package com.android.lf.lroid.v.views;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Scroller;

import java.util.Map;

/**
 * Created by feng on 2016/8/22.
 */

public class SlidingListView extends ListView {

    private View itemView;
    private int downX;
    private int downY;
    private int mTouchSlop;

    private Scroller mScroller;
    private final int SNAP_VELOCITY = 600;
    private VelocityTracker velocityTracker;

    private int width;

    private boolean isSliding = false;

    public SlidingListView(Context context) {
        super(context);
        init();
    }

    public SlidingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlidingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        mScroller = new Scroller(getContext());
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(getContext()));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                addVelocityTracker(ev);
                if (!mScroller.isFinished()){
                    return super.dispatchTouchEvent(ev);
                }
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                int index = pointToPosition(downX, downY);
                if (index == AbsListView.INVALID_POSITION) {
                    return super.dispatchTouchEvent(ev);
                }
                itemView = getChildAt(index - getFirstVisiblePosition());
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(getScrollVelocity()) > SNAP_VELOCITY || (Math.abs(ev.getX() - downX) > mTouchSlop && Math.abs(ev.getY() - downY) < mTouchSlop)) {
                    isSliding = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                removeVelocityTracker();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isSliding) {
            addVelocityTracker(ev);
            int x = (int) ev.getX();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    int deltaX = downX - x;
                    downX = x;
                    itemView.scrollBy(deltaX, 0);
                    return true;
                case MotionEvent.ACTION_UP:
                    Log.e("TAG","velocity is ------>  "+getScrollVelocity());
                    if (getScrollVelocity() > SNAP_VELOCITY) {
                        //向右滑动
                        slidingRight();
                    } else if (getScrollVelocity() < -SNAP_VELOCITY) {
                        //向左滑动
                    }
                    removeVelocityTracker();
                    isSliding = false;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }


    private void slidingRight() {
        int delta = width - Math.abs(itemView.getScrollX());
        mScroller.startScroll(itemView.getScrollX(), 0, -delta, 0, Math.abs(delta));
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            itemView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void addVelocityTracker(MotionEvent ev) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(ev);
    }

    private void removeVelocityTracker() {
        if (velocityTracker != null) {
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }

    private int getScrollVelocity() {
        velocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) velocityTracker.getXVelocity();
        return velocity;
    }

}

