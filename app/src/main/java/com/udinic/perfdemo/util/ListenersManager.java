package com.udinic.perfdemo.util;


import java.util.ArrayList;
import java.util.List;

public class ListenersManager {

    private static ListenersManager sInstance;

    List<UdinicListener> listeners = new ArrayList<>();

    private ListenersManager() {

    }

    public static ListenersManager getManager() {
        if (sInstance == null)
            sInstance = new ListenersManager();

        return sInstance;
    }
    public void addListener(UdinicListener listener) {
        listeners.add(listener);
    }

    public void removeListener(UdinicListener listener) {
        listeners.remove(listener);
    }

}
