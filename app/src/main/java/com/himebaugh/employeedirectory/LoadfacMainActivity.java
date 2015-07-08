package com.himebaugh.employeedirectory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class LoadfacMainActivity extends Activity {

    private final int DURATION = 3000;
    private Thread mLoadActivityThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadfac_main);

        mLoadActivityThread = new Thread() {

            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(DURATION);
                    } catch (InterruptedException e) {
                    } finally {
                        finish();
                        Intent intent = new Intent(getBaseContext(),
                                MainTabActivity.class);
                        startActivity(intent);

                    }
                }
            }
        };
        mLoadActivityThread.start();
    }
}
