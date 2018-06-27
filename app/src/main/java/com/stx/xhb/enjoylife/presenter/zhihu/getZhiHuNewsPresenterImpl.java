package com.stx.xhb.enjoylife.presenter.zhihu;

import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;
import com.xhb.core.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/9
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class getZhiHuNewsPresenterImpl extends BasePresenter<ZhiHuNewsResponse, getZhiHuNewsContract.View> implements getZhiHuNewsContract {

    @Override
    public void getNews(String url) {
        request(ApiManager.ApiFactory.creatZhiHuApi().getZhiHuNews(url), new LoadTaskCallback<ZhiHuNewsResponse>() {
            @Override
            public void onTaskLoaded(ZhiHuNewsResponse data) {
                if (getView() != null) {
                    getView().onResponse(data);
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

    @Override
    public void getNewsBefore(String time) {
        request(ApiManager.ApiFactory.creatZhiHuApi().getZhiHuNewsBefore(time), new LoadTaskCallback<ZhiHuNewsResponse>() {
            @Override
            public void onTaskLoaded(ZhiHuNewsResponse data) {
                if (getView() != null) {
                    getView().onResponse(data);
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
