package com.udinic.perfdemo;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HeavyListActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.list);
        TerAdapter adapter = new TerAdapter();
        adapter.setItems(generateItems());
        list.setAdapter(adapter);
    }

    private List<String> generateItems() {
        int num = 300;
        Random rand = new Random(System.currentTimeMillis());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(RandomStringUtils.random(rand.nextInt(13) + 5, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private class TerAdapter implements ListAdapter {

        List<String> items = new ArrayList<>();
        public final int[] COLORS = new int[]{Color.BLUE, Color.DKGRAY, Color.GREEN, Color.RED, Color.YELLOW};

        public void setItems(List<String> items) {
            this.items = items;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(HeavyListActivity.this);

            String item = (String)getItem(i);
            view = inflater.inflate(R.layout.listitem_row, viewGroup, false);

            TextView tv = (TextView) view.findViewById(R.id.text);
            tv.setText(item);

            TextView txt1 = (TextView) view.findViewById(R.id.txt1);
            TextView txt2 = (TextView) view.findViewById(R.id.txt2);
            txt1.setText(item.toUpperCase());
            txt2.setText(item.toLowerCase());

            view.findViewById(R.id.layout2).setBackgroundColor(COLORS[item.charAt(0) % COLORS.length]);
            view.findViewById(R.id.layout3).setBackgroundColor(COLORS[item.charAt(1) % COLORS.length]);

            return view;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}
