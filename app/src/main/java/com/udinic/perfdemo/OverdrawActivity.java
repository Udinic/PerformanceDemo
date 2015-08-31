package com.udinic.perfdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Demonstrate Overdraw layers.
 *
 * Turn on "Debug GPU Overdraw", on the Developer Options, to see them.
 */
public class OverdrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdraw);
    }
}
