package com.stx.xhb.enjoylife.base;

import com.xhb.core.BuildConfig;
import com.xhb.core.BaseApplication;
import com.xhb.core.api.RestApi;
import com.xhb.core.manage.crash.AndroidCrash;
import com.tencent.bugly.Bugly;

/**
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 程序入口
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //Android Crash异常处理
        AndroidCrash.getInstance().init(this);
        RestApi.getInstance().bug(BuildConfig.DEBUG);
        //腾讯bugly初始化  参数1：上下文对象  参数2：注册时申请的APPID  参数3：是否开启debug模式，true表示打开debug模式，false表示关闭调试模式
        Bugly.init(getApplicationContext(), "784b642b7a", BuildConfig.DEBUG);
    }
}
