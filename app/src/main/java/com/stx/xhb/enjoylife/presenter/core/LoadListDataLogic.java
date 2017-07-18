package com.stx.xhb.enjoylife.presenter.core;

import retrofit2.Response;


public interface LoadListDataLogic<T> {

    void onLoadListSuccessHandle(Response<T> response, boolean isMore);

    void onLoadFail(String msg);
}
