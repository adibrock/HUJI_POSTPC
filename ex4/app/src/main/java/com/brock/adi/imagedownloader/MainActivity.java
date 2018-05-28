package com.brock.adi.imagedownloader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImgurInternetCalls.OnDataReadyCallback, ImageAdapter.OnImageClicked {

    ImageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ImageAdapter(this);
        RecyclerView imageRecyclerView = findViewById(R.id.recyclerView);
        imageRecyclerView.setAdapter(adapter);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        findViewById(R.id.downloadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImgurInternetCalls(MainActivity.this);
            }
        });
    }

    @Override
    public void onDataReady(ImgurInternetCalls.Data data) {
        ArrayList<String> imageUrls = new ArrayList<>();
        for(ImgurInternetCalls.Link link: data.data){
            imageUrls.add(link.link);
        }
        adapter.setImageUrls(imageUrls);
    }

    @Override
    public void onClicked(String url) {
        startActivity(SingleImageActivity.createStartingIntent(this, url));

    }
}

// client id = 15cff3fcff2705c
// client secret = 67e409d6359ad40fe7b5904a2ac306c1f73e08b5