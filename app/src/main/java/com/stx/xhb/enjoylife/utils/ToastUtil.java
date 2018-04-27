package com.stx.xhb.enjoylife.utils;
import android.widget.Toast;
import com.stx.xhb.enjoylife.base.MyApplication;

/**
 * Author:Tiger
 * <p>
 * CrateTime:2016-12-16 9:27
 * <p>
 * Description:Toast工具
 */
public class ToastUtil {

    /**
     * @param charSequence 显示文字
     */
    public static void show(CharSequence charSequence) {
        show(charSequence, Toast.LENGTH_SHORT);
    }

    /**
     * @param charSequence 显示文字
     * @param duration     显示时长 Toast.LENGTH_LONG/LENGTH_SHORT
     */
    public static void show(CharSequence charSequence, int duration) {
        MyApplication.ToastManager.instance.show(charSequence, duration);
    }

}
