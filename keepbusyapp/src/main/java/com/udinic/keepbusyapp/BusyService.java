package com.udinic.keepbusyapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by udi.cohen on 8/20/15.
 */
public class BusyService extends Service {
    private static final int SPIN_COUNT = 2000;
    int numThreads = 4;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("BusyService", "Starting " + numThreads + " threads");
        long startWhen = System.nanoTime();

        SpinThread threads[] = new SpinThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new SpinThread(i);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ie) {
                System.err.println("join " + i + " failed: " + ie);
            }
        }

        long endWhen = System.nanoTime();
        Log.d("BusyService", "All threads finished in " +
            ((endWhen - startWhen) / 1000000) + "ms");

        return BIND_AUTO_CREATE;
    }

    static class SpinThread extends Thread {
        private int mTid;

        SpinThread(int tid) {
            mTid = tid;
        }

        public void run() {
            long startWhen = System.nanoTime();
            Log.d("BusyService", "Thread " + mTid + " started");
            int tid = mTid;
            int reps = SPIN_COUNT + tid;
            int ret = 0;

            for (int i = 0; i < reps; i++) {
                for (int j = 0; j < 1000000; j++) {
                    ret += i * j;
                }
            }

            long endWhen = System.nanoTime();
            Log.d("BusyService", "Thread " + mTid + " finished in " +
                ((endWhen - startWhen) / 1000000) + "ms (" + ret + ")");
        }
    }
    
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
