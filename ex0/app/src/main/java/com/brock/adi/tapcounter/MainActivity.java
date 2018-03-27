package com.brock.adi.tapcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView counter = findViewById(R.id.counterText);
        counter.setText(R.string.press_button_to_start);
        counter.setTextSize(24);

        Button counterButton = findViewById(R.id.counterButton);
        counterButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                counter.setText("" + (++MainActivity.this.counter));
                counter.setTextSize(64);
                }
        });
    }
}
