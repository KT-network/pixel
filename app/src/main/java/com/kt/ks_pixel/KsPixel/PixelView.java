package com.kt.ks_pixel.KsPixel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.kt.ks_pixel.R;

import java.util.ArrayList;
import java.util.List;


public class PixelView extends SurfaceView implements SurfaceHolder.Callback {


    private Paint mPaint;
    private Paint mItemPaint;

    private SurfaceHolder mHolder;
    public ColorItemPool colorItem;
    public ColorItemTask itemTasks;
    public ColorItemTask popItemTasks;
    private List<ColorItem> items;

    private int widthNum, heightNum;

    public PixelView(Context context) {
        super(context);
    }

    public PixelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PixelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public PixelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {


        Draw();
        Log.e("TAG", "surfaceCreated: ");
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.i("TAG", "onSizeChanged: ");

        colorItem.init(6 , 10);


    }

    private void init() {

        Log.i("TAG", "init: ");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.blue));
        mPaint.setStyle(Paint.Style.STROKE);

        mItemPaint = new Paint();
        mItemPaint.setAntiAlias(true);
        mItemPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mHolder = this.getHolder();
        mHolder.addCallback(this);


        colorItem = new ColorItemPool();
        itemTasks = new ColorItemTask();
        popItemTasks = new ColorItemTask();
        items = new ArrayList<>();

        this.setZOrderOnTop(true);
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);

    }


    private void Draw() {
        Canvas canvas = mHolder.lockCanvas();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                canvas.drawRect(i*70,j*70,i*70+70,j*70+70,mItemPaint);
            }
        }


        for (ColorItem[] items:colorItem.getColorItems()){
            for (ColorItem item:items){
                mItemPaint.setColor(item.getColor());
                canvas.drawRect(item.getWidth(), item.getHeight(), item.getWidth() + 70, item.getHeight() + 70, mItemPaint);
            }
        }

        Log.e("TAG", "Draw: "+colorItem.getColorItems().length);

        getHolder().unlockCanvasAndPost(canvas);


    }



    public void setSizeNum(int w,int h){
        this.widthNum = w;
        this.heightNum = h;

    }


}
