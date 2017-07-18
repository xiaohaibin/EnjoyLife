package com.stx.xhb.androidcore.crash;

import java.io.File;

/**
 * Created by jxnk25 on 2017/2/13.
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description：
 */

public interface HttpReportCallback {

    void uploadException2remote(File file);
}
