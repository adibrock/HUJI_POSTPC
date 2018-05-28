package com.brock.adi.imagedownloader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SingleImageActivity extends AppCompatActivity {
    public static Intent createStartingIntent(Context context, String url){
        Intent intent = new Intent(context, SingleImageActivity.class);
        intent.putExtra("Url", url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);
        ImageView imageView = findViewById(R.id.singleImage);
        String url = getIntent().getStringExtra("Url");
        GlideApp.with(this).load(url).into(imageView);
    }
}
