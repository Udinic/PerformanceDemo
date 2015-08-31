package com.udinic.perfdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.udinic.perfdemo.util.BigSpendyObject;
import com.udinic.perfdemo.util.ListenersManager;
import com.udinic.perfdemo.util.UdinicListener;

/**
 * Demonstration of memory leak.
 *
 * Start this activity and rotate the screen a few times, then take a heap dump.
 *
 * Using the Heap dump, we'll see:
 *      - The activity has many instances
 *      - following the reference tree of its instances, we see the Activity is being retained
 *      by the ListenersManager object.
 *      - Many instances of BigSpendyObject, even though we don't use it in our code.
 *
 * Using Allocation Tracker, we'll see that we have many allocations of BigSpendyObject,
 * all being done on the CTor.
 *
 */
public class MemoryLeakActivity extends BaseActivity implements UdinicListener {

    String mStringField;
    BigSpendyObject spendyObject = new BigSpendyObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        mStringField = "onCreate() Called";

        ListenersManager.getManager().addListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memory, menu);
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

    @Override
    public void doStuff() {
        // Stuff
    }
}
