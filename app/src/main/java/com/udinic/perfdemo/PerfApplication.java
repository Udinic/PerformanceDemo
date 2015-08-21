package com.udinic.perfdemo;

import android.app.Application;

/**
 * Created by udi.cohen on 8/17/15.
 */
public class PerfApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
    }
}
