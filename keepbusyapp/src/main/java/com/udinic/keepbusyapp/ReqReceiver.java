package com.udinic.keepbusyapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Broadcast receiver to start that service that will keep the cpu busy.
 *
 * Usage:
 *      adb shell am broadcast -a com.udinic.keepbusyapp.ACTION_KEEP_BUSY
 *
 */
public class ReqReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ReqReceiver", "Starting BusyService");
        context.startService(new Intent(context, BusyService.class));
    }
}
