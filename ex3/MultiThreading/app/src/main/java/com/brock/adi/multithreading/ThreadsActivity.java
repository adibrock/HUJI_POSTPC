package com.brock.adi.multithreading;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThreadsActivity extends AppCompatActivity implements ThreadHandler.CountingCallback {

    ThreadHandler threadHandler;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        textView = findViewById(R.id.textView);

        findViewById(R.id.button_create).setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (threadHandler != null) {return;}
                textView.setBackgroundColor(Color.WHITE);
                textView.setText("Ready");
                threadHandler = new ThreadHandler(ThreadsActivity.this);
            }
        });

        findViewById(R.id.button_start).setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (threadHandler == null) {return;}
                textView.setBackgroundColor(Color.GRAY);
                threadHandler.startCount();
            }
        });

        findViewById(R.id.button_cancel).setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (threadHandler == null) {return;}
                textView.setBackgroundColor(Color.RED);
                textView.setText("Canceled!");
                threadHandler.cancel();
                threadHandler = null;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (threadHandler != null){threadHandler.cancel();}
        super.onBackPressed();
    }

    @Override
    public void onCountReady(int count) {
        textView.setText(String.valueOf(count));
    }

    @Override
    public void onCountFinished() {
        textView.setBackgroundColor(Color.GREEN);
        textView.setText("Done!");
        threadHandler = null;
    }
}
