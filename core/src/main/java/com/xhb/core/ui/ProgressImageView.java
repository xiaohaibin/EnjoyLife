package com.xhb.core.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.github.chrisbanes.photoview.PhotoView;
import com.xhb.core.util.ScreenUtil;

/**
 * @author: xiaohaibin.
 * @time: 2018/7/3
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class ProgressImageView extends PhotoView {

    public static final int FONT_SIZE = 14;
    public static final int ROUND_WIDTH = 50;
    public static final int STROKE_WIDTH = 7;
    private int mFontSize;
    private int mRoundWidth;
    private int mStrokeWidth;
    private Paint mPaint;
    private boolean mShowProgress;
    private int mProgress;
    private float mTextY;
    private int mCenterX;
    private int mCenterY;
    private int mRadius;
    private RectF mOval;


    public ProgressImageView(Context context) {
        super(context);
    }

    public ProgressImageView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public ProgressImageView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    @TargetApi(21)
    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        float scale = getContext().getResources().getDisplayMetrics().density;

        mFontSize = (int) (FONT_SIZE * scale);
        mRoundWidth = (int) (ROUND_WIDTH * scale);
        mStrokeWidth = (int) (STROKE_WIDTH * scale);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mFontSize);

        mCenterX = ScreenUtil.getScreenWidth(getContext()) / 2;
        mCenterY = ScreenUtil.getScreenHeight(getContext()) / 2;
        mRadius = mRoundWidth / 2;

        mTextY = mCenterY + mFontSize * 11.0f / 28;

        mOval = new RectF(mCenterX - mRadius, mCenterY - mRadius, mCenterX
                + mRadius, mCenterY + mRadius);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mShowProgress) {
            if (mCenterX == 0 || mCenterY == 0) {
                init();
            }
            // 画最外层的大圆环
            mPaint.setColor(Color.DKGRAY);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mStrokeWidth);
            canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);

            // 画进度百分比
            mPaint.setStrokeWidth(0);
            mPaint.setColor(Color.WHITE);
            mPaint.setTypeface(Typeface.MONOSPACE);
            mPaint.setTextAlign(Paint.Align.CENTER);
            String progressStr = mProgress + "%";
            canvas.drawText(progressStr, mCenterX, mTextY, mPaint);

            // 画圆环的进度
            mPaint.setStrokeWidth(mStrokeWidth);
            mPaint.setColor(Color.WHITE);
            canvas.drawArc(mOval, 0, 360 * mProgress / 100, false, mPaint);
        } else {
            super.onDraw(canvas);
        }
    }

    public void startProgress() {
        mShowProgress = true;
        setProgress(0);
    }

    public void setProgress(int progress) {
        if (mShowProgress) {
            mProgress = progress;
            invalidate();
        }
    }

    public void closeProgress() {
        mShowProgress = false;
    }
}
