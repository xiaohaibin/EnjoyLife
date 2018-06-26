package com.xhb.core;

import android.app.Application;
import android.content.Context;
import com.xhb.core.util.LoggerHelper;

public class BaseApplication extends Application {

    private static Context mContext;
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
