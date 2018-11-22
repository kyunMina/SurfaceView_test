package com.example.mukai.surfaceview_test;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class MainActivity extends Activity {

    public TextView Counter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Counter = findViewById(R.id.counter);

        final DrawingView view = (DrawingView)findViewById(R.id.drawingView);

        findViewById(R.id.drawingView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Counter.setText("ss");

                return false;
            }
        });

        findViewById(R.id.btnUndo).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                view.undo();
            }
        });

        findViewById(R.id.btnRedo).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                view.redo();
            }
        });
    }

    public void TextChanged(){
        Log.d("debug","TextChanged");
        Counter.setTextColor(Color.BLUE);

    }
}