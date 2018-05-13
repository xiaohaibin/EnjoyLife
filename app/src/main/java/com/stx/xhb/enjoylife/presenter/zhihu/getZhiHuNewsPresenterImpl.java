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
public class getZhiHuNewsPresenterImpl extends BasePresenter<getZhiHuNewsContract.View> implements getZhiHuNewsContract {

    @Override
    public void getNews(String url) {
        ApiManager.ApiFactory.creatZhiHuApi().getZhiHuNews(url).enqueue(new Callback<ZhiHuNewsResponse>() {
            @Override
            public void onResponse(Call<ZhiHuNewsResponse> call, Response<ZhiHuNewsResponse> response) {
                if (getView()!=null&&response.isSuccessful()) {
                    getView().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<ZhiHuNewsResponse> call, Throwable t) {
                if (getView()!=null) {
                    getView().onFailure(t.getMessage());
                }
            }
        });
    }

    @Override
    public void getNewsBefore(String time) {
        ApiManager.ApiFactory.creatZhiHuApi().getZhiHuNewsBefore(time).enqueue(new Callback<ZhiHuNewsResponse>() {
            @Override
            public void onResponse(Call<ZhiHuNewsResponse> call, Response<ZhiHuNewsResponse> response) {
                if (getView()!=null&&response.isSuccessful()) {
                    getView().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<ZhiHuNewsResponse> call, Throwable t) {
                if (getView()!=null) {
                    getView().onFailure(t.getMessage());
                }
            }
        });
    }
}
