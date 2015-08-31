package com.udinic.perfdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainSelectionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selection);

        createOnClick(R.id.btnListService, ListServiceCallActivity.class);
        createOnClick(R.id.btnBusyUIThread, BusyUIThreadActivity.class);
        createOnClick(R.id.btnMemoryLeakActivity, MemoryLeakActivity.class);
        createOnClick(R.id.btnOverdraw, OverdrawActivity.class);
        createOnClick(R.id.btnHeavyList, HeavyListActivity.class);
        createOnClick(R.id.btnHeavyRenderingAnim, HeavyAnimationActivity.class);
    }


    private void createOnClick(@IdRes int btnResId, final Class<?> activityToStart) {
        findViewById(btnResId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSelectionActivity.this, activityToStart));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_selection, menu);
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
