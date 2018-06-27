package com.stx.xhb.enjoylife.presenter.tuchong;

import com.xhb.core.base.IBaseView;
import com.xhb.core.base.IModel;
import com.xhb.core.model.annotation.Implement;
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/24
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Implement(getFeedAppIPresenterImpl.class)
public interface getFeedAppContact {

    interface Model extends IModel {
        void getFeedAppImage(int page, String type, String posId);
    }

    interface View extends IBaseView {

        void onResponse(List<TuchongImagResponse.FeedListBean> feedList, boolean isMore);

        void onFailure(String msg);
    }
}
