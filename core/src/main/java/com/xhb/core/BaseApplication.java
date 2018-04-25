package com.xhb.core;

import android.app.Application;
import android.content.Context;

import com.xhb.core.api.RestApi;
import com.xhb.core.manage.log.LogLevel;
import com.xhb.core.manage.log.Logger;

public class BaseApplication extends Application {

    private static Context mContext;
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Logger.init(getPackageName())
                .methodCount(3)
                .hideThreadInfo()
                .logLevel(BuildConfig.DEBUG? LogLevel.FULL : LogLevel.NONE);
    }
}
