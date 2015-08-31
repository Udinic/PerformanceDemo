package com.udinic.perfdemo;

import android.os.Bundle;

/**
 * Demonstrate Overdraw layers.
 *
 * Turn on "Debug GPU Overdraw", on the Developer Options, to see them.
 */
public class OverdrawActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdraw);
    }
}
