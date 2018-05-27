package com.brock.adi.imagedownloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls.add("http://i.imgur.com/jGkaxEZ.jpg");
        imageUrls.add("http://i.imgur.com/cuS5DDv.jpg");
        imageUrls.add("http://i.imgur.com/bl5DOR0.jpg");

        adapter = new ImageAdapter(imageUrls);
        RecyclerView imageRecyclerView = findViewById(R.id.recyclerView);
        imageRecyclerView.setAdapter(adapter);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        findViewById(R.id.downloadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

// client id = 15cff3fcff2705c
// client secret = 67e409d6359ad40fe7b5904a2ac306c1f73e08b5