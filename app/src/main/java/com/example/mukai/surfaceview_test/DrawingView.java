package com.example.mukai.surfaceview_test;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    private final HistoryStack<ArrayList<PointF>> history = new HistoryStack<ArrayList<PointF>>();
    private ArrayList<PointF> currentStroke;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * アンドゥ
     */
    public void undo(){
        history.undo();
        invalidate();
    }

    /**
     * リドゥ
     */
    public void redo(){
        history.redo();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if( event.getAction() == MotionEvent.ACTION_DOWN){
            // 新しい描画
            currentStroke = new ArrayList<PointF>();
            return true;
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE){
            currentStroke.add(new PointF(event.getX(),event.getY()));
            invalidate();
            return true;
        }
        else if(event.getAction()==MotionEvent.ACTION_UP){
            history.add(currentStroke);
            currentStroke = null;
            invalidate();

            return true;
        }

        return super.onTouchEvent(event);
    }

    /**
     * PointFの配列を元に一連の線を描画する
     * @param canvas
     * @param paint
     * @param stroke
     */
    private void drawStroke(Canvas canvas,Paint paint,ArrayList<PointF> stroke){
        PointF startPoint = null;
        for(PointF pf:stroke){
            if( startPoint != null){
                canvas.drawLine(startPoint.x, startPoint.y, pf.x, pf.y, paint);
            }

            startPoint = pf;
        }
    }

    private final Paint paint = new Paint();

    {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5.f);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // 履歴に入っている線を描画する
        for(final ArrayList<PointF> stroke:history.iterateUndo()){
            drawStroke(canvas,paint,stroke);
        }

        // 現在描画中の線を描画する
        if( currentStroke != null){
            drawStroke(canvas,paint,currentStroke );
        }
    }
}