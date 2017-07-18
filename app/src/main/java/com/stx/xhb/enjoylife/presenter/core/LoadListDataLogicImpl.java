package com.stx.xhb.enjoylife.presenter.core;

import com.android.core.control.Toast;
import com.android.core.model.mvp.BasePresenter;

import retrofit2.Response;

/**
 *加载
 */
public class LoadListDataLogicImpl<T> extends BasePresenter<BaseDataView> implements LoadListDataLogic<T> {


    /**
     * 处理获取列表成功回调的公共函数
     *
     * @param response
     * @param isMore
     */
    public void onLoadListSuccessHandle(Response<T> response, boolean isMore) {
        //加载完成
        getView().onLoadComplete(isMore);
        T body = response.body();
        if (body != null) {
            getView().onResponseLData(body, isMore);
        }
    }

    @Override
    public void onLoadFail(String msg) {
        //提示请求失败
        Toast.show(msg);
    }

}
