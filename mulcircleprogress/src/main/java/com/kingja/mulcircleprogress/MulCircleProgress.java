package com.kingja.mulcircleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/4/17 0017 上午 11:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MulCircleProgress extends View {
    private static final int DEFAULT_PROGRESS_COUNT = 3;
    private static final int DEFAULT_PROGRESS_DIVIDER_WIDTH = 2;
    private static final int DEFAULT_PROGRESS_RADIUS = 50;
    private static final int DEFAULT_PROGRESS_WIDTH = 20;
    private static final int DEFAULT_CIRCLE_COLOR = 0xffffffff;
    private static final int DEFAULT_DIVIDER_COLOR = 0xffffffff;
    private static final int DEFAULT_PROGRESSBLACKGROUDCOLOR = 0xffffffff;
    private static final int DEFAULT_PROGRESSPROGRESSCOLOR = 0xffffffff;
    private List<? extends IProgressBar> dataList;
    private float progressRadius;
    private float progressWidth;
    private String TAG = getClass().getSimpleName();
    private int size;
    private Paint circlePaint;
    private Paint dividerPaint;
    private int progressBlackgroudColor;
    private int progressProgressColor;
    private int progressDividerColor;
    private float progressDividerWidth;
    private Paint progressPaint;
    private RectF arcRectF;
    private Paint progressBlackgroundPaint;
    private int progressCount;

    public MulCircleProgress(Context context) {
        this(context, null);
    }

    public MulCircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MulCircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initMulCirCleProgress();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MulCircleProgress);
        progressRadius = typedArray.getDimension(R.styleable.MulCircleProgress_progressRadius,
                dp2px(DEFAULT_PROGRESS_RADIUS));
        progressWidth = typedArray.getDimension(R.styleable.MulCircleProgress_progressWidth,
                dp2px(DEFAULT_PROGRESS_WIDTH));

        progressBlackgroudColor = typedArray.getColor(R.styleable.MulCircleProgress_progressBlackgroudColor,
                DEFAULT_PROGRESSBLACKGROUDCOLOR);
        progressProgressColor = typedArray.getColor(R.styleable.MulCircleProgress_progressProgressColor,
                DEFAULT_PROGRESSPROGRESSCOLOR);

        progressDividerWidth = typedArray.getDimension(R.styleable.MulCircleProgress_progressDividerWidth,
                dp2px(DEFAULT_PROGRESS_DIVIDER_WIDTH));
        progressDividerColor = typedArray.getColor(R.styleable.MulCircleProgress_progressDividerColor,
                DEFAULT_DIVIDER_COLOR);
        progressCount = typedArray.getInteger(R.styleable.MulCircleProgress_progressCount, DEFAULT_PROGRESS_COUNT);
        typedArray.recycle();
    }

    private float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = (dataList == null ? progressCount : dataList.size());
        int size = (int) (progressRadius * 2 + (progressWidth + progressDividerWidth) * count * 2);
        setMeasuredDimension(size, size);
        invalidate();
    }


    private void initMulCirCleProgress() {
        arcRectF = new RectF();
        initPaint();

    }

    private void initPaint() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(DEFAULT_CIRCLE_COLOR);
        circlePaint.setStyle(Paint.Style.FILL);

        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setColor(progressDividerColor);
        dividerPaint.setStyle(Paint.Style.STROKE);
        dividerPaint.setStrokeWidth(progressDividerWidth);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressProgressColor);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(progressWidth);

        progressBlackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressBlackgroundPaint.setColor(progressBlackgroudColor);
        progressBlackgroundPaint.setStyle(Paint.Style.STROKE);
        progressBlackgroundPaint.setStrokeWidth(progressWidth);
    }

    public void setData(List<? extends IProgressBar> dataList) {
        this.dataList = dataList;
        requestLayout();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        size = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (dataList == null) {
            return;
        }
        float dRadius = progressRadius + 0.5f * progressDividerWidth;
        float pRadius = progressRadius + progressDividerWidth + 0.5f * progressWidth;
        for (int i = 0; i < dataList.size(); i++) {
            IProgressBar progressItem = dataList.get(i);
            /*绘制圆*/
            canvas.drawCircle(0.5f * size, 0.5f * size, progressRadius, circlePaint);
            /*分割线 */

            canvas.drawCircle(0.5f * size, 0.5f * size, dRadius, dividerPaint);
            dRadius += progressDividerWidth + progressWidth;
            /*进度条*/
            arcRectF.set(0.5f * size - pRadius, 0.5f * size - pRadius, 0.5f * size + pRadius, 0.5f * size + pRadius);
            canvas.drawCircle(0.5f * size, 0.5f * size, pRadius, progressBlackgroundPaint);
            progressPaint.setColor(progressItem.getProgressColor());
            canvas.drawArc(arcRectF, 270, -progressItem.getProgress() * 360, false, progressPaint);
            pRadius += progressDividerWidth + progressWidth;

        }
        /*1.绘制中心圆*/


    }
}
