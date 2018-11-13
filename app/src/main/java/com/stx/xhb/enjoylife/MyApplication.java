package com.stx.xhb.enjoylife;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Config;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.stx.xhb.enjoylife.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.xhb.core.BuildConfig;
import com.xhb.core.BaseApplication;
import com.xhb.core.api.RestApi;
import com.xhb.core.manage.crash.AndroidCrash;
import com.tencent.bugly.Bugly;
import com.xhb.core.util.LoggerHelper;

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
        //初始化日志工具类
        LoggerHelper.initLogger(true);
        RestApi.getInstance();
        Bugly.init(getApplicationContext(), "784b642b7a", true);
        //初始化Toast
        ToastManager.instance.init(this);
        initUM();
    }

    private void initUM() {
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,"");
        MobclickAgent.setSessionContinueMillis(2 * 60 * 1000);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setDebugMode(true);
    }


    public enum ToastManager {
        instance;
        private TextView contentTextView;
        private Toast toast;

        public void init(Context context) {
            contentTextView = (TextView) LayoutInflater.from(context).inflate(R.layout.view_toast, null);
            toast = new Toast(context);
            toast.setView(contentTextView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }

        public void show(CharSequence charSequence, int duration) {
            if (!TextUtils.isEmpty(charSequence)) {
                contentTextView.setText(charSequence);
                toast.setDuration(duration);
                toast.show();
            }
        }
    }
}
