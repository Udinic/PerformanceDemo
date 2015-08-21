package com.udinic.perfdemo;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class BusyProcsActivity extends AppCompatActivity {

    public static final String KEY_NUM_CLICKS = "num_clicks";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busy_procs);

        preferences = getSharedPreferences("udini.xml", MODE_PRIVATE);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int clicks = preferences.getInt(KEY_NUM_CLICKS, 0);
//                preferences.edit().putInt(KEY_NUM_CLICKS, ++clicks).commit();
                int n = 30;
                Log.d("PerfDemo", "Start Fib("+n+")");
                long res = fib(n);
                Log.d("PerfDemo", "Fib("+n+") = " + res);
            }
        });

//        iv.animate().rotation(360f).start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView iv = (ImageView) findViewById(R.id.shape);
        ObjectAnimator anim = ObjectAnimator.ofFloat(iv, View.ROTATION, 0f, 360f);
        anim.setDuration(1000);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(ObjectAnimator.RESTART);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_busy_procs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static long fib(int n) {
        if (n <= 1) return n;
        else return fib(n-1) + fib(n-2);
    }

}
