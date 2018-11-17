package com.example.mukai.surfaceview_test;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawingView view = (DrawingView)findViewById(R.id.drawingView);

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
}