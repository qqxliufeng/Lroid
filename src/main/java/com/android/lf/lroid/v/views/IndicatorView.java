package com.android.lf.lroid.v.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.lf.lroid.R;

/**
 * Created by feng on 2016/9/14.
 */

public class IndicatorView extends View {

    private Paint mPaint;
    private int radius;
    private boolean isFocused;

    private int indicatorColor;

    public IndicatorView(Context context) {
        this(context,null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.IndicatorView,defStyleAttr,0);
        indicatorColor = typedArray.getColor(R.styleable.IndicatorView_indicator_color,context.getResources().getColor(R.color.colorPrimary));
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(getMeasuredHeight() / 2,getMeasuredWidth() / 2);
    }

    public void setSelect(boolean focused) {
        isFocused = focused;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(radius,radius,radius ,mPaint);

        if (isFocused){
            mPaint.setColor(Color.WHITE);
        }else {
            mPaint.setColor(indicatorColor);
        }
        canvas.drawCircle(radius,radius,radius - 2,mPaint);

    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        invalidate();
    }
}
