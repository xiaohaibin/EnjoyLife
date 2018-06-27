package com.stx.xhb.enjoylife.presenter.zhihu;

import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsContentResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;
import com.xhb.core.base.BaseIPresenter;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/9
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class getNewsContentIPresenterImpl extends BaseIPresenter<ZhiHuNewsContentResponse, getNewsContentContract.View> implements getNewsContentContract.Model {

    @Override
    public void getNewsContent(String id) {
        if (getView() == null) {
            return;
        }
        getView().showLoading();
        request(ApiManager.ApiFactory.creatZhiHuApi().getZhiHuNewsContent(id), new LoadTaskCallback<ZhiHuNewsContentResponse>() {
            @Override
            public void onTaskLoaded(ZhiHuNewsContentResponse data) {
                getView().hideLoading();
                getView().onResponse(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().hideLoading();
                getView().onFailed(msg);
            }
        });
    }

    @Override
    public void onDestory() {

    }
}
