package com.udinic.perfdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class BgService extends Service {

    LocalBinder mLocalBinder = new LocalBinder();
    private static final int SPIN_COUNT = 2000;
    private Random mRand = new Random();

    public BgService() {


    }

    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }

    public class LocalBinder extends Binder {

        public BgService getService() {

            return BgService.this;
        }
    }

    public String doWork(int arg) {

        int reps = SPIN_COUNT * mRand.nextInt(arg+1);
        int ret = 0;

        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < 100; j++) {
                ret += i * j;
            }
        }

        return String.valueOf(ret);
    }

}
