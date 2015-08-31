package com.udinic.perfdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.udinic.perfdemo.util.ViewServer;

/**
 * Base activity.
 *
 * calling the ViewServer's methods, to enable HierarchyViewer on non-eng devices.
 */
public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewServer.get(this).addWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

}
