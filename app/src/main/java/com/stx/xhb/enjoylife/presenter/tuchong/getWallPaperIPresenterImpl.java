package com.stx.xhb.enjoylife.presenter.tuchong;

import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;
import com.xhb.core.base.BaseIPresenter;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class getWallPaperIPresenterImpl extends BaseIPresenter<TuChongWallPaperResponse, getWallPaperContract.View> implements getWallPaperContract.Model {

    @Override
    public void getWallPaper(int page) {
        request(ApiManager.ApiFactory.createTuChongApi().getWallPaper(page), new LoadTaskCallback<TuChongWallPaperResponse>() {
            @Override
            public void onTaskLoaded(TuChongWallPaperResponse data) {
                if (getView() != null && data.getFeedList() != null && !data.getFeedList().isEmpty()) {
                    getView().onResponse(data.getFeedList(), data.isMore());
                }
            }

            @Override
            public void onDataNotAvailable(String msg) {
                if (getView() != null) {
                    getView().onFailure(msg);
                }
            }
        });
    }
}
