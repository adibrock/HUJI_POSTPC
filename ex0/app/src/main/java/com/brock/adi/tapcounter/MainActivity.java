package com.brock.adi.tapcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_COUNT = "count";
    int counter = 0;
    TextView counterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterText = findViewById(R.id.counterText);

        if (savedInstanceState != null){
            counter = savedInstanceState.getInt(KEY_COUNT);
        }

        if (counter == 0) {
            counterText.setText(R.string.press_button_to_start);
        } else{
            setNumberOfTaps();
        }

        findViewById(R.id.counterButton).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    counter ++;
                    setNumberOfTaps();
                }
        });
    }

    private void setNumberOfTaps(){
        counterText.setText(String.valueOf(counter));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putInt(KEY_COUNT, counter);
        super.onSaveInstanceState(outState);
    }
}
