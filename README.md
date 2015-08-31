# PerformanceDemo
Simple demonstrations of performance issues. Using these examples will allow practicing performance analyzing tools, such as Systrace, Traceview and more.

## Perf Demo
This is the main app, showing a few options to simulate different performance issues.

## Keep Busy app
Simple app to keep the CPU busy. Useful to test how other processes affects your app, and more.
Currently, the app creates 4 threads and takes about ~8 seconds to complete. You can play with the values in the service to tweak that.
Running the process is possible through the adb command:
    adb shell am broadcast -a com.udinic.keepbusyapp.ACTION_KEEP_BUSY
Note: you must open the app's activity at least once before that, due to Android security measures to prevent apps from responding to broadcasts before opening the app for the first time.
