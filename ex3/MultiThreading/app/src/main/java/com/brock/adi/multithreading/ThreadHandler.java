package com.brock.adi.multithreading;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadHandler {

    interface CountingCallback{
        void onCountReady(int count);
        void onCountFinished();
    }

    private ExecutorService executor;
    private Handler handler;
    private CountingCallback callback;

    public ThreadHandler(CountingCallback callback) {
        this.executor = Executors.newSingleThreadExecutor();
        this.handler = new Handler();
        this.callback = callback;
    }

    public void startCount() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    final int finalI = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onCountReady(finalI);
                        }
                    });

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       callback.onCountFinished();
                   }
               });
            }
        });
    }

    public void cancel(){
        executor.shutdownNow();
    }



}
