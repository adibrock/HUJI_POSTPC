package com.brock.adi.multithreading;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity implements MyAsyncTask.CountingCallback {
    private MyAsyncTask asyncTask;
    TextView textView;
    boolean asyncTaskStartedExecuting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        textView = findViewById(R.id.textView);

        findViewById(R.id.button_create).setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asyncTask != null){
                    return;
                }
                textView.setBackgroundColor(Color.WHITE);
                textView.setText("Ready");
                asyncTask = new MyAsyncTask(AsyncTaskActivity.this);
                asyncTaskStartedExecuting = false;
            }
        });

        findViewById(R.id.button_start).setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asyncTask != null && !asyncTaskStartedExecuting){
                    asyncTaskStartedExecuting = true;
                    textView.setBackgroundColor(Color.GRAY);
                    asyncTask.execute();
                }
            }
        });

        findViewById(R.id.button_cancel).setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asyncTask != null){
                    asyncTask.cancel(true);
                }
            }
        });
    }

    @Override
    public void onCountReady(int count) {
        textView.setText(String.valueOf(count));
    }

    @Override
    public void onCountFinished() {
        textView.setBackgroundColor(Color.GREEN);
        textView.setText("Done!");
        asyncTask = null;
    }

    @Override
    public void onBackPressed() {
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        super.onBackPressed();
    }

    @Override
    public void onCountCanceled() {
        textView.setBackgroundColor(Color.RED);
        textView.setText("Canceled!");
        asyncTask = null;
    }
}