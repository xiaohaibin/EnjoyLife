package com.meikoz.core;

import android.app.Application;
import android.content.Context;

import com.meikoz.core.api.RestApi;
import com.meikoz.core.manage.log.LogLevel;
import com.meikoz.core.manage.log.Logger;

public class MainApplication extends Application {

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
                .logLevel(RestApi.isDebug ? LogLevel.FULL : LogLevel.NONE);
    }
}
