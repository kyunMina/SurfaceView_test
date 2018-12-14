package com.example.mukai.surfaceview_test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    public TextView Counter;
    public int a = 0;

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
                Log.d("debug","TextChanged");


                int event = motionEvent.getActionMasked();

                switch (event){
                    case MotionEvent.ACTION_UP:
                        //view.performClick();
                        a++;
                        Counter.setText(""+a);
                        //TextChanged();


                }

                return false;
            }
        });

        findViewById(R.id.btnUndo).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (a > 0){
                    view.undo();
                    a--;
                    Counter.setText(""+a);
                    findViewById(R.id.drawingView).setOnTouchListener(null);

                }

            }
        });

        findViewById(R.id.btnRedo).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (a < 3){
                    view.redo();
                    a++;
                    Counter.setText(""+a);
                }

            }
        });
    }

    public void TextChanged(){
//        Log.d("debug","TextChanged");
//        Counter.setTextColor(Color.BLUE);

        //
        //Toast toast = Toast.makeText(this,""+a,Toast.LENGTH_SHORT);
        //toast.show();

    }
}