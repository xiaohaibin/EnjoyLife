package com.stx.xhb.enjoylife.presenter.core;

import com.android.core.model.mvp.BasePresenter;

import retrofit2.Response;

public class LoadPresenter<T> extends BasePresenter<LoadView> implements LoadDataLogic<T> {

    @Override
    public void onLoadSuccessHandle(Response<T> response) {
        getView().onLoadComplete();
        T body = response.body();
        if (body != null)
            getView().onLoadSuccessResponse(body);
    }

    public void onLoadFail(String msg) {
        //可以选择单独还是统一处理
        getView().onFailure(msg);
    }
}
