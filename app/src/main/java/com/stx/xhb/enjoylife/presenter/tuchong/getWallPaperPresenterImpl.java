package com.stx.xhb.enjoylife.presenter.tuchong;

import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;
import com.xhb.core.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class getWallPaperPresenterImpl extends BasePresenter<getWallPaperContract.View> implements getWallPaperContract{

    private Call<TuChongWallPaperResponse> mResponseCall;

    @Override
    public void getWallPaper(int page) {
        mResponseCall = ApiManager.ApiFactory.createTuChongApi().getWallPaper(page);
        mResponseCall.enqueue(new Callback<TuChongWallPaperResponse>() {
            @Override
            public void onResponse(Call<TuChongWallPaperResponse> call, Response<TuChongWallPaperResponse> response) {
                if (response.isSuccessful() && response.body() != null&&response.body().getFeedList()!=null&&!response.body().getFeedList().isEmpty()) {
                    getView().onResponse(response.body().getFeedList(), response.body().isMore());
                }
            }

            @Override
            public void onFailure(Call<TuChongWallPaperResponse> call, Throwable t) {
                getView().onFailure(t.getMessage());
            }
        });
    }

    public void cancleNetWork(){
        if (mResponseCall!=null){
            mResponseCall.cancel();
        }
    }
}
