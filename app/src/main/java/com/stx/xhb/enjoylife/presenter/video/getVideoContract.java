package com.stx.xhb.enjoylife.presenter.video;

import com.xhb.core.base.IBaseView;
import com.xhb.core.base.IModel;
import com.xhb.core.model.annotation.Implement;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;

/**
 * Author：xiaohaibin
 * Time：2017/7/26
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(getVideoIPresenterImpl.class)
public interface getVideoContract {

    interface Model extends IModel {
        void getVideoInfo(String date, int num);
    }

    interface getVideoView extends IBaseView {

        void onResponse(VideoResponse response);

        void onFailure(String msg);
    }
}
