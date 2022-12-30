package com.dhl.blockdetectui_java;

import android.os.Looper;
import android.util.Log;
import android.util.Printer;
import android.view.Choreographer;

public class BlockDetectByPrinter {

    public static void start(){
        Looper.getMainLooper().setMessageLogging(new Printer() {
            private static final String START = ">>>>> Dispatching";
            private static final String END = "<<<<< Finished";
            @Override
            public void println(String x) {
                if(x.startsWith(START)){
                    LogMonitor.getInstance().startMonitor();
                }
                if(x.startsWith(END)){
                    LogMonitor.getInstance().removeMonitor();
                }

            }
        });
    }

    /**
     * 利用Choreographer
     */
    public static void start2(){
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                Log.d("Choreographer","doFrame");
                LogMonitor.getInstance().removeMonitor();
                LogMonitor.getInstance().startMonitor();
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }
}
