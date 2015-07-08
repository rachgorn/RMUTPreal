package com.himebaugh.employeedirectory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class LoadActivity extends Activity {
    private final int DURATION = 3000;
    private Thread mLoadActivityThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        final GifMovieView gif1 = (GifMovieView) findViewById(R.id.gifMovieView);
        gif1.setMovieResource(R.drawable.loading);


        mLoadActivityThread = new Thread() {

            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(DURATION);
                    } catch (InterruptedException e){
                    } finally {
                        finish();
                        Intent intent = new Intent(getBaseContext(),
                                SubActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        mLoadActivityThread.start();

    }

}
