package com.udinic.keepbusyapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by udi.cohen on 8/20/15.
 */
public class ReqReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ReqReceiver", "onReceive");
        context.startService(new Intent(context, BusyService.class));
    }
}
