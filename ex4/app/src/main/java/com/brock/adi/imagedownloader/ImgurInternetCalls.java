package com.brock.adi.imagedownloader;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImgurInternetCalls {
    private final static String ALBUMURL1 = "https://api.imgur.com/3/album/aB9xH/images";
    private final static String ALBUMURL2 = "https://api.imgur.com/3/album/5s6oE/images";
    private final static String ALBUMURL3 = "https://api.imgur.com/3/album/QCu3w/images";

    public static class Link{
        public String link;
    }

    public static class Data {
        public List<Link> data;
    }

    public interface OnDataReadyCallback{
        void onDataReady(Data data);
    }

    private static final OkHttpClient client = new OkHttpClient();

    public ImgurInternetCalls(final OnDataReadyCallback callback) {
        ArrayList<String> albums = new ArrayList<>(Arrays.asList(ALBUMURL1,ALBUMURL2, ALBUMURL3));
        Collections.shuffle(albums);
        String album = albums.get(0);
        final Handler handler = new Handler();
        Request request = new Request
                .Builder()
                .addHeader("Authorization", "Client-ID 15cff3fcff2705c")
                .url(album)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String json = response.body().string();
                final Data data = new Gson().fromJson(json, Data.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onDataReady(data);
                    }
                });

            }
        });

    }





}
