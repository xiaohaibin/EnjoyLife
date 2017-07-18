package com.stx.xhb.enjoylife.presenter.core;

import retrofit2.Response;

/**
 * Created by Mr.xiao on 16/7/28.
 */
public interface LoadDataLogic<T> {

    void onLoadSuccessHandle(Response<T> response);

    void onLoadFail(String msg);
}
