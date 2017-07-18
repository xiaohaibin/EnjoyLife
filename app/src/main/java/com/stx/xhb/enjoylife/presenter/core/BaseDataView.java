package com.stx.xhb.enjoylife.presenter.core;

import com.android.core.model.mvp.BaseView;

/**
 *加载列表数据view
 */
public interface BaseDataView<T> extends BaseView {

    void onLoadComplete(boolean isMore);

    void onResponseLData(T response, boolean isMore);
}
