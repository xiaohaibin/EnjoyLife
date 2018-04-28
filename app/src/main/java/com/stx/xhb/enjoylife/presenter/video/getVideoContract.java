package com.stx.xhb.enjoylife.presenter.video;

import com.xhb.core.base.BaseView;
import com.xhb.core.model.annotation.Implement;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;

/**
 * Author：xiaohaibin
 * Time：2017/7/26
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(getVideoPresenterImpl.class)
public interface getVideoContract {

    void getVideoInfo(String date, int num);

    interface getVideoView extends BaseView{

        void onResponse(VideoResponse response);

        void onFailure(String msg);
    }
}
