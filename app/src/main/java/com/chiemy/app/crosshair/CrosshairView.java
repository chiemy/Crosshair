package com.chiemy.app.crosshair;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created: chiemy
 * Date: 16/9/18
 * Description:
 */
public class CrosshairView extends View{
    private static final int DEFAULT_COLOR = Color.RED;
    public static final int DEFAULT_SIZE = 10;
    private static final int LINE_WIDTH = 2;

    private static final float MIN_SCALE = 1f;
    public static final float MAX_SCALE = 5f;

    private float width = DEFAULT_SIZE;

    public static final float MAX_WIDTH = DEFAULT_SIZE * MAX_SCALE * 2;

    private Paint crosshairPaint;

    public CrosshairView(Context context) {
        this(context, null);
    }

    public CrosshairView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrosshairView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        crosshairPaint = new Paint();
        crosshairPaint.setColor(DEFAULT_COLOR);
        crosshairPaint.setStrokeWidth(LINE_WIDTH);
        crosshairPaint.setStyle(Paint.Style.FILL);
    }

    private int centerX;
    private int centerY;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(centerX - width, centerY, centerX + width, centerY, crosshairPaint);
        canvas.drawLine(centerX, centerY - width, centerX, centerY + width, crosshairPaint);
    }

    public void setLineWidth(float lineWidth) {
        crosshairPaint.setStrokeWidth(lineWidth);
        postInvalidate();
    }

    public void setColor(int color) {
        crosshairPaint.setColor(color);
        postInvalidate();
    }

    public void setScale(float scale) {
        scale = Math.max(MIN_SCALE, scale);
        scale = Math.min(MAX_SCALE, scale);
        width = DEFAULT_SIZE * scale;
        postInvalidate();
    }
}
