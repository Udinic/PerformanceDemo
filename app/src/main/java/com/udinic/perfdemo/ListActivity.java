package com.udinic.perfdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.udinic.perfdemo.service.BgService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private BgService mService = null;
    private boolean mBound = false;
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BgService.LocalBinder binder = (BgService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            initList();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mList = (ListView)findViewById(R.id.list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, BgService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    private void initList() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) {

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                TextView view = (TextView)super.getView(position, convertView, parent);

                Thread trd = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        String res = mService.doWork(position);

                    }
                }, "udini-trd_"+position);

                trd.start();

                try {
                    trd.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                view.setText(getItem(position));

                return view;
            }
        };


        List<String> items = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            items.add("Item[" + i + "]");
        }

        adapter.addAll(items);
        mList.setAdapter(adapter);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
}
