package com.udinic.perfdemo;

import android.app.Application;


public class PerfApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        // uncomment to enable LeakCanary, then start Memory leak activity to test it.
//        LeakCanary.install(this);
    }
}
