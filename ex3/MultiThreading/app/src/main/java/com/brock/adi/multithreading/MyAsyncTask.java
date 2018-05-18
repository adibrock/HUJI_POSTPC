package com.brock.adi.multithreading;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

    public interface CountingCallback{
        void onCountReady(int count);
        void onCountFinished();
        void onCountCanceled();
    }

    private CountingCallback callback;

    public MyAsyncTask(CountingCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 1; i <= 10 ; i++) {
            if(this.isCancelled()){
                return null;
            }
            publishProgress(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return null ;
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        callback.onCountReady(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.onCountFinished();
    }

    @Override
    protected void onCancelled(Void aVoid) {
        callback.onCountCanceled();
    }
}
