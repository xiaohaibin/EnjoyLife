package com.stx.xhb.enjoylife.presenter.core;

import com.android.core.model.mvp.BaseView;


public interface LoadView<T> extends BaseView {

    void onLoadComplete();

    void onLoadSuccessResponse(T body);

    void onFailure(String msg);
}
