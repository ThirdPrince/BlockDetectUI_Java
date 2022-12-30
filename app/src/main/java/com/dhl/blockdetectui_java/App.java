package com.dhl.blockdetectui_java;

import android.app.Application;
import android.content.Context;

import com.github.moduth.blockcanary.BlockCanary;


public class App extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        BlockDetectByPrinter.start2();
       //BlockCanary.install(this, new AppContext()).start();
    }
    public static Context getAppContext() {
        return sContext;
    }
}
