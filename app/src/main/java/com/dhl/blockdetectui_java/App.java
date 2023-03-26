package com.dhl.blockdetectui_java;

import android.app.Application;
import android.content.Context;



public class App extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        BlockDetectByPrinter.start();
       //BlockCanary.install(this, new AppContext()).start();
    }
    public static Context getAppContext() {
        return sContext;
    }
}
