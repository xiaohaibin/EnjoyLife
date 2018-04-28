package com.stx.xhb.enjoylife.presenter.tuchong;

import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;
import com.xhb.core.base.BaseView;
import com.xhb.core.model.annotation.Implement;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Implement(getWallPaperPresenterImpl.class)
public interface getWallPaperContract {

    void getWallPaper(int page);

    interface View extends BaseView {

        void onResponse(List<TuChongWallPaperResponse.FeedListBean> feedList, boolean isMore);

        void onFailure(String msg);
    }
}
