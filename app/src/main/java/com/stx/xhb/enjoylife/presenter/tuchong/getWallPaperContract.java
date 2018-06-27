package com.stx.xhb.enjoylife.presenter.tuchong;

import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.xhb.core.base.IBaseView;
import com.xhb.core.base.IModel;
import com.xhb.core.model.annotation.Implement;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Implement(getWallPaperIPresenterImpl.class)
public interface getWallPaperContract {

    interface Model extends IModel {
        void getWallPaper(int page);
    }

    interface View extends IBaseView {

        void onResponse(List<TuChongWallPaperResponse.FeedListBean> feedList, boolean isMore);

        void onFailure(String msg);
    }
}
