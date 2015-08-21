package com.udinic.perfdemo.util;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udi.cohen on 8/16/15.
 */
public class BigSpendyObject {
    List<Bitmap> list = new ArrayList<>(20);
    String bla[] = new String[1024];

    public String[] get() {
        return bla;
    }

    public List<Bitmap> getList() {
        return list;
    }
}
