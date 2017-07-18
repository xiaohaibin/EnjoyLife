package com.stx.xhb.enjoylife.base;

import com.android.core.MainApp;
import com.android.core.control.crash.AndroidCrash;
import com.android.core.control.logcat.Logcat;
import com.android.core.model.mvp.LogicProxy;
import com.stx.xhb.enjoylife.presenter.getImagePresenter;

/**
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 程序入口
 */
public class MyApplication extends MainApp {
    @Override
    public void onCreate() {
        super.onCreate();
        LogicProxy.getInstance().init(getImagePresenter.class);
        //Android Crash异常处理
        AndroidCrash.getInstance().init(this);
        //log日志
        Logcat.init("com.android.logcat").hideThreadInfo().methodCount(3);
    }
}
