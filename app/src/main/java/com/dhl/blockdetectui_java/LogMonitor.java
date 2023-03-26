package com.dhl.blockdetectui_java;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;

/**
 * 检测卡顿的地方
 * @author dhl 
 */
public class LogMonitor {

    private static final String TAG = "LogMonitor";

    private static LogMonitor instance = new LogMonitor();
    private HandlerThread mLogThread = new HandlerThread("log");
    private Handler mIoHandler;

    private static final long TIME_BLOCK = 1000L;

    private LogMonitor(){
        mLogThread.start();
        mIoHandler = new Handler(mLogThread.getLooper());
    }
    private static Runnable mLogRunnable = new Runnable() {
        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTraceElements = Looper.getMainLooper().getThread().getStackTrace();
            for (StackTraceElement s:stackTraceElements){
                sb.append(s.toString()+"\n");
            }
            Log.e(TAG,"此处卡顿了！"+sb.toString());

        }
    };


    public static LogMonitor getInstance() {
        return instance;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public boolean isMonitor(){
        return mIoHandler.hasCallbacks(mLogRunnable);
    }

    public void startMonitor(){
        mIoHandler.postDelayed(mLogRunnable,TIME_BLOCK);

    }

    public void removeMonitor(){
        mIoHandler.removeCallbacks(mLogRunnable);
    }
}
