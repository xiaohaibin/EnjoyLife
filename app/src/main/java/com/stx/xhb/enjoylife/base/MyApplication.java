package com.stx.xhb.enjoylife.base;

import com.android.core.MainApp;
import com.android.core.api.HttpRequest;
import com.android.core.control.crash.AndroidCrash;
import com.android.core.control.logcat.Logcat;
import com.android.core.model.mvp.LogicProxy;
import com.stx.xhb.enjoylife.presenter.getImagePresenter;
import com.tencent.bugly.Bugly;

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
        HttpRequest.getInstance().deBug(true);
        //腾讯bugly初始化  参数1：上下文对象  参数2：注册时申请的APPID  参数3：是否开启debug模式，true表示打开debug模式，false表示关闭调试模式
        Bugly.init(getApplicationContext(), "784b642b7a", true);
    }
}
